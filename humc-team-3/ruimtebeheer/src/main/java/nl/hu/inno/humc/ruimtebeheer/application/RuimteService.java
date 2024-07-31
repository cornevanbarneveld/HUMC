package nl.hu.inno.humc.ruimtebeheer.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.ruimtebeheer.data.RuimteRepo;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.inventaris.MiddelVerplaatsNotification;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel.Middel;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte.Ruimte;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel.TypeGevaar;
import nl.hu.inno.humc.ruimtebeheer.producer.MiddelAfwezigProducer;
import nl.hu.inno.humc.ruimtebeheer.producer.message.MiddelAfwezigMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class RuimteService {

    @Autowired
    private RuimteRepo ruimteRepository;

    @Autowired
    private MiddelService middelService;

    @Autowired
    private MiddelAfwezigProducer middelAfwezigProducer;

    public void saveRuimte(Ruimte ruimte) {
        ruimteRepository.save(ruimte);
    }

    public Ruimte getRuimteForId(Long id) throws Exception {
        return ruimteRepository.findById(id)
                .orElseThrow(() -> new Exception("Ruimte not found"));
    }

    public Ruimte getRuimte(Long id) throws Exception {
        Ruimte ruimte = getRuimteForId(id);
        ruimte.getInventaris().checkMiddelenOverDeDatum();
        return ruimte;

    }

    public Ruimte getRuimteByNaam(String naam) {
        return ruimteRepository.findByNaam(naam)
                .orElseThrow(() -> new RuntimeException("Ruimte not found"));
    }

    public Middel addMiddel(String naam, String beschrijving, TypeGevaar typeGevaar, String id, Long ruimteId, LocalDateTime registratieDatum, LocalDateTime houdbaarheidsDatum ) throws Exception {
        Middel middel = new Middel(naam,beschrijving,typeGevaar, registratieDatum, houdbaarheidsDatum);
        Ruimte ruimte = getRuimteForId(ruimteId);
        ruimte.getInventaris().addMiddel(middel);
        ruimteRepository.save(ruimte);
        return middel;
    }

    public void deleteMiddel(Long middelId, Long ruimteId) throws Exception {
        Ruimte ruimte = getRuimteForId(ruimteId);
        middelService.deleteMiddel(middelId);
        ruimte.getInventaris().deleteMiddel(middelId);
        middelAfwezigProducer.sendMessage(new MiddelAfwezigMessage(middelId));

    }

    public List<Ruimte> getRuimtes() {
        return ruimteRepository.findAll();
    }

    public Ruimte addVerplaatsMiddelNotification(Long ruimteId, Long middelId, LocalDateTime beginDatum, LocalDateTime eindDatum) throws Exception {
        Middel middel = middelService.getMiddelForId(middelId);
        List<Ruimte> ruimtes = this.getRuimtes();
        Ruimte oudeRuimte = null;
        for (Ruimte ruimte1 : ruimtes){
            if (ruimte1.getInventaris().getMiddelen().contains(middel)){
                oudeRuimte = ruimte1;
            }
        }
        if (oudeRuimte == null){
            throw new Exception();
        }
        MiddelVerplaatsNotification middelVerplaatsNotification = new MiddelVerplaatsNotification(middelId, ruimteId, beginDatum, eindDatum);
        oudeRuimte.getInventaris().addTeVerplaatsenMiddel(middelVerplaatsNotification);
        this.ruimteRepository.save(oudeRuimte);
        return oudeRuimte;

    }

    public Ruimte verplaatsMiddel(Long oudeRuimteId, Long nieuweRuimteId, Long middelId) throws Exception {
        Ruimte oudeRuimte = getRuimteForId(oudeRuimteId);
        Ruimte nieuweRuimte = getRuimteForId(nieuweRuimteId);
        oudeRuimte.getInventaris().deleteMiddel(middelId);
        nieuweRuimte.getInventaris().addMiddel(middelService.getMiddelForId(middelId));
        return nieuweRuimte;

    }

    public Middel registreerdMiddelAfwezig(Long middelId) {
        middelAfwezigProducer.sendMessage(new MiddelAfwezigMessage(middelId));
        return middelService.getMiddelForId(middelId);
    }

}

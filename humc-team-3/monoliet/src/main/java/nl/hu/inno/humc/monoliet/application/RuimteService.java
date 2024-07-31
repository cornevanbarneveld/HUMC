package nl.hu.inno.humc.monoliet.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.monoliet.data.RuimteRepository;
import nl.hu.inno.humc.monoliet.domain.middel.Middel;
import nl.hu.inno.humc.monoliet.domain.middel.TypeGevaar;
import nl.hu.inno.humc.monoliet.domain.ruimte.Ruimte;
import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class RuimteService {

    @Autowired
    private RuimteRepository ruimteRepository;

    @Autowired
    private ApparaatService apparaatService;

    @Autowired
    private MiddelService middelService;

    public void saveRuimte(Ruimte ruimte) {
        ruimteRepository.save(ruimte);
    }

    public Ruimte getRuimteForId(Long id) {
        return ruimteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ruimte not found"));
    }

    public Ruimte getRuimte(Long id) {
        Ruimte ruimte = getRuimteForId(id);
        ruimte.getInventaris().checkMiddelenOverDeDatum();
        return ruimte;
    }

    public Ruimte getRuimteByNaam(String naam) {
        return ruimteRepository.findByNaam(naam)
                .orElseThrow(() -> new RuntimeException("Ruimte not found"));
    }

    public Apparaat addApparaat(String naam, boolean elektrisch, String id, Long ruimteId) {
        Apparaat apparaat = new Apparaat(naam,elektrisch,id);
        Ruimte ruimte = getRuimteForId(ruimteId);
        ruimte.getInventaris().addApparaat(apparaat);
        return apparaat;
    }

    public void deleteApparaat(String apparaatId, Long ruimteId) {
        Ruimte ruimte = getRuimteForId(ruimteId);
        apparaatService.deleteApparaat(apparaatId);
        ruimte.getInventaris().deleteApparat(apparaatId);
    }

    public Middel addMiddel(String naam, String beschrijving, TypeGevaar typeGevaar, String id, Long ruimteId, LocalDateTime registratieDatum, LocalDateTime houdbaarheidsDatum ) {
        Middel middel = new Middel(naam,beschrijving,typeGevaar, id, registratieDatum, houdbaarheidsDatum);
        Ruimte ruimte = getRuimteForId(ruimteId);
        ruimte.getInventaris().addMiddel(middel);
        return middel;
    }

    public void deleteMiddel(String middelId, Long ruimteId) {
        Ruimte ruimte = getRuimteForId(ruimteId);
        middelService.deleteMiddel(middelId);
        ruimte.getInventaris().deleteMiddel(middelId);
    }



}

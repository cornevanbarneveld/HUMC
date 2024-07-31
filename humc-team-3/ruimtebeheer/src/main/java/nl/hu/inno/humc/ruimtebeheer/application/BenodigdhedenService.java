package nl.hu.inno.humc.ruimtebeheer.application;

import nl.hu.inno.humc.ruimtebeheer.api.RoosterPuntApi;
import nl.hu.inno.humc.ruimtebeheer.api.dto.RoosterpuntDTO;
import nl.hu.inno.humc.ruimtebeheer.api.dto.RoosterPuntenPeriodeDto;
import nl.hu.inno.humc.ruimtebeheer.config.RoosterConfig;
import nl.hu.inno.humc.ruimtebeheer.consumer.message.RequestBeschikbareItemsMessage;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel.Middel;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte.Ruimte;
import nl.hu.inno.humc.ruimtebeheer.producer.AccesResponseProducer;
import nl.hu.inno.humc.ruimtebeheer.producer.message.BeschikbareItemsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BenodigdhedenService {

    @Autowired
    private RoosterPuntApi roosterPuntApi;

    @Autowired
    private RuimteService ruimteService;

    @Autowired
    private MiddelService middelService;

    @Autowired
    private AccesResponseProducer accesResponseProducer;

    @Autowired
    private RoosterConfig roosterConfig;


    public void checkBenodigdheden(RequestBeschikbareItemsMessage requestBeschikbareItemsMessage) throws Exception {
        System.out.println("hier2");
        String uri = roosterConfig.getUri();
        RoosterPuntenPeriodeDto roosterpuntPeriodeDto = roosterPuntApi.getRoosterpuntenForPeriode(uri, requestBeschikbareItemsMessage.startDatumOnderzoek(), requestBeschikbareItemsMessage.eindDatumOnderzoek());
        System.out.println("hierf");
        System.out.println(roosterpuntPeriodeDto);
        List<Long> ruimteIds = new ArrayList<>();
        for (Ruimte ruimte: ruimteService.getRuimtes()) {
            ruimteIds.add(ruimte.getId());
        }
        System.out.println("hier3");

        List<Middel> middelen = middelService.getMiddelen();
        List<Long> middelIds = new ArrayList<>();
        for (Middel middel: middelen) {
            middelIds.add(middel.getId());
        }
        System.out.println("hier4");

        for (RoosterpuntDTO roosterpuntDTO: roosterpuntPeriodeDto.roosterpunten()) {
            System.out.println(roosterpuntDTO);
            //ingeroosterde ruimtes verwijderen
            ruimteIds.remove(roosterpuntDTO.ruimteNummer());
            //ingeroosterde middelen verwijderen
            for (Long middelId: roosterpuntDTO.middelIds()) {
                System.out.println("middelid:" + middelId);
                middelIds.remove(middelId);
            }
        }

        System.out.println("hier5");

        //nu alle beschikbare middelen
        middelen.removeIf(middel -> !middelIds.contains(middel.getId()));

        Map<String, Integer> beschikbareMiddelen = new HashMap<>();
        Map<String, Integer> nietBeschikbareMiddelen = new HashMap<>();

        System.out.println("hier6");
        //zet beschikbare en niet beschikbare middelen
        for (Map.Entry<String, Integer> entry : requestBeschikbareItemsMessage.benodigdeMiddelen().entrySet()) {
            String middelNaam = entry.getKey();
            Integer aantal = entry.getValue();
            int aantalBeschikbaar = this.getMiddelenByNaam(middelen, middelNaam).size();

            if (aantalBeschikbaar >= aantal) {
                beschikbareMiddelen.put(middelNaam, aantal);
            } else {
                beschikbareMiddelen.put(middelNaam, aantalBeschikbaar);
                nietBeschikbareMiddelen.put(middelNaam, (aantal - aantalBeschikbaar));
            }
        }

        System.out.println("hier7");
        //geef een beschikbare ruimte
        Long ruimteId = 0L;
        if (ruimteIds.size() > 0) {
            ruimteId = ruimteIds.get(0);
        }
        System.out.println("hier8");
        System.out.println(beschikbareMiddelen);
        System.out.println(nietBeschikbareMiddelen);
        System.out.println(ruimteId);
        BeschikbareItemsMessage beschikbareItemsMessage = new BeschikbareItemsMessage(beschikbareMiddelen, nietBeschikbareMiddelen, ruimteId, true);
        accesResponseProducer.sendMessage(beschikbareItemsMessage);
    }

    private List<Middel> getMiddelenByNaam(List<Middel> middelen, String naam) {
        middelen.removeIf(middel -> !middel.getNaam().equals(naam));
        return middelen;
    }

    public void checkBenodigdhedenFailed() {
        BeschikbareItemsMessage beschikbareItemsMessage = new BeschikbareItemsMessage(null, null, null, true);
        accesResponseProducer.sendMessage(beschikbareItemsMessage);
    }
}

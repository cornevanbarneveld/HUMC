package nl.hu.inno.humc.monoliet.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.monoliet.data.RoosterRepository;
import nl.hu.inno.humc.monoliet.domain.Laboratorium;
import nl.hu.inno.humc.monoliet.domain.Rooster;
import nl.hu.inno.humc.monoliet.domain.Roosterpunt;
import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.domain.exception.NietBeschikbaarException;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;
import nl.hu.inno.humc.monoliet.presentation.dto.RoosterDto;
import nl.hu.inno.humc.monoliet.presentation.dto.RoosterpuntDTO;
import nl.hu.inno.humc.monoliet.presentation.dto.SimpleRoosterpuntDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class RoosterService {
    private final RoosterRepository roosterRepository;
    private final OtherServicesImpl otherServices;

    public RoosterService(RoosterRepository roosterRepository, OtherServicesImpl otherServices) {
        this.roosterRepository = roosterRepository;
        this.otherServices = otherServices;
    }

    public RoosterDto createRooster() {
        Rooster rooster = new Rooster();

        this.roosterRepository.save(rooster);

        return this.convertToRoosterDto(rooster);
    }

    public RoosterDto getRooster(Long id) {
        return this.convertToRoosterDto(this.getRoosterIfNotNull(id));
    }


    public List<RoosterpuntDTO> getRoosterpuntByPeriode(LocalDateTime startDatum, LocalDateTime eindDatum) {
        List<Rooster> roosters = roosterRepository.findAll();
        List<Roosterpunt> roosterpunten = new ArrayList<>();
        for (Rooster rooster: roosters) {
            roosterpunten.addAll(rooster.getAfspraken());
        }

        ArrayList<RoosterpuntDTO> roosterpuntenDtos = new ArrayList<>();
        for (Roosterpunt roosterpunt: roosterpunten) {
            if (overlappenPeriodes(startDatum,eindDatum, roosterpunt.getBegintijd(),roosterpunt.getEindtijd())) {
                roosterpuntenDtos.add(this.convertToRoosterpuntDTO(roosterpunt));
            }
        }
        return roosterpuntenDtos;
    }

    private boolean overlappenPeriodes(LocalDateTime startDatum1, LocalDateTime eindDatum1, LocalDateTime startDatum2, LocalDateTime eindDatum2) {
        return startDatum1.isBefore(eindDatum2) && eindDatum1.isAfter(startDatum2);
    }

    public RoosterpuntDTO addRoosterpunt(Long roosterId, Long organisatorId, LocalDateTime begintijd, LocalDateTime eindtijd) throws NietBeschikbaarException {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        Medewerker organisator = this.otherServices.getMedewerkerIfNotNull(organisatorId);

        Roosterpunt roosterpunt = rooster.addRoosterpunt(organisator, begintijd, eindtijd);

        return this.convertToRoosterpuntDTO(roosterpunt);
    }

    public RoosterpuntDTO addApparaat(Long roosterId, Long id, String naam) throws NietBeschikbaarException {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        Roosterpunt roosterpunt = rooster.getAfspraakById(id);
        Apparaat apparaat = this.otherServices.getApparaatIfNotNull(naam);

        rooster.addApparaatToRoosterpunt(apparaat, roosterpunt);

        return this.convertToRoosterpuntDTO(roosterpunt);
    }

    public RoosterpuntDTO addLocatie(Long roosterId, Long id, String naam) throws NietBeschikbaarException {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        Roosterpunt roosterpunt = rooster.getAfspraakById(id);
        Laboratorium laboratorium = this.otherServices.getLaboratoriumByName(naam);

        rooster.addLocatieToRoosterpunt(laboratorium, roosterpunt);

        return this.convertToRoosterpuntDTO(roosterpunt);
    }

    public RoosterpuntDTO addBetrokkene(Long roosterId, Long id, Long medewerkersNr) throws NietBeschikbaarException {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        Roosterpunt roosterpunt = rooster.getAfspraakById(id);
        Medewerker medewerker = this.otherServices.getMedewerkerIfNotNull(medewerkersNr);

        rooster.addBetrokkeneToRoosterpunt(medewerker, roosterpunt);

        return this.convertToRoosterpuntDTO(roosterpunt);
    }

    public Rooster getRoosterIfNotNull(Long id) {
        return this.roosterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rooster not found"));
    }

    private RoosterDto convertToRoosterDto(Rooster rooster) {
        List<Long> afspraaknummers = new ArrayList<>();
        for (Roosterpunt roosterpunt : rooster.getAfspraken()){
            afspraaknummers.add(roosterpunt.getId());
        }

        return new RoosterDto(
                rooster.getId(),
                afspraaknummers
        );
    }

    private RoosterpuntDTO convertToRoosterpuntDTO(Roosterpunt roosterpunt) {
        return new RoosterpuntDTO(
                roosterpunt.getId(),
                roosterpunt.getRooster().getId(),
                roosterpunt.getOrganisator().getId(),
                roosterpunt.getBegintijd(),
                roosterpunt.getEindtijd(),
                roosterpunt.getBetrokkenen(),
                roosterpunt.getApparaten(),
                roosterpunt.getLocaties()
        );
    }

    public List<RoosterpuntDTO> getRoosterpuntenByPeriode(Long roosterId, LocalDateTime startDatum, LocalDateTime eindDatum) {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        List<Roosterpunt> roosterpunten = rooster.getAfspraken();
        List<RoosterpuntDTO> roosterpuntenInPeriode = new ArrayList<>();
        //alle periodes die overlappen met de gegeven periode
        for (Roosterpunt roosterpunt: roosterpunten) {
            if (roosterpunt.getBegintijd().isBefore(startDatum) && startDatum.isBefore(eindDatum)) {
                roosterpuntenInPeriode.add(this.convertToRoosterpuntDTO(roosterpunt));
            }
        }
        return roosterpuntenInPeriode;
    }

//    private RoosterpuntDTO convertToSimpleRoosterpuntDTO(Roosterpunt roosterpunt) {
//        List<Long> medewerkerIds = new ArrayList<>();
//        for (Medewerker medewerker: roosterpunt.getBetrokkenen()) {
//            medewerkerIds.add(medewerker.getId());
//        }
//        return new SimpleRoosterpuntDto(
//                roosterpunt.getId(),
//                roosterpunt.getRooster().getId(),
//                roosterpunt.getOrganisator().getId(),
//                roosterpunt.getBegintijd(),
//                roosterpunt.getEindtijd(),
//                roosterpunt.getBetrokkenen(),
//                roosterpunt.getApparaten(),
//                roosterpunt.getLocaties()
//        );
//    }


}

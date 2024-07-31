package nl.hu.inno.humc.roostering.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import nl.hu.inno.humc.roostering.data.RoosterRepository;
import nl.hu.inno.humc.roostering.domain.Rooster;
import nl.hu.inno.humc.roostering.domain.exception.RoosterNotFoundException;
import nl.hu.inno.humc.roostering.domain.roosterpunt.Roosterpunt;
import nl.hu.inno.humc.roostering.domain.exception.NietBeschikbaarException;
import nl.hu.inno.humc.roostering.domain.medewerker.Medewerker;
import nl.hu.inno.humc.roostering.domain.roosterpunt.RoosterpuntId;
import nl.hu.inno.humc.roostering.presentation.dto.RoosterDto;
import nl.hu.inno.humc.roostering.presentation.dto.RoosterpuntDto;
import nl.hu.inno.humc.roostering.presentation.dto.RoosterpuntDtos;
import nl.hu.inno.humc.roostering.producer.Producer;
import nl.hu.inno.humc.roostering.producer.message.MiddelMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class RoosterService {
    private final RoosterRepository roosterRepository;
    private final OtherServicesImpl otherServices;
    private final Producer producer;

    public RoosterService(RoosterRepository roosterRepository, OtherServicesImpl otherServices, Producer producer) {
        this.roosterRepository = roosterRepository;
        this.otherServices = otherServices;
        this.producer = producer;
    }

    public RoosterDto createRooster() {
        Rooster rooster = new Rooster();

        this.roosterRepository.save(rooster);

        return this.convertToRoosterDto(rooster);
    }

    public RoosterDto getRooster(String id) {
        Rooster rooster = this.getRoosterIfNotNull(id);

        return this.convertToRoosterDto(rooster);
    }

    public RoosterpuntDto addRoosterpunt(String roosterId, String beschrijving, Long ruimteNummer, Long organisatorId, LocalDateTime begintijd, LocalDateTime eindtijd) throws NietBeschikbaarException {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        Medewerker organisator = this.otherServices.getMedewerkerIfNotNull(organisatorId);
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, begintijd, eindtijd);

        Roosterpunt roosterpunt = rooster.addRoosterpunt(roosterpuntId, beschrijving, ruimteNummer);

        return this.convertToRoosterpuntDTO(roosterpunt);
    }

    public String annuleerRoosterpunt(String roosterId, Long organisatorId, LocalDateTime begintijd, LocalDateTime eindtijd) {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        Medewerker organisator = this.otherServices.getMedewerkerIfNotNull(organisatorId);
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, begintijd, eindtijd);
        Roosterpunt roosterpunt = rooster.getAfspraakByRoosterpuntIdSamenstelling(roosterpuntId);

        return rooster.annuleerRoosterpunt(roosterpunt);
    }

    public RoosterpuntDto addMiddel(String roosterId, Long middelId, Long organisatorId, LocalDateTime begintijd, LocalDateTime eindtijd) throws NietBeschikbaarException, JsonProcessingException {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        Medewerker organisator = this.otherServices.getMedewerkerIfNotNull(organisatorId);
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, begintijd, eindtijd);
        Roosterpunt roosterpunt = rooster.getAfspraakByRoosterpuntIdSamenstelling(roosterpuntId);

        rooster.addMiddelToRoosterpunt(middelId, roosterpunt);

        this.producer.verplaatsMiddel(
                new MiddelMessage(middelId, roosterpunt.getRuimteNummer(), begintijd, eindtijd));

        return this.convertToRoosterpuntDTO(roosterpunt);
    }

    public RoosterpuntDto addLocatie(String roosterId, Long ruimteNummer, Long organisatorId, LocalDateTime begintijd, LocalDateTime eindtijd) throws NietBeschikbaarException {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        Medewerker organisator = this.otherServices.getMedewerkerIfNotNull(organisatorId);
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, begintijd, eindtijd);
        Roosterpunt roosterpunt = rooster.getAfspraakByRoosterpuntIdSamenstelling(roosterpuntId);

        rooster.setRoosterpuntRuimte(ruimteNummer, roosterpunt);

        return this.convertToRoosterpuntDTO(roosterpunt);
    }

    public RoosterpuntDto addBetrokkene(String roosterId, Long medewerkersNr, Long organisatorId, LocalDateTime begintijd, LocalDateTime eindtijd) throws NietBeschikbaarException {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        Medewerker organisator = this.otherServices.getMedewerkerIfNotNull(organisatorId);
        RoosterpuntId roosterpuntId = new RoosterpuntId(organisator, begintijd, eindtijd);
        Roosterpunt roosterpunt = rooster.getAfspraakByRoosterpuntIdSamenstelling(roosterpuntId);
        Medewerker medewerker = this.otherServices.getMedewerkerIfNotNull(medewerkersNr);

        rooster.addBetrokkeneToRoosterpunt(medewerker, roosterpunt);

        return this.convertToRoosterpuntDTO(roosterpunt);
    }

    public List<RoosterpuntDto> getRoosterpuntenByLaboratorium(String roosterId, Long ruimteNummer) {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);

        List<Roosterpunt> roosterpunten = rooster.getAfsprakenVanDeRuimte(ruimteNummer);

        List<RoosterpuntDto> roosterpuntDtos = new ArrayList<>();
        roosterpunten.forEach(roosterpunt -> roosterpuntDtos.add(convertToRoosterpuntDTO(roosterpunt)));
        return roosterpuntDtos;
    }

    public RoosterDto annuleerAfsprakenVanMiddel(Long middelId) {
        Rooster rooster = this.getfirstRooster();
        rooster.verwijderMiddelIdUitAlleAfspraken(middelId);
        return this.convertToRoosterDto(rooster);
    }

    public RoosterDto getRooster(){
        return this.convertToRoosterDto(this.getfirstRooster());
    }

    public Rooster getfirstRooster(){
        List<Rooster> roosters = this.roosterRepository.findAll();
        if (roosters.isEmpty()){
            throw new RoosterNotFoundException();
        }
        return roosters.get(0);
    }

    public Rooster getRoosterIfNotNull(String id) {
        return this.roosterRepository.findById(id)
                .orElseThrow(RoosterNotFoundException::new);
    }

    private RoosterDto convertToRoosterDto(Rooster rooster) {
        List<RoosterpuntId> afspraaknummers = new ArrayList<>();
        for (Roosterpunt roosterpunt : rooster.getAfspraken()){
            afspraaknummers.add(roosterpunt.getRoosterpuntId());
        }

        return new RoosterDto(
                rooster.getId(),
                afspraaknummers
        );
    }

    private RoosterpuntDto convertToRoosterpuntDTO(Roosterpunt roosterpunt) {
        return new RoosterpuntDto(
                roosterpunt.getRoosterpuntId(),
                roosterpunt.getBeschrijving(),
                roosterpunt.getOrganisator().getId(),
                roosterpunt.getBegintijd(),
                roosterpunt.getEindtijd(),
                roosterpunt.getBetrokkenen(),
                roosterpunt.getMiddelen(),
                roosterpunt.getRuimteNummer()
        );
    }

    public RoosterpuntDtos getRoosterpuntenByPeriode(String roosterId, LocalDateTime startDatum, LocalDateTime eindDatum) {
        Rooster rooster = this.getRoosterIfNotNull(roosterId);
        List<Roosterpunt> roosterpunten = rooster.getAfspraken();
        List<RoosterpuntDto> roosterpuntenInPeriode = new ArrayList<>();
        //alle periodes die overlappen met de gegevenperiode
        for (Roosterpunt roosterpunt: roosterpunten) {
            if (roosterpunt.getBegintijd().isBefore(startDatum) && startDatum.isBefore(eindDatum)) {
                roosterpuntenInPeriode.add(this.convertToRoosterpuntDTO(roosterpunt));
            }
        }
        return new RoosterpuntDtos(roosterpuntenInPeriode);
    }

}

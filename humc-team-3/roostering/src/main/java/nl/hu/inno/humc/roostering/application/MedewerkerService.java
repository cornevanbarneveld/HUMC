package nl.hu.inno.humc.roostering.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.roostering.data.MedewerkerRepository;
import nl.hu.inno.humc.roostering.domain.exception.MedewerkerNotFoundException;
import nl.hu.inno.humc.roostering.domain.medewerker.*;
import nl.hu.inno.humc.roostering.presentation.dto.MedewerkerDto;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class MedewerkerService {
    private final MedewerkerRepository medewerkerRepository;

    public MedewerkerService(MedewerkerRepository medewerkerRepository) {
        this.medewerkerRepository = medewerkerRepository;
    }

    public MedewerkerDto registreerMedewerker(String voornaam, String initialen, String achternaam, String woonplaats, String adres, String postcode, String email, String telefoonnummer, String functie){
        Naam naamObject = new Naam(voornaam, initialen, achternaam);
        AdresGegevens adresGegevens = new AdresGegevens(woonplaats, adres, postcode);
        Email emailObject = new Email(email);
        Telefoonnummer telefoonnummerObject = new Telefoonnummer(telefoonnummer);

        Medewerker medewerker = new Medewerker(naamObject, adresGegevens, emailObject, telefoonnummerObject, functie);

        this.medewerkerRepository.save(medewerker);

        return returnMedewerkerData(medewerker);
    }

    public MedewerkerDto updateMedewerkersEmail(Long id, String email) {
        Medewerker medewerker = this.GetMedewerkerIfNotNull(id);

        Email emailObject = new Email(email);
        medewerker.setEmail(emailObject);

        this.medewerkerRepository.save(medewerker);

        return this.returnMedewerkerData(medewerker);
    }

    public MedewerkerDto getMedewerker(Long id){
        return this.returnMedewerkerData(this.GetMedewerkerIfNotNull(id));
    }

    public Medewerker GetMedewerkerIfNotNull(Long id){
        return this.medewerkerRepository
                .findById(id)
                .orElseThrow(MedewerkerNotFoundException::new);
    }

    private MedewerkerDto returnMedewerkerData(Medewerker medewerker){
        return new MedewerkerDto(
                medewerker.getId(),
                medewerker.getNaam().getVoornaam(),
                medewerker.getNaam().getInitialen(),
                medewerker.getNaam().getAchternaam(),
                medewerker.getWoonplaats(),
                medewerker.getAdres(),
                medewerker.getPostcode(),
                medewerker.getEmail(),
                medewerker.getTelefoonnummer(),
                medewerker.getFunctie().name());
    }
}

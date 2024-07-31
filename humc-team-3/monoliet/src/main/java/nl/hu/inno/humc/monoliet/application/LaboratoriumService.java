package nl.hu.inno.humc.monoliet.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.monoliet.domain.Laboratorium;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LaboratoriumService {

    @Autowired
    private MedewerkerService medewerkerService;

    @Autowired
    private RuimteService ruimteService;

    public Laboratorium createLaboratorium(String postcode,String straatnaam, int huisnummer, int ruimtenummer, int halnummer, String naam, String type) {
        Laboratorium laboratorium = new Laboratorium(postcode,straatnaam,huisnummer,ruimtenummer,halnummer,naam,type);
        ruimteService.saveRuimte(laboratorium);
        return laboratorium;
    }

    public Laboratorium getLaboratoriumById(Long id) {
        return (Laboratorium) ruimteService.getRuimteForId(id);
    }

    public Laboratorium getLaboratoriumByName(String name) {
        return (Laboratorium) ruimteService.getRuimteByNaam(name);
    }

    public Laboratorium replaceBeheerder(Long laboratoriumId, Long medewerkerId) {
        Laboratorium laboratorium = getLaboratoriumById(laboratoriumId);
        Medewerker medewerker = medewerkerService.GetMedewerkerIfNotNull(medewerkerId);
        laboratorium.setBeheerder(medewerker);
        return laboratorium;
    }
}

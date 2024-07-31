package nl.hu.inno.humc.ruimtebeheer.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.Laboratorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class LaboratoriumService {

    @Autowired
    private RuimteService ruimteService;

    public Laboratorium createLaboratorium(String postcode, String straatnaam, int huisnummer, int ruimtenummer, int halnummer, String naam, String type) {
        Laboratorium laboratorium = new Laboratorium(postcode,straatnaam,huisnummer,ruimtenummer,halnummer,naam,type);
        ruimteService.saveRuimte(laboratorium);
        return laboratorium;
    }
    public Laboratorium getLaboratoriumById(Long id) throws Exception {
        return (Laboratorium) ruimteService.getRuimteForId(id);
    }

    public Laboratorium getLaboratoriumByName(String name) {
        return (Laboratorium) ruimteService.getRuimteByNaam(name);
    }

    public Laboratorium replaceBeheerder(Long laboratoriumId, Long medewerkerId) throws Exception {
        Laboratorium laboratorium = getLaboratoriumById(laboratoriumId);
        laboratorium.setBeheerder(medewerkerId);
        return laboratorium;
    }
}

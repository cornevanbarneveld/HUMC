package nl.hu.inno.humc.monoliet.application;

import nl.hu.inno.humc.monoliet.domain.Laboratorium;
import nl.hu.inno.humc.monoliet.domain.Rooster;
import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class OtherServicesImpl implements OtherServices{
    private final MedewerkerService medewerkerService;
    private final ApparaatService apparaatService;
    private final LaboratoriumService laboratoriumService;

    public OtherServicesImpl(MedewerkerService medewerkerService, ApparaatService apparaatService, LaboratoriumService laboratoriumService) {
        this.medewerkerService = medewerkerService;
        this.apparaatService = apparaatService;
        this.laboratoriumService = laboratoriumService;
    }

    @Override
    public Medewerker getMedewerkerIfNotNull(Long id) {
        return this.medewerkerService.GetMedewerkerIfNotNull(id);
    }

    @Override
    public Apparaat getApparaatIfNotNull(String naam) {
        return this.apparaatService.getApparaatIfNotNull(naam);
    }

    @Override
    public Laboratorium getLaboratoriumByName(String naam) {
        return this.laboratoriumService.getLaboratoriumByName(naam);
    }
}

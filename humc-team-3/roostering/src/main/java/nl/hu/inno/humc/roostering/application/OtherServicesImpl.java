package nl.hu.inno.humc.roostering.application;

import nl.hu.inno.humc.roostering.api.Api;
import nl.hu.inno.humc.roostering.presentation.dto.LaboratoriumDto;
import nl.hu.inno.humc.roostering.domain.medewerker.Medewerker;
import nl.hu.inno.humc.roostering.presentation.dto.MiddelDto;
import org.springframework.stereotype.Service;

@Service
public class OtherServicesImpl implements OtherServices{
    private final MedewerkerService medewerkerService;
    private final Api Api;


    public OtherServicesImpl(MedewerkerService medewerkerService, nl.hu.inno.humc.roostering.api.Api Api) {
        this.medewerkerService = medewerkerService;
        this.Api = Api;
    }

    @Override
    public Medewerker getMedewerkerIfNotNull(Long id) {
        return this.medewerkerService.GetMedewerkerIfNotNull(id);
    }

    @Override
    public MiddelDto getMiddelDtoById(Long id) {
        return this.Api.getMiddelById(id);
    }

    @Override
    public LaboratoriumDto getLaboratoriumByName(String naam) {
        return this.Api.getLaboratoriumByName(naam);
    }
}

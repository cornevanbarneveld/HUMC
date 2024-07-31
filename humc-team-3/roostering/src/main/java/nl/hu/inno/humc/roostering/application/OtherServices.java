package nl.hu.inno.humc.roostering.application;


import nl.hu.inno.humc.roostering.presentation.dto.LaboratoriumDto;
import nl.hu.inno.humc.roostering.domain.medewerker.Medewerker;
import nl.hu.inno.humc.roostering.presentation.dto.MiddelDto;

public interface OtherServices{
    Medewerker getMedewerkerIfNotNull(Long id);
    MiddelDto getMiddelDtoById(Long id);
    LaboratoriumDto getLaboratoriumByName(String naam);
}

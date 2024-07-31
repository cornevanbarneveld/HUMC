package nl.hu.inno.humc.monoliet.presentation.mapper;

import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.presentation.dto.Apparaat.ApparaatDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApparaatMapper {

    public ApparaatDto toApparaatDto(Apparaat apparaat){
        return new ApparaatDto(apparaat.getNaam(),apparaat.isElektrisch(),apparaat.getApparaatId().getId());
    }

    public List<ApparaatDto> toApparaatDtos(List<Apparaat> apparaten){
        ArrayList<ApparaatDto> apparaatDtos = new ArrayList<>();
        for (Apparaat apparaat: apparaten) {
            apparaatDtos.add(toApparaatDto(apparaat));
        }
        return apparaatDtos;
    }
}

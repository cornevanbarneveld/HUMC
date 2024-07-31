package nl.hu.inno.humc.monoliet.presentation.mapper;

import nl.hu.inno.humc.monoliet.domain.middel.Middel;
import nl.hu.inno.humc.monoliet.presentation.dto.Middel.MiddelDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MiddelMapper {

    public MiddelDto toMiddelDto(Middel middel) {
        return new MiddelDto(middel.getNaam(), middel.getBeschrijving(), middel.getTypeGevaar(), middel.getId().getId(),
                middel.getHoudbaarheidsperiode().getRegistratieDatum(), middel.getHoudbaarheidsperiode().getHoudbaarheidsDatum());
    }

    public List<MiddelDto> toMiddelDtos(List<Middel> middelen) {
        ArrayList<MiddelDto> middelDtos = new ArrayList<>();
        for (Middel m: middelen) {
            middelDtos.add(toMiddelDto(m));
        }
        return middelDtos;
    }
}

package nl.hu.inno.humc.ruimtebeheer.presentation.mapper;

import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel.Middel;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel.MiddelDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MiddelMapper {

    public MiddelDto toMiddelDto(Middel middel) {
        return new MiddelDto(middel.getNaam(), middel.getBeschrijving(), middel.getTypeGevaar(),middel.getId(),
                middel.getHoudbaarheidsperiode().getstartDatum(), middel.getHoudbaarheidsperiode().getEindDatum());
    }

    public List<MiddelDto> toMiddelDtos(List<Middel> middelen) {
        ArrayList<MiddelDto> middelDtos = new ArrayList<>();
        for (Middel m: middelen) {
            middelDtos.add(toMiddelDto(m));
        }
        return middelDtos;
    }
}

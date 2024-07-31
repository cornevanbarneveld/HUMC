package nl.hu.inno.humc.monoliet.presentation.mapper;

import nl.hu.inno.humc.monoliet.domain.ruimte.Ruimte;
import nl.hu.inno.humc.monoliet.presentation.dto.InventarisDto;
import nl.hu.inno.humc.monoliet.presentation.dto.RuimteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RuimteMapper {

    @Autowired
    private MiddelMapper middelMapper;

    @Autowired
    private ApparaatMapper apparaatMapper;

    public RuimteDto toRuimteDto(Ruimte ruimte) {

        InventarisDto inventarisDto = new InventarisDto(apparaatMapper.toApparaatDtos(ruimte.getInventaris().getApparaten()),
                middelMapper.toMiddelDtos(ruimte.getInventaris().getMiddelen()),ruimte.getInventaris().getOverDeDatumMiddelen());
        return new RuimteDto(ruimte.getId(), ruimte.getLocatie().getPostcode().getPostcode(), ruimte.getLocatie().getStraatnaam(), ruimte.getLocatie().getHuisnummer()
                ,ruimte.getLocatie().getRuimtenummer(), ruimte.getLocatie().getHalnummer(), ruimte.getNaam(), inventarisDto );
    }



}

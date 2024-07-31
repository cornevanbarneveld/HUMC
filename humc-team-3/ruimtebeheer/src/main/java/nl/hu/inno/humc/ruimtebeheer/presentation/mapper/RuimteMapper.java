package nl.hu.inno.humc.ruimtebeheer.presentation.mapper;

import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.inventaris.MiddelVerplaatsNotification;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte.Ruimte;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.InventarisDto;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel.MiddelVerplaatsNotificationDto;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.ruimte.RuimteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RuimteMapper {

    @Autowired
    private MiddelMapper middelMapper;

    public RuimteDto toRuimteDto(Ruimte ruimte) {

        InventarisDto inventarisDto = new InventarisDto(middelMapper.toMiddelDtos(ruimte.getInventaris().getMiddelen()),ruimte.getInventaris().getOverDeDatumMiddelen()
                ,toMiddelVerplaatsNotificationDtos(ruimte.getInventaris().getTeVerplaatsenMiddelen()));
        return new RuimteDto(ruimte.getId(), ruimte.getLocatie().getPostcode().getPostcode(), ruimte.getLocatie().getStraatnaam(), ruimte.getLocatie().getHuisnummer()
                ,ruimte.getLocatie().getRuimtenummer(), ruimte.getLocatie().getHalnummer(), ruimte.getNaam(), inventarisDto );
    }

    public List<MiddelVerplaatsNotificationDto> toMiddelVerplaatsNotificationDtos(List<MiddelVerplaatsNotification> middelVerplaatsNotifications) {
        List<MiddelVerplaatsNotificationDto> middelVerplaatsNotificationDtos = new ArrayList<>();
        for (MiddelVerplaatsNotification middelVerplaatsNotification: middelVerplaatsNotifications) {
            middelVerplaatsNotificationDtos.add(toMiddelVerplaatsNotificationDto(middelVerplaatsNotification));
        }
        return middelVerplaatsNotificationDtos;
    }

    public MiddelVerplaatsNotificationDto toMiddelVerplaatsNotificationDto(MiddelVerplaatsNotification middelVerplaatsNotification) {
        return new MiddelVerplaatsNotificationDto(middelVerplaatsNotification.getMiddelId(), middelVerplaatsNotification.getRuimteIdTo(), middelVerplaatsNotification.getBeginDatum(), middelVerplaatsNotification.getEindDatum() );
    }




}

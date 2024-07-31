package nl.hu.inno.humc.ruimtebeheer.presentation.dto;

import nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel.MiddelDto;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel.MiddelVerplaatsNotificationDto;

import java.util.List;

public record InventarisDto(
        List<MiddelDto> middelen,
        List<Long> overDeDatumMiddelen,
        List<MiddelVerplaatsNotificationDto> teVerplaatsenMiddelen
) {
}

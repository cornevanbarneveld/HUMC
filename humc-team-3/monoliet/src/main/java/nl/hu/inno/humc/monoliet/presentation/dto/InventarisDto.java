package nl.hu.inno.humc.monoliet.presentation.dto;

import nl.hu.inno.humc.monoliet.domain.middel.MiddelId;
import nl.hu.inno.humc.monoliet.presentation.dto.Apparaat.ApparaatDto;
import nl.hu.inno.humc.monoliet.presentation.dto.Middel.MiddelDto;

import java.util.List;

public record InventarisDto(
        List<ApparaatDto> apparaten,
        List<MiddelDto> middelen,

        List<MiddelId> overDeDatumMiddelen
) {
}

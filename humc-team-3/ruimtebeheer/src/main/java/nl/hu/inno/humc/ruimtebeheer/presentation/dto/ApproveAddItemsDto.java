package nl.hu.inno.humc.ruimtebeheer.presentation.dto;

import java.util.List;

public record ApproveAddItemsDto(
        List<Long> approvedItems
) {
}

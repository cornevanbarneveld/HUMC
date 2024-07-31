package nl.hu.inno.humc.ruimtebeheer.presentation.dto;

import java.util.List;

public record KeurItemsDto(List<String> goedgekeurdeMiddelen,
                           List<String> afgekeurderdeMiddelen) {
}

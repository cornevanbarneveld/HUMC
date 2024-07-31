package nl.hu.inno.humc.onderzoek.presentation.dto;

import java.util.UUID;

public record OnderzoekResponseDTO (UUID uuid, String message, boolean error) {
}

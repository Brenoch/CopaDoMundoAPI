package com.fitapi.dtos.response;

import java.time.LocalDate;
import java.util.Set;

public record PartidaResponseDTO(
        Long id,
        LocalDate data,
        String estadio,
        String faseCompeticao,
        String placar,
        Set<SelecaoResponseDTO> selecoes
) {
}

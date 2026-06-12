package com.fitapi.dtos.request;

import java.time.LocalDate;
import java.util.Set;

public record PartidaRequestDTO(
        LocalDate data,
        String estadio,
        String faseCompeticao,
        String placar,
        Set<Long> selecaoIds
) {
}

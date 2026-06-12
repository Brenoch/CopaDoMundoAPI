package com.fitapi.dtos.response;

public record SelecaoResponseDTO(
        Long id,
        String nomePais,
        String tecnico,
        Integer rankingFifa
) {
}

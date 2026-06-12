package com.fitapi.dtos.request;

public record SelecaoRequestDTO(
        String nomePais,
        String tecnico,
        Integer rankingFifa
) {
}

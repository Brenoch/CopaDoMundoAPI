package com.fitapi.dtos.request;

public record JogadorRequestDTO(
        String nome,
        Integer numeroCamisa,
        String posicao,
        Integer idade,
        Long selecaoId
) {
}

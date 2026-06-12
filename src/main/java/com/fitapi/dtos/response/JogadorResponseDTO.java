package com.fitapi.dtos.response;

public record JogadorResponseDTO(
        Long id,
        String nome,
        Integer numeroCamisa,
        String posicao,
        Integer idade,
        SelecaoResponseDTO selecao
) {
}

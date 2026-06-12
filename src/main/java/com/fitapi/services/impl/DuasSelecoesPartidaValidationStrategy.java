package com.fitapi.services.impl;

import com.fitapi.dtos.request.PartidaRequestDTO;
import com.fitapi.exception.BusinessException;
import com.fitapi.services.interfaces.PartidaValidationStrategy;
import org.springframework.stereotype.Component;

@Component
public class DuasSelecoesPartidaValidationStrategy implements PartidaValidationStrategy {

    private static final int QUANTIDADE_SELECOES_POR_PARTIDA = 2;

    @Override
    public void validate(PartidaRequestDTO partidaRequest) {
        if (partidaRequest.selecaoIds() == null
                || partidaRequest.selecaoIds().size() != QUANTIDADE_SELECOES_POR_PARTIDA) {
            throw new BusinessException("Uma partida deve possuir exatamente duas selecoes participantes");
        }
    }
}

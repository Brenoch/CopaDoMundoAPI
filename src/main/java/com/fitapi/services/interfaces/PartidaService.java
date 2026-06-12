package com.fitapi.services.interfaces;

import com.fitapi.dtos.request.PartidaRequestDTO;
import com.fitapi.dtos.response.PartidaResponseDTO;

import java.util.List;

public interface PartidaService {

    List<PartidaResponseDTO> listar();

    PartidaResponseDTO buscarPorId(Long id);

    PartidaResponseDTO criar(PartidaRequestDTO partidaRequest);

    PartidaResponseDTO atualizar(Long id, PartidaRequestDTO partidaRequest);

    void remover(Long id);
}

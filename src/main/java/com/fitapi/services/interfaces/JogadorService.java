package com.fitapi.services.interfaces;

import com.fitapi.dtos.request.JogadorRequestDTO;
import com.fitapi.dtos.response.JogadorResponseDTO;

import java.util.List;

public interface JogadorService {

    List<JogadorResponseDTO> listar();

    JogadorResponseDTO buscarPorId(Long id);

    JogadorResponseDTO criar(JogadorRequestDTO jogadorRequest);

    JogadorResponseDTO atualizar(Long id, JogadorRequestDTO jogadorRequest);

    void remover(Long id);
}

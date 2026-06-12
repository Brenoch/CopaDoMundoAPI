package com.fitapi.services.interfaces;

import com.fitapi.dtos.request.SelecaoRequestDTO;
import com.fitapi.dtos.response.JogadorResponseDTO;
import com.fitapi.dtos.response.PartidaResponseDTO;
import com.fitapi.dtos.response.SelecaoResponseDTO;

import java.util.List;

public interface SelecaoService {

    List<SelecaoResponseDTO> listar();

    SelecaoResponseDTO buscarPorId(Long id);

    SelecaoResponseDTO criar(SelecaoRequestDTO selecaoRequest);

    SelecaoResponseDTO atualizar(Long id, SelecaoRequestDTO selecaoRequest);

    void remover(Long id);

    List<JogadorResponseDTO> listarJogadores(Long selecaoId);

    List<PartidaResponseDTO> listarPartidas(Long selecaoId);
}

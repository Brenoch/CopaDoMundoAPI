package com.fitapi.services.impl;

import com.fitapi.dao.interfaces.JogadorDAO;
import com.fitapi.dao.interfaces.PartidaDAO;
import com.fitapi.dao.interfaces.SelecaoDAO;
import com.fitapi.dtos.request.SelecaoRequestDTO;
import com.fitapi.dtos.response.JogadorResponseDTO;
import com.fitapi.dtos.response.PartidaResponseDTO;
import com.fitapi.dtos.response.SelecaoResponseDTO;
import com.fitapi.entities.Jogador;
import com.fitapi.entities.Partida;
import com.fitapi.entities.Selecao;
import com.fitapi.exception.ResourceNotFoundException;
import com.fitapi.services.interfaces.SelecaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SelecaoServiceImpl implements SelecaoService {

    private final SelecaoDAO selecaoDAO;
    private final JogadorDAO jogadorDAO;
    private final PartidaDAO partidaDAO;

    @Override
    public List<SelecaoResponseDTO> listar() {
        return selecaoDAO.findAll().stream()
                .map(this::toSelecaoResponse)
                .toList();
    }

    @Override
    public SelecaoResponseDTO buscarPorId(Long id) {
        return toSelecaoResponse(buscarSelecaoPorId(id));
    }

    @Override
    @Transactional
    public SelecaoResponseDTO criar(SelecaoRequestDTO selecaoRequest) {
        return toSelecaoResponse(selecaoDAO.save(toSelecaoEntity(selecaoRequest)));
    }

    @Override
    @Transactional
    public SelecaoResponseDTO atualizar(Long id, SelecaoRequestDTO selecaoRequest) {
        Selecao selecao = buscarSelecaoPorId(id);
        selecao.setNomePais(selecaoRequest.nomePais());
        selecao.setTecnico(selecaoRequest.tecnico());
        selecao.setRankingFifa(selecaoRequest.rankingFifa());
        return toSelecaoResponse(selecaoDAO.save(selecao));
    }

    @Override
    @Transactional
    public void remover(Long id) {
        Selecao selecao = buscarSelecaoPorId(id);
        selecao.getPartidas().forEach(partida -> partida.getSelecoes().remove(selecao));
        selecaoDAO.delete(selecao);
    }

    @Override
    public List<JogadorResponseDTO> listarJogadores(Long selecaoId) {
        buscarSelecaoPorId(selecaoId);
        return jogadorDAO.findBySelecaoId(selecaoId).stream()
                .map(this::toJogadorResponse)
                .toList();
    }

    @Override
    public List<PartidaResponseDTO> listarPartidas(Long selecaoId) {
        buscarSelecaoPorId(selecaoId);
        return partidaDAO.findBySelecaoId(selecaoId).stream()
                .map(this::toPartidaResponse)
                .toList();
    }

    private Selecao buscarSelecaoPorId(Long id) {
        return selecaoDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Selecao nao encontrada"));
    }

    private Selecao toSelecaoEntity(SelecaoRequestDTO selecaoRequest) {
        return Selecao.builder()
                .nomePais(selecaoRequest.nomePais())
                .tecnico(selecaoRequest.tecnico())
                .rankingFifa(selecaoRequest.rankingFifa())
                .build();
    }

    private SelecaoResponseDTO toSelecaoResponse(Selecao selecao) {
        return new SelecaoResponseDTO(
                selecao.getId(),
                selecao.getNomePais(),
                selecao.getTecnico(),
                selecao.getRankingFifa()
        );
    }

    private JogadorResponseDTO toJogadorResponse(Jogador jogador) {
        return new JogadorResponseDTO(
                jogador.getId(),
                jogador.getNome(),
                jogador.getNumeroCamisa(),
                jogador.getPosicao(),
                jogador.getIdade(),
                toSelecaoResponse(jogador.getSelecao())
        );
    }

    private PartidaResponseDTO toPartidaResponse(Partida partida) {
        return new PartidaResponseDTO(
                partida.getId(),
                partida.getData(),
                partida.getEstadio(),
                partida.getFaseCompeticao(),
                partida.getPlacar(),
                partida.getSelecoes().stream().map(this::toSelecaoResponse).collect(Collectors.toSet())
        );
    }
}

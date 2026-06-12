package com.fitapi.services.impl;

import com.fitapi.dao.interfaces.JogadorDAO;
import com.fitapi.dao.interfaces.SelecaoDAO;
import com.fitapi.dtos.request.JogadorRequestDTO;
import com.fitapi.dtos.response.JogadorResponseDTO;
import com.fitapi.dtos.response.SelecaoResponseDTO;
import com.fitapi.entities.Jogador;
import com.fitapi.entities.Selecao;
import com.fitapi.exception.BusinessException;
import com.fitapi.exception.ResourceNotFoundException;
import com.fitapi.services.interfaces.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class JogadorServiceImpl implements JogadorService {

    private final JogadorDAO jogadorDAO;
    private final SelecaoDAO selecaoDAO;

    @Override
    public List<JogadorResponseDTO> listar() {
        return jogadorDAO.findAll().stream()
                .map(this::toJogadorResponse)
                .toList();
    }

    @Override
    public JogadorResponseDTO buscarPorId(Long id) {
        return toJogadorResponse(buscarJogadorPorId(id));
    }

    @Override
    @Transactional
    public JogadorResponseDTO criar(JogadorRequestDTO jogadorRequest) {
        Jogador jogador = toJogadorEntity(jogadorRequest);
        return toJogadorResponse(jogadorDAO.save(jogador));
    }

    @Override
    @Transactional
    public JogadorResponseDTO atualizar(Long id, JogadorRequestDTO jogadorRequest) {
        Jogador jogador = buscarJogadorPorId(id);
        jogador.setNome(jogadorRequest.nome());
        jogador.setNumeroCamisa(jogadorRequest.numeroCamisa());
        jogador.setPosicao(jogadorRequest.posicao());
        jogador.setIdade(jogadorRequest.idade());
        jogador.setSelecao(buscarSelecaoPorId(jogadorRequest.selecaoId()));
        return toJogadorResponse(jogadorDAO.save(jogador));
    }

    @Override
    @Transactional
    public void remover(Long id) {
        jogadorDAO.delete(buscarJogadorPorId(id));
    }

    private Jogador buscarJogadorPorId(Long id) {
        return jogadorDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jogador nao encontrado"));
    }

    private Selecao buscarSelecaoPorId(Long id) {
        if (id == null) {
            throw new BusinessException("O campo selecaoId e obrigatorio");
        }

        return selecaoDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Selecao nao encontrada"));
    }

    private Jogador toJogadorEntity(JogadorRequestDTO jogadorRequest) {
        return Jogador.builder()
                .nome(jogadorRequest.nome())
                .numeroCamisa(jogadorRequest.numeroCamisa())
                .posicao(jogadorRequest.posicao())
                .idade(jogadorRequest.idade())
                .selecao(buscarSelecaoPorId(jogadorRequest.selecaoId()))
                .build();
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

    private SelecaoResponseDTO toSelecaoResponse(Selecao selecao) {
        return new SelecaoResponseDTO(
                selecao.getId(),
                selecao.getNomePais(),
                selecao.getTecnico(),
                selecao.getRankingFifa()
        );
    }
}

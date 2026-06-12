package com.fitapi.services.impl;

import com.fitapi.dao.interfaces.PartidaDAO;
import com.fitapi.dao.interfaces.SelecaoDAO;
import com.fitapi.dtos.request.PartidaRequestDTO;
import com.fitapi.dtos.response.PartidaResponseDTO;
import com.fitapi.dtos.response.SelecaoResponseDTO;
import com.fitapi.entities.Partida;
import com.fitapi.entities.Selecao;
import com.fitapi.exception.BusinessException;
import com.fitapi.exception.ResourceNotFoundException;
import com.fitapi.services.events.PartidaCriadaEvent;
import com.fitapi.services.interfaces.PartidaService;
import com.fitapi.services.interfaces.PartidaValidationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PartidaServiceImpl implements PartidaService {

    private final PartidaDAO partidaDAO;
    private final SelecaoDAO selecaoDAO;
    private final List<PartidaValidationStrategy> validationStrategies;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<PartidaResponseDTO> listar() {
        return partidaDAO.findAll().stream()
                .map(this::toPartidaResponse)
                .toList();
    }

    @Override
    public PartidaResponseDTO buscarPorId(Long id) {
        return toPartidaResponse(buscarPartidaPorId(id));
    }

    @Override
    @Transactional
    public PartidaResponseDTO criar(PartidaRequestDTO partidaRequest) {
        validar(partidaRequest);
        Partida partidaSalva = partidaDAO.save(toPartidaEntity(partidaRequest));
        eventPublisher.publishEvent(new PartidaCriadaEvent(partidaSalva.getId(), partidaSalva.getPlacar()));
        return toPartidaResponse(partidaSalva);
    }

    @Override
    @Transactional
    public PartidaResponseDTO atualizar(Long id, PartidaRequestDTO partidaRequest) {
        validar(partidaRequest);
        Partida partida = buscarPartidaPorId(id);
        partida.setData(partidaRequest.data());
        partida.setEstadio(partidaRequest.estadio());
        partida.setFaseCompeticao(partidaRequest.faseCompeticao());
        partida.setPlacar(partidaRequest.placar());
        partida.setSelecoes(resolveSelecoes(partidaRequest.selecaoIds()));
        return toPartidaResponse(partidaDAO.save(partida));
    }

    @Override
    @Transactional
    public void remover(Long id) {
        partidaDAO.delete(buscarPartidaPorId(id));
    }

    private void validar(PartidaRequestDTO partidaRequest) {
        validationStrategies.forEach(strategy -> strategy.validate(partidaRequest));
    }

    private Partida buscarPartidaPorId(Long id) {
        return partidaDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partida nao encontrada"));
    }

    private Partida toPartidaEntity(PartidaRequestDTO partidaRequest) {
        return Partida.builder()
                .data(partidaRequest.data())
                .estadio(partidaRequest.estadio())
                .faseCompeticao(partidaRequest.faseCompeticao())
                .placar(partidaRequest.placar())
                .selecoes(resolveSelecoes(partidaRequest.selecaoIds()))
                .build();
    }

    private Set<Selecao> resolveSelecoes(Set<Long> selecaoIds) {
        return selecaoIds.stream()
                .map(this::buscarSelecaoPorId)
                .collect(Collectors.toSet());
    }

    private Selecao buscarSelecaoPorId(Long id) {
        if (id == null) {
            throw new BusinessException("Os IDs das selecoes nao podem ser nulos");
        }

        return selecaoDAO.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Selecao nao encontrada"));
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

    private SelecaoResponseDTO toSelecaoResponse(Selecao selecao) {
        return new SelecaoResponseDTO(
                selecao.getId(),
                selecao.getNomePais(),
                selecao.getTecnico(),
                selecao.getRankingFifa()
        );
    }
}

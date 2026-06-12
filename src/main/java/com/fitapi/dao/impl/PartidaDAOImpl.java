package com.fitapi.dao.impl;

import com.fitapi.dao.interfaces.PartidaDAO;
import com.fitapi.entities.Partida;
import com.fitapi.repositories.PartidaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PartidaDAOImpl implements PartidaDAO {

    private final PartidaRepository partidaRepository;

    @Override
    public List<Partida> findAll() {
        return partidaRepository.findAll();
    }

    @Override
    public List<Partida> findBySelecaoId(Long selecaoId) {
        return partidaRepository.findBySelecoesId(selecaoId);
    }

    @Override
    public Optional<Partida> findById(Long id) {
        return partidaRepository.findById(id);
    }

    @Override
    public Partida save(Partida partida) {
        return partidaRepository.save(partida);
    }

    @Override
    public void delete(Partida partida) {
        partidaRepository.delete(partida);
    }
}

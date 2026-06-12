package com.fitapi.dao.impl;

import com.fitapi.dao.interfaces.JogadorDAO;
import com.fitapi.entities.Jogador;
import com.fitapi.repositories.JogadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JogadorDAOImpl implements JogadorDAO {

    private final JogadorRepository jogadorRepository;

    @Override
    public List<Jogador> findAll() {
        return jogadorRepository.findAll();
    }

    @Override
    public List<Jogador> findBySelecaoId(Long selecaoId) {
        return jogadorRepository.findBySelecaoId(selecaoId);
    }

    @Override
    public Optional<Jogador> findById(Long id) {
        return jogadorRepository.findById(id);
    }

    @Override
    public Jogador save(Jogador jogador) {
        return jogadorRepository.save(jogador);
    }

    @Override
    public void delete(Jogador jogador) {
        jogadorRepository.delete(jogador);
    }
}

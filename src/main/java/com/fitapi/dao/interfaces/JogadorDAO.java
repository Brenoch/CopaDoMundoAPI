package com.fitapi.dao.interfaces;

import com.fitapi.entities.Jogador;

import java.util.List;
import java.util.Optional;

public interface JogadorDAO {

    List<Jogador> findAll();

    List<Jogador> findBySelecaoId(Long selecaoId);

    Optional<Jogador> findById(Long id);

    Jogador save(Jogador jogador);

    void delete(Jogador jogador);
}

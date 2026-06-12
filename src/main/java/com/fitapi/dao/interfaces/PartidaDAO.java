package com.fitapi.dao.interfaces;

import com.fitapi.entities.Partida;

import java.util.List;
import java.util.Optional;

public interface PartidaDAO {

    List<Partida> findAll();

    List<Partida> findBySelecaoId(Long selecaoId);

    Optional<Partida> findById(Long id);

    Partida save(Partida partida);

    void delete(Partida partida);
}

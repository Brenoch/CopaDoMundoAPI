package com.fitapi.dao.interfaces;

import com.fitapi.entities.Selecao;

import java.util.List;
import java.util.Optional;

public interface SelecaoDAO {

    List<Selecao> findAll();

    Optional<Selecao> findById(Long id);

    Selecao save(Selecao selecao);

    void delete(Selecao selecao);
}

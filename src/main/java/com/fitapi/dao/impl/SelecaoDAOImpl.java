package com.fitapi.dao.impl;

import com.fitapi.dao.interfaces.SelecaoDAO;
import com.fitapi.entities.Selecao;
import com.fitapi.repositories.SelecaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SelecaoDAOImpl implements SelecaoDAO {

    private final SelecaoRepository selecaoRepository;

    @Override
    public List<Selecao> findAll() {
        return selecaoRepository.findAll();
    }

    @Override
    public Optional<Selecao> findById(Long id) {
        return selecaoRepository.findById(id);
    }

    @Override
    public Selecao save(Selecao selecao) {
        return selecaoRepository.save(selecao);
    }

    @Override
    public void delete(Selecao selecao) {
        selecaoRepository.delete(selecao);
    }
}

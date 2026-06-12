package com.fitapi.repositories;

import com.fitapi.entities.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    List<Jogador> findBySelecaoId(Long selecaoId);
}

package com.fitapi.repositories;

import com.fitapi.entities.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidaRepository extends JpaRepository<Partida, Long> {

    List<Partida> findBySelecoesId(Long selecaoId);
}

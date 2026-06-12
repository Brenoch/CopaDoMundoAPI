package com.fitapi.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "selecoes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Selecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomePais;
    private String tecnico;
    private Integer rankingFifa;

    @Builder.Default
    @OneToMany(mappedBy = "selecao", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Jogador> jogadores = new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "selecoes")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Partida> partidas = new HashSet<>();
}

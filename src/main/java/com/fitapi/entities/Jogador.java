package com.fitapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "jogadores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer numeroCamisa;
    private String posicao;
    private Integer idade;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "selecao_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Selecao selecao;
}

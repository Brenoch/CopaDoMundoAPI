CREATE DATABASE IF NOT EXISTS copa
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE copa;

CREATE TABLE IF NOT EXISTS selecoes (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome_pais VARCHAR(255),
  tecnico VARCHAR(255),
  ranking_fifa INT,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS jogadores (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(255),
  numero_camisa INT,
  posicao VARCHAR(255),
  idade INT,
  selecao_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX idx_jogadores_selecao_id (selecao_id),
  CONSTRAINT fk_jogadores_selecao
    FOREIGN KEY (selecao_id) REFERENCES selecoes (id)
    ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS partidas (
  id BIGINT NOT NULL AUTO_INCREMENT,
  data DATE,
  estadio VARCHAR(255),
  fase_competicao VARCHAR(255),
  placar VARCHAR(255),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS partida_selecao (
  partida_id BIGINT NOT NULL,
  selecao_id BIGINT NOT NULL,
  PRIMARY KEY (partida_id, selecao_id),
  INDEX idx_partida_selecao_selecao_id (selecao_id),
  CONSTRAINT fk_partida_selecao_partida
    FOREIGN KEY (partida_id) REFERENCES partidas (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_partida_selecao_selecao
    FOREIGN KEY (selecao_id) REFERENCES selecoes (id)
    ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

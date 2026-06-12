# Relatorio Tecnico - World Cup API

## Dominio

O sistema gerencia selecoes, jogadores, partidas e a participacao dos atletas em cada jogo da Copa do Mundo.

- `Selecao` possui varios `Jogador` por relacionamento `@OneToMany`.
- `Jogador` pertence a uma `Selecao` por relacionamento `@ManyToOne`.
- `Partida` possui duas `Selecao` participantes por relacionamento `@ManyToMany`.
- `ParticipacaoJogador` registra quais jogadores participaram de cada `Partida`.
- `ParticipacaoJogador` armazena dados do jogo como titularidade, minutos jogados, gols e cartoes.

## Arquitetura

O projeto foi organizado em camadas:

- `controllers`: entrada da API REST.
- `dtos`: contratos de request e response.
- `entities`: entidades JPA.
- `repositories`: Spring Data JPA.
- `dao`: acesso a dados desacoplado dos services.
- `services`: regras de negocio.
- `exception`: tratamento centralizado de erros.
- `config`: configuracoes do Swagger e listeners.

## SOLID e Clean Code

- SRP: controllers, services, DAOs e repositories possuem responsabilidades separadas.
- DIP: controllers dependem de interfaces de service; services dependem de interfaces DAO.
- OCP: validacoes de partida podem ser estendidas criando novas implementacoes de `PartidaValidationStrategy`.
- DTOs evitam expor entidades diretamente na API.
- Metodos possuem nomes claros e responsabilidades pequenas.

## Design Patterns

- DTO: separa contrato externo da API das entidades internas.
- Repository: encapsula persistencia com Spring Data JPA.
- Service Layer: centraliza regras de negocio.
- DAO: isola o acesso a dados dos services.
- Strategy: `PartidaValidationStrategy` permite adicionar novas validacoes de partida.
- Observer: `PartidaCriadaEvent` e `PartidaCriadaListener` registram evento quando uma partida e cadastrada.
- Associative Entity: `ParticipacaoJogador` modela a participacao de atletas em partidas com atributos proprios.

## Banco de Dados

O banco utilizado e MySQL. As credenciais devem ser configuradas por variaveis de ambiente:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

## Endpoints de Participacao dos Atletas

- `POST /participacoes-jogadores`: cadastra a participacao de um jogador em uma partida.
- `GET /participacoes-jogadores`: lista todas as participacoes cadastradas.
- `GET /partidas/{id}/participacoes`: lista os atletas que participaram de uma partida.

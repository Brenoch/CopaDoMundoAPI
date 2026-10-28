# World Cup API

API REST para gerenciar seleções, jogadores e partidas da Copa do Mundo.
Projeto acadêmico feito em Java com Spring Boot, com a documentação
interativa dos endpoints gerada pelo Swagger.

## Tecnologias

- Java 17
- Spring Boot 3.3.5 (Web, Data JPA, Validation)
- MySQL 8
- springdoc OpenAPI (Swagger UI)
- Maven (wrapper incluído)

## Como rodar

**Requisitos:** JDK 17 ou superior e um servidor MySQL 8.

1. Crie o banco e as tabelas com o script do repositório:

   ```bash
   mysql -u root -p < mysql-worldcup.sql
   ```

   O script cria o banco `copa`.

2. Informe a conexão por variáveis de ambiente. O valor padrão do
   `application.properties` aponta para outro banco (`fitapi`), então defina
   `DB_URL` para usar o `copa`:

   | Variável | Exemplo |
   | --- | --- |
   | `DB_URL` | `jdbc:mysql://localhost:3306/copa?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=America/Sao_Paulo` |
   | `DB_USERNAME` | `root` |
   | `DB_PASSWORD` | a senha do seu MySQL |

3. Suba a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

   No Windows, use `mvnw.cmd spring-boot:run`.

A API fica em `http://localhost:8081` e a documentação interativa em
`http://localhost:8081/swagger-ui.html`.

## Endpoints

| Recurso | Operações |
| --- | --- |
| `/selecoes` | `GET` (listar), `POST` (criar), `GET /{id}`, `PUT /{id}`, `DELETE /{id}` |
| `/selecoes/{id}/jogadores` | `GET` jogadores da seleção |
| `/selecoes/{id}/partidas` | `GET` partidas da seleção |
| `/jogadores` | `GET`, `POST`, `GET /{id}`, `PUT /{id}`, `DELETE /{id}` |
| `/partidas` | `GET`, `POST`, `GET /{id}`, `PUT /{id}`, `DELETE /{id}` |

Todos os endpoints estão documentados, com exemplos de corpo de requisição, no
Swagger UI.

## Modelo de dados

- `Selecao`: país, técnico e ranking da FIFA. Tem vários jogadores
  (`@OneToMany`).
- `Jogador`: nome, número da camisa, posição e idade. Pertence a uma seleção
  (`@ManyToOne`).
- `Partida`: data, estádio, fase da competição e placar. Relaciona-se com as
  seleções participantes por `@ManyToMany`.

**Regra de negócio:** toda partida deve ter exatamente duas seleções. Caso
contrário, a API responde com erro e uma mensagem explicando o motivo.

## Arquitetura

O projeto é organizado em camadas:

- `controllers`: entrada da API REST.
- `dtos`: contratos de request e response.
- `entities`: entidades JPA.
- `repositories`: Spring Data JPA.
- `dao`: acesso a dados desacoplado dos services.
- `services`: regras de negócio.
- `exception`: tratamento centralizado de erros.
- `config`: configuração do Swagger e listeners.

### SOLID e Clean Code

- **SRP:** controllers, services, DAOs e repositories têm responsabilidades
  separadas.
- **DIP:** controllers dependem de interfaces de service, e services dependem
  de interfaces DAO.
- **OCP:** novas validações de partida entram criando outra implementação de
  `PartidaValidationStrategy`, sem alterar o código existente.
- DTOs evitam expor as entidades diretamente na API.

### Padrões de projeto

- **DTO:** separa o contrato externo da API das entidades internas.
- **Repository:** encapsula a persistência com Spring Data JPA.
- **Service Layer:** concentra as regras de negócio.
- **DAO:** isola o acesso a dados dos services.
- **Strategy:** `PartidaValidationStrategy` permite acrescentar validações de
  partida.
- **Observer:** `PartidaCriadaEvent` e `PartidaCriadaListener` registram um
  evento sempre que uma partida é cadastrada.

## Próximos passos

- Registrar a participação dos jogadores em cada partida (titularidade,
  minutos jogados, gols e cartões).
- Adicionar testes automatizados.


# Gerenciador de Eventos - Backend

Este é o backend do projeto **Gerenciador de Eventos**, construído com **Java 21 e Spring Boot**. Ele fornece uma API RESTful para gerenciar eventos e participantes, utilizando PostgreSQL como banco de dados e suporte ao CORS para integração com o frontend.

---

## Tecnologias utilizadas

- Java 21
- Spring Boot 3+
- Spring Data JPA
- PostgreSQL
- Docker (via `compose.yml`)
- Maven
- DTOs, Services, Exceptions customizadas
  
---

## Repositórios
Backend: [GitHub - gerenciador-evento](https://github.com/dalt1x/gerenciador-evento)
Frontend: [GitHub - gerenciador-evento-front](https://github.com/dalt1x/gerenciador-evento-front)

---

## Como executar o projeto

### 1. Clone o repositório
```bash
git clone https://github.com/dalt1x/gerenciador-evento.git
cd gerenciador-evento
```

### 2. Inicie o PostgreSQL com Docker
```bash
docker compose up -d
```

O serviço ficará disponível em `localhost:5432` com:
- **Banco:** `eventos_db`
- **Usuário:** `postgres`
- **Senha:** `admin`

> Os detalhes podem ser ajustados no arquivo `compose.yml`.

---

### 3. Execute a aplicação

#### Via terminal
```bash
./mvnw spring-boot:run
```

#### Ou via IDE (IntelliJ, Eclipse etc.)
Rode a classe:
```
io.github.dalt1x.gerenciadorDeEventos.GerenciandorDeEventosApplication
```

---

## Endpoints principais

| Método | Endpoint              | Descrição                     |
|--------|------------------------|-------------------------------|
| GET    | /eventos               | Lista todos os eventos        |
| POST   | /eventos               | Cria um novo evento           |
| PUT    | /eventos/{id}          | Atualiza um evento existente  |
| DELETE | /eventos/{id}          | Remove um evento              |
| GET    | /participantes         | Lista todos os participantes  |
| POST   | /participantes         | Cadastra novo participante    |

---

## Configurações

### `application.yml`
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/eventos_db
    username: postgres
    password: admin
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

> A configuração do banco está em `src/main/resources/application.yml`.

---

## Estrutura de pacotes

```
src/main/java/io/github/dalt1x/gerenciadorDeEventos/
├── config/                # Configurações globais (CORS)
├── controller/            # Controladores REST
├── dto/                   # Objetos de transferência de dados
├── entity/                # Entidades JPA
├── exception/             # Exceções personalizadas
├── repository/            # Interfaces JPA Repository
├── service/               # Lógica de negócio
└── GerenciandorDeEventosApplication.java
```

---

## Funcionalidades

- CRUD de eventos e participantes
- Relacionamentos entre entidades
- DTOs para segurança e clareza nas respostas
- CORS liberado para integração com frontend

---


## Objetivo do Projeto

Este projeto tem fins didáticos e serve como base para integração entre backend Java e frontend simples. Ideal para aprendizado de APIs REST com Spring Boot.

---

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests com melhorias ou correções.

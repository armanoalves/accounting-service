# Contas Service

Este projeto é um serviço de backend para gerenciamento de contas a pagar, desenvolvido como desafio técnico. Ele implementa uma API RESTful completa com autenticação JWT, importação de CSV e relatórios.

## 🏗 Arquitetura

O projeto segue estritamente os princípios do **Domain-Driven Design (DDD)** e **Clean Architecture**, garantindo desacoplamento entre regras de negócio e detalhes de infraestrutura.

### Estrutura de Pacotes (`src/main/java/com/totvs/contasservice`)

*   **`domain`**: O coração do software. Contém as Entidades (`Conta`, `Usuario`) e Regras de Negócio. Não depende de nenhum framework.
*   **`application`**: Contém os Casos de Uso (`UseCases`) que orquestram a lógica de negócio. Define portas (interfaces) para comunicação com o mundo externo (`Gateways`).
*   **`infrastructure`**: Implementação técnica. Contém Controllers, Repositórios JPA, Configurações de Segurança e Mappers.
*   **`main`**: Configuração de Injeção de Dependência (`ContaConfig`), que conecta as camadas seguindo a Inversão de Dependência.

## 🚀 Tecnologias

*   **Java 17**
*   **Spring Boot 3** (Web, Data JPA, Security, Validation)
*   **PostgreSQL** (Banco de Dados)
*   **Flyway** (Migração de Banco de Dados)
*   **Docker & Docker Compose** (Containerização)
*   **JWT (JSON Web Token)** (Autenticação Stateless)
*   **Apache Commons CSV** (Processamento de arquivos)

## ⚙️ Como Executar

### Pré-requisitos
*   Docker & Docker Compose instalados.
*   OU Java 17 + Maven + PostgreSQL rodando localmente.

### Via Docker (Recomendado)
Execute o comando abaixo na raiz do projeto para subir a aplicação e o banco de dados:

```bash
docker-compose up -d --build
```

A API estará disponível em: `http://localhost:8081`

### Localmente (Maven)
1.  Garanta que um PostgreSQL esteja rodando na porta `5432` (db: `appdb`, user: `appuser`, pass: `apppassword`).
2.  Execute:
    ```bash
    ./mvnw spring-boot:run
    ```

## 📍 Endpoints Principais

### Autenticação (`/usuarios`)
*   `POST /usuarios/create`: Criar novo usuário (Requer email, senha, role).
*   `POST /usuarios/login`: Autenticar e obter Token JWT.

### Contas (`/contas`)
**Nota**: A maioria dos endpoints requer Autenticação (Header `Authorization: Bearer <token>`).

*   `POST /contas`: Cadastrar conta (Requer dataVencimento, valor, descricao, situacao).
*   `POST /contas/import`: Importar CSV `multipart/form-data` (chave: `file`).
*   `GET /contas`: Listar contas com paginação e filtros (dataVencimento, descricao).
*   `GET /contas/{id}`: Detalhes de uma conta.
*   `PUT /contas/{id}`: Atualizar conta.
*   `DELETE /contas/{id}`: Remover conta.
*   `GET /contas/total-pago`: Obter valor total pago em um período (query params: `inicio`, `fim`).

## ✅ Validações e Erros

O projeto utiliza **Bean Validation** e um **Global Exception Handler** para garantir a integridade dos dados e retornos de erro amigáveis (Status 400/404).

## 🧪 Estrutura de Pastas (Clean Arch)
```
src/main/java/com/totvs/contasservice
├── application          # Casos de Uso e Interfaces (Portas)
├── domain               # Entidades e Regras de Negócio (Nucleo)
├── infrastructure       # Controllers, Persistence, Security
└── main                 # Configuração de Beans (Cola da Arquitetura)
```

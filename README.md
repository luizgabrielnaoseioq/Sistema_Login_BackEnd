# Sistema de Login com Spring Boot

Este projeto é um sistema simples de autenticação de usuários utilizando **Java** e **Spring Boot**, com um **CRUD** básico. A aplicação permite criar, buscar, atualizar e deletar usuários no banco de dados MySQL.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 2.x**
- **MySQL 8.x**
- **JPA/Hibernate**
- **Lombok**
- **Spring Web**
- **Spring Data JPA**
- **Validation API (JSR-303)**

## Funcionalidades

- **Criação de usuários**
- **Listagem de todos os usuários**
- **Busca de um usuário por ID**
- **Atualização de dados de um usuário**
- **Deleção de usuários**

## Pré-Requisitos

Antes de rodar a aplicação, verifique se você tem os seguintes pré-requisitos instalados:

- **Java 17** ou superior
- **Maven 3.8+** (ou Gradle, se preferir)
- **MySQL** rodando localmente ou em um servidor

## Configuração do Banco de Dados

1. Crie um banco de dados no MySQL, por exemplo:

```sql
CREATE DATABASE login;
```

2. No arquivo `application.properties`, configure a conexão com o MySQL:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/login
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql: true
logging.level.org.springframework.web=DEBUG
logging.level.org.springframework.boot.autoconfigure=DEBUG
spring.mvc.static-path-pattern=/static/**
```

Substitua `suasenha` pela senha do seu usuário MySQL.

## Endpoints da API

Aqui estão os endpoints disponíveis na API do sistema de login:

1. **Criar um usuário**:
    - **Método**: `POST`
    - **Rota**: `/api/user/criar`
    - **Exemplo de requisição**:

      ```bash
      POST http://localhost:8080/api/user/criar
      Content-Type: application/json
      Body:
      {
        "email": "usuario@example.com",
        "name": "Nome do Usuário",
        "password": "senha"
      }
      ```

    - **Resposta** (Status 201 - Created):

      ```json
      {
        "email": "usuario@example.com",
        "name": "Nome do Usuário",
        "password": "senha"
      }
      ```

2. **Buscar todos os usuários**:
    - **Método**: `GET`
    - **Rota**: `/api/user/findAll`
    - **Exemplo de requisição**:

      ```bash
      GET http://localhost:8080/api/user/findAll
      ```

    - **Resposta** (Status 200 - OK):

      ```json
      [
        {
          "email": "usuario@example.com",
          "name": "Nome do Usuário",
          "password": "senha"
        },
        {
          "email": "outro@example.com",
          "name": "Outro Usuário",
          "password": "senha123"
        }
      ]
      ```

3. **Buscar usuário por ID**:
    - **Método**: `GET`
    - **Rota**: `/api/user/{id}`
    - **Exemplo de requisição**:

      ```bash
      GET http://localhost:8080/api/user/1
      ```

    - **Resposta** (Status 200 - OK):

      ```json
      {
        "email": "usuario@example.com",
        "name": "Nome do Usuário",
        "password": "senha"
      }
      ```

    - **Erro** (Status 404 - Not Found):

      ```json
      {
        "message": "Usuário não encontrado"
      }
      ```

4. **Atualizar um usuário**:
    - **Método**: `PUT`
    - **Rota**: `/api/user/update/{id}`
    - **Exemplo de requisição**:

      ```bash
      PUT http://localhost:8080/api/user/update/1
      Content-Type: application/json
      Body:
      {
        "email": "novoemail@example.com",
        "name": "Novo Nome",
        "password": "novasenha"
      }
      ```

    - **Resposta** (Status 200 - OK):

      ```json
      {
        "email": "novoemail@example.com",
        "name": "Novo Nome",
        "password": "novasenha"
      }
      ```

5. **Deletar um usuário**:
    - **Método**: `DELETE`
    - **Rota**: `/api/user/delete/{id}`
    - **Exemplo de requisição**:

      ```bash
      DELETE http://localhost:8080/api/user/delete/1
      ```

    - **Resposta** (Status 204 - No Content): Não há corpo de resposta, apenas o código de status.

## Como Rodar a Aplicação

1. Clone o repositório:

```bash
git clone https://github.com/seuusuario/login_notion.git
cd login_notion
```

2. Execute o comando para rodar a aplicação:

Se estiver usando **Maven**:

```bash
mvn spring-boot:run
```

Ou, se estiver usando **Gradle**:

```bash
gradle bootRun
```

3. A aplicação estará rodando em `http://localhost:8080`.

## Testando a API

Você pode usar ferramentas como o **Postman** ou **cURL** para testar os endpoints descritos acima.

## Contribuições

Se você deseja contribuir para o projeto, sinta-se à vontade para fazer um **fork** do repositório e enviar **pull requests**.

```

Este README.md contém todas as informações que você precisa, com os detalhes de cada endpoint da API, além de explicar como configurar e rodar o projeto, tudo no formato adequado para o GitHub.
# 📚 Microserviço - Catálogo de Biblioteca

Este projeto é um microserviço responsável por gerenciar o catálogo de uma biblioteca, permitindo o cadastro, edição, exclusão e busca de livros e autores.

### Funcionalidades do microserviço

  - 📥 Cadastro de novos livros, autores e gêneros literários;
  - 🔍 Busca de livros por título, autor e id;
  - 🔍 Busca de autor por id;
  - ✏️ Edição de dados existentes;
  - 🗑️ Remoção de livros, autores e gêneros.

## 🚀 Rodando o projeto online

|               |                                URL                                |
-------------------------------------------------------------------------------------
| Microserviço  |  https://library-microservice.onrender.com/swagger-ui/index.html  |


## 🚀 Como rodar o projeto localmente

Para clonar e executar o projeto localmente, siga os passos abaixo:

```bash

git clone <https://github.com/aninhaa08/library-microservice.git>
cd <library-microservice>

```

## Criação de branches

Esse projeto será dividido em branches de funcionalidades. Ao começar uma nova atividade, crie uma nova branch e nomeie-a como descrito na seção abaixo (Regras de nomenclatura de branches).
Para criar uma nova branch:

```bash

git checkout dev
git pull
git checkout -b nome-da-nova-branch
git push origin nome-da-nova-branch

```

## Regras de nomenclatura de branches

Nomeie sua nova branch seguindo os padrões abaixo.
Sua branch deve conter 2 partes:

1. Tipo de mudança/funcionalidade:
   - **docs**: apenas mudanças na documentação;
   - **feat**: nova funcionalidade (mais utilizado);
   - **fix**: correção de bugs;
   - **test**: adicionar ou corrigir testes;
   - **perf**: mudança de código para melhorar sua performance;
   - **refactor**: mudança de código que não adiciona uma funcionalidade e também não corrige um bug;
   - **style**: mudanças no código que não afetam seu significado (espaço em branco, formatação, ponto e vírgula, etc).

2. O que a branch faz:
   - Descreva de forma resumida e com palavras-chaves a funcionalidade da sua branch.

**Exemplo de nome de branch:**
feat-cadastro-livros

---

## 📘 Rodando o banco Postgres e a aplicação

### Requisitos 

- [Docker](https://www.docker.com/) **obrigatório**
- [DBeaver](https://dbeaver.io/)
- [Insomnia](https://insomnia.rest/) **ou** [Postman](https://www.postman.com/) 


### 1. Subir o banco PostgreSQL com Docker

1. Crie um arquivo com nome `.env` na raiz do projeto com o seguinte conteúdo:

    ```env
    DB_NAME=meubanco
    DB_USER=meuusuario
    DB_PASSWORD=minhasenha
    ```

2. Rode o banco de dados no terminal:

    ```bash
    docker-compose up -d
    ```

### 2. Rodar a aplicação no IntelliJ

1. Clique na opção de menu no canto superior esquerdo
2. Vá em  `Run > Edit Configurations`
3. Em **Environment Variables**, adicione:

    ```
    db_name=meubanco
    db_user=meuusuario
    db_password=minhasenha
    ```

---

### 3. Acessar o banco pelo DBeaver
   - Esse passo é para caso você deseje visualizar o banco de dados, suas tabelas e dados. *Não é obrigatório.*

1. Abra o DBeaver
2. Clique em `Nova Conexão > PostgreSQL`
3. Preencha os dados:

    - **Host**: `localhost`
    - **Porta**: `5432`
    - **Database**: `meubanco`
    - **User**: `meuusuario`
    - **Password**: `minhasenha`

4. Navegue e visualize o banco e suas tabelas.


## 4. Fazer requisições às rotas com Insomnia / Postman
  - Esse passo serve para validar se os endpoints (get/post/put/delete) da aplicação estão funcionando corretamente. *Não é obrigatório.*

1. Siga os passos 1 e 2 para rodar sua aplicação no IntelliJ + subir o banco no Docker
2. Clique em "Create" e crie uma coleção na ferramenta de requisições escolhida (Insomnia/Postman) caso ainda não possua uma
3. Clique em "+" para adicionar uma nova requisição
4. Selecione o tipo de requisição desejada (get/post/put/delete) e adicione a url da aplicação (localhost:8080)
5. Na frente da url, adicione a rota a ser acessada (como "/books")
6. Exemplo de requisição GET:

    ```
    GET http://localhost:8080/books
    ```

7. Para POSTs, clique em "Body" e adicione o código JSON adequado (que inclua todos os parâmetros necessários para a requisição)
8. Selecione "Send" e teste sua requisição.

---

- Banco de dados: disponível via Docker e acessível em `localhost:5432`
- Aplicação: rodando localmente via `localhost:8080`
- Requisições via Insomnia/Postman e consultas via DBeaver
- Swagger rodando em `http://localhost:8080/swagger-ui/index.html#/`
- Aplicação online rodando em `https://library-microservice.onrender.com/swagger-ui/index.html`

---

## 👥 Integrantes do Grupo e funções
- Todos os integrantes tiveram participação. Em sua maioria, os commits de cada um se concentram em suas devidas branches, nomeadas de acordo com a funcionalidade de cada uma.

| Nome Completo                            | Função/Responsabilidades                                                                                                                                                                                                                                |
|------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Ana Carolina Souto Miranda               | Estrutura da aplicação, configuração do banco de dados (Postgres), configuração do docker (imagem do banco de dados), upload da API e criação das rotas "Put Authors/:id" e "Post Authors"                                                              |
| Caio Juhasz Danjó                        | Configuração do Swagger, realização dos testes unitário, criação das rotas "Put Books/:id" e "Get Authors" e slides.                                                                                                                                      |
| Felipe Cruz da Silva Santos              | Criação das rotas "Get Books/:genre" e "Get Authors/:books"                                                                                                                                                                                             |
| Lívia Gabriela Lana Antas                | Configuração do Banco de Dados H2, configuração do docker (imagem da aplicação), criação das rotas "Delete Genres/:id", "Put Genres/:id", "Post Genres", "Get Genres/:id", "Get Genres", "Delete Genres/:id", "Delete Authors/:id", "Delete Books/:id" e slides. |
| Maria Eduarda Loreta Silva Santos        | Criação das rotas "Get Books" e "Post Books"                                                                                                                                                                                                            |
| Maria Eduarda Santana Marques            | Criação das rotas "Get Books/:id" e "Get Authors/:id"                                                                                                                                                                                                   |

## 👾 Link da apresentação de slides no Canva

https://www.canva.com/design/DAGqAe-0oAc/keGiqYdPoY-0DyUZLJuQVA/edit?utm_content=DAGqAe-0oAc&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton


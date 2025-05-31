# 📚 Microserviço - Catálogo de Biblioteca

Este projeto é um microserviço responsável por gerenciar o catálogo de uma biblioteca, permitindo o cadastro, edição, exclusão e busca de livros e autores.

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
   Descreva de forma resumida e com palavras-chaves a funcionalidade da sua branch.

**Exemplo de nome de branch:**
feat-cadastro-livros

## Funcionalidades do microserviço

  - 📥 Cadastro de novos livros e autores;
  - 🔍 Busca por título, autor e ISBN;
  - ✏️ Edição de dados existentes;
  - 🗑️ Remoção de livros e autores.
  

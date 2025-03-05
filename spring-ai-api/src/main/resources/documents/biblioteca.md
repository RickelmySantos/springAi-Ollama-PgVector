# Sistema de Gerenciamento de Biblioteca

Bem-vindo ao Sistema de Gerenciamento de Biblioteca, um espaço digital criado para facilitar a organização e o controle de livros, usuários e empréstimos. Se você gosta de ler ou administra uma biblioteca, aqui você encontra uma maneira simples e eficiente de gerenciar tudo em um só lugar.

---

## 🔍 Funcionalidades Principais

1. **Pesquisa de Livros**

   - O usuário pode buscar um livro pelo nome do livro ou pelo nome do autor.

2. **Empréstimo de Livros**

   - O usuário pode realizar o empréstimo de um livro, desde que ele não esteja atualmente emprestado por outro usuário.

3. **Top 10 Livros da Semana**

   - Exibição dos 10 livros mais emprestados da semana.

4. **Lançamentos**
   - Exibição dos livros recém-adicionados à biblioteca.

## ⚠️ Regras do Empréstimo

- Um livro só pode ser emprestado se estiver disponível.
- Caso o livro esteja emprestado, o usuário pode aguardar sua devolução ou buscar outro livro.
- O sistema pode ter um limite de tempo para devolução do livro, conforme as regras da biblioteca.

## 🤖 Como o Chatbot Pode Ajudar?

- Pesquisar livros disponíveis por título ou autor.
- Informar se um livro está disponível para empréstimo.
- Exibir os livros mais populares da semana.
- Listar os lançamentos mais recentes da biblioteca.
- Auxiliar no processo de empréstimo, caso o usuário tenha uma conta cadastrada.

## 📌 Observações

- Para realizar empréstimos, o usuário pode precisar de um cadastro no sistema.
- O chatbot pode informar disponibilidade, mas a reserva pode precisar ser feita dentro do sistema da biblioteca.

---

## Funcionalidades

### 1. Gestão de Livros:

- Adicionar, atualizar, listar e remover livros.
- Informações de cada livro:
  - Título
  - Autor
  - Gênero
  - Ano de publicação
  - Quantidade em estoque

### 2. Gestão de Autores:

- Adicionar, listar e remover autores.
- Informações de cada autor:
  - Nome
  - Nacionalidade
  - Data de nascimento

### 3. Gestão de Usuários:

- Cadastro de usuários com informações:
  - Nome
  - E-mail
  - Senha
  - Tipo de usuário (admin ou cliente)
- Login com autenticação e autorização usando Spring Security.

### 4. Controle de Empréstimos:

- Realizar empréstimos e devoluções de livros.
- Validar a disponibilidade dos livros antes do empréstimo.
- Registrar a data de empréstimo e data de devolução.

### 5. Relatórios Simples:

- Listar livros emprestados por usuário.
- Listar livros mais emprestados.

---

## Tecnologias Utilizadas

- **Spring Boot** - Framework principal para o backend.
- **Spring Data JPA** - Gerenciamento de persistência e acesso ao banco de dados.
- **Spring Security** - Implementação de autenticação e autorização.
- **H2 Database** - Banco de dados em memória para testes iniciais.
- **Maven** - Gerenciador de dependências e build.
- **JUnit e Mockito** - Testes unitários e de integração.

---

## Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- Java 17+
- Maven 3.8+
- IDE de sua preferência (IntelliJ, Eclipse, VsCode, etc.)

### Passos para Execução

1. Clone este repositório:

   ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd nome-do-repositorio
   ```

3. Execute o projeto usando Maven:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse a aplicação no navegador:
   - URL padrão: [http://localhost:8080](http://localhost:8080)

---

## Endpoints REST

### Livros:

- **GET** `/api/livros`: Listar todos os livros.
- **POST** `/api/livros`: Adicionar um novo livro.
- **PUT** `/api/livros/{id}`: Atualizar um livro existente.
- **DELETE** `/api/livros/{id}`: Remover um livro.

### Autores:

- **GET** `/api/autores`: Listar todos os autores.
- **POST** `/api/autores`: Adicionar um novo autor.
- **DELETE** `/api/autores/{id}`: Remover um autor.

### Usuários:

- **POST** `/api/usuarios`: Cadastro de usuários.
- **POST** `/api/login`: Login e autenticação.

### Empréstimos:

- **POST** `/api/Emprestimo`: Realizar um empréstimo.
- **PUT** `/api/Emprestimo/{id}/devolucao`: Realizar a devolução de um empréstimo.

---

## Melhorias Futuras

- Implementar busca com filtros (título, autor, gênero).
- Adicionar paginação e ordenação nos endpoints de listagem.
- Criar notificações para devoluções atrasadas.
- Integração com um frontend (Angular)

---

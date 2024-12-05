[![Typing SVG](https://readme-typing-svg.herokuapp.com?font=Fira+Code&pause=1000&color=2EF74D&repeat=false&width=435&lines=BACKEND+CHALLENGER+-+TRADEMAP)](https://git.io/typing-svg)

Desafio Backend para a empresa [TradeMap](https://www.linkedin.com/company/trademaphub).

# O Desafio (De cordo com a TradeMap)

"Precisamos de uma API que sirva o Back-end de um blog. Essa API precisa ser capaz de:

- Criar um Post
- Editar um Post
- Deletar um Post
- Listar os Posts de maneira paginada, permitindo filtro por data.
- Obter informações de um Post por ID.

A entidade `Post`, deve ter os seguintes campos:

- id -> Identificador do Post.
- title -> Título do Post.
- description -> Descrição do Post.
- body -> Corpo do Post.
- created_at -> Data de criação do Post.
- updated_at -> Data de atualização do Post.""

# Solução

## Ferramentas Utilizadas
- Java 17
- Spring Boot 3.x.x
- Maven
- PostgreSQL 16
- Docker
- FlyWay (Migrattions)
- Swagger (Documentação da API)

  <br>

  ## Comecei por aqui

  > O PostMap é um projeto criado por mim para a vaga back-end da TradeMap
 
  ### O Projeto pode ser executado utilizando Docker Compose  (RECOMENDADO)
  - Caso não saiba como instalar e configurar o Docker, siga a documentação: https://docs.docker.com/engine/install/

    <br>
 
  Com o Docker instalado, utilize o comando para iniciar o projeto: <br>
  > Lembre-se de navegar até a pasta que contém o docker-compose.yaml
  ```
  docker compose up -d --build
  ```
  Desta forma, provavelmente seu projeto já terá sido iniciado! <br>

  Caso queira parar o projeto, utilize: <br>
  ```
  docker compose down
  ```

  <br>

  Caso ocorra algum problema de "port already in use", o projeto PostMap utiliza a porta 8080 para executar a API e a 5432 (Padrão do PostgreSQL). <br>
  Lembre-se de fechar qualquer projeto que esteja utilizando essas portas

  ### Caso tenha erros com a porta 5432
  Utilize o comando: <br>
  ```
  sudo systemctl stop postgres
  ```
  Isso irá para o postgres do seu host, mas não se preocupe, após testar a API, poderá ativar seu postgres novamente com o comando: <br>
    ```
  sudo systemctl start postgres
  ```
  <br>
  
  ## Projeto 
  1. O Projeto PostMap seguiu os requisitos do desafio, foi utilizado o princípio SOLID para criar a arquitetura MVC com Services.
  2. Realizado o desenvolvimento do CRUD de posts (Simulando uma rede social)
  3. A aplicação foi dockerizada com Multi-staging no Dockerfile para disponibilizar uma imagem mais leve e também automatizada com Docker-compose para fácil inicialização.


  ## END-POINTS
  
  ### **Listar todos os Posts**

  - **Endpoint**: `/post`
  - **Método**: `GET`
  - **Código**: `200 OK`
  - **Descrição**: Busca todos os posts.
  - **Permite filtro por data**: O filtro por data é enviado como parâmetro na URL do recurso, <br>
  a initialDate (Data Inicial) e finalDate(dataFinal), caso não seja informado o filtro, <br>
  será retornado todos os objetos persistidos. As Datas devem ser enviadas no padrão ISO 8601, sem o timezone. (yyyy-MM-dd) <br>
  
  Disponibilizado também a personalização da quantidade de itens por página e seleção de página, que foi realizado através do Objeto Pageable do Spring  Framework. A seleção é feita via URL com os parâmetros page e size <br>
  Os dados retornados como size e page, identificam quantas páginas existes na lista de itens, qual página atual e se é ou não a primeira ou última página, facilitando assim a produção de compenentes front-end para integração. <br>

  Exemplo de URL com filtro por data:
  ```
  localhost:8080/post?initialDate=2024-01-01&finalDate=2024-01-02
  ```
  <br>
  
  Exemplo de URL sem filtro por data:
  ```
  localhost:8080/post
  ```
  <br>
  
  Exemplo de URL com filtro por data e paginção:
  ```
  localhost:8080/post?initialDate=2024-01-01&finalDate=2024-01-02&size=5&page=0
  ```
  <br>
  
    Exemplo de URL sem filtro por data e paginção:
  ```
  localhost:8080/post?size=5&page=0
  ```
  
  <br>

  Exemplo de JSON retornado

  ```
  {
    "postContent": [
    {
      "id": "b9fa43da-b2ef-4213-bafe-7069c753d6f3",
      "title": "POST TESTE",
      "description": "POST DE TESTE",
      "body": "Este é o POST de teste",
      "createdAt": "2024-12-05T15:32:41.008858Z",
      "updatedAt": "2024-12-05T15:32:41.008858Z"
    }
    ],
    "number": 0,
    "size": 5,
    "totalPages": 1,
    "totalElements": 1,
    "numberOfElements": 1,
    "last": true,
    "first": true,
    "empty": false,
    "sorted": false,
    "unsorted": true
  ```

  
   ### **Obter Post por UUID**

  - **Endpoint**: `/post/id`
  - **Método**: `GET`
  - **Código**: `200 OK`
  - **Descrição**: Busca um post por UUID.
<br>

  Exemplo de URL com busca por UUID
  ```
  localhost:8080/post/b9fa43da-b2ef-4213-bafe-7069c753d6f3
  ```
  <br>
  Exemplo de JSON Retornado
  
  ```
  {
    "id": "b9fa43da-b2ef-4213-bafe-7069c753d6f3",
    "title": "POST TESTE",
    "description": "POST DE TESTE",
    "body": "Este é o POST de teste",
    "createdAt": "2024-12-05T15:32:41.008858Z",
    "updatedAt": "2024-12-05T15:32:41.008858Z"
  }
  ```
<br>

   ### **Criar Post**

  - **Endpoint**: `/post`
  - **Método**: `POST`
  - **Código**: `201 CREATED`
  - **Descrição**: Cria um novo Post com os dados enviados <br>
    - title
    - description (descrição), não obrigatório
    - body
  - URI: Locale /post/{id}

  Exemplo de URL para criar o POST
  ```
  localhost:8080/post
  ```
  <br>
  Exemplo de JSON enviado
  
  ```
  {
  	"title": "POST DE TESTE",
    "description": "TESTE",
    "body": "ESTE É UM POST DE TESTE"
  }
  ```
  <br>
  Exemplo de JSON Retornado
  
    ```
    {
        "id": "9ca1bcd4-835e-4b47-b274-e0268fdb9ac7",
        "title": "POST PRO JUZVO",
        "description": "POST DO JUZVO",
        "body": "JUZVO VAI APRENDER JS DE VERDADE!",
        "createdAt": "2024-12-05T18:38:05.775401163Z",
        "updatedAt": "2024-12-05T18:38:05.775401163Z"
    }```

  <br>
  
  URI: `localhost:8080/post/9ca1bcd4-835e-4b47-b274-e0268fdb9ac7`
 
   <br>
   
  ### **Editar Post**

  - **Endpoint**: `/post/{id}`
  - **Método**: `PUT`
  - **Código**: `200 OK`
  - **Descrição**: Atualiza um Post, selecionando o post via UUID no URL e enviando um JSON com os campos <br>
    - title
    - description
    - body

  Exemplo de URL com UUID
  ```
  localhost:8080/post/9ca1bcd4-835e-4b47-b274-e0268fdb9ac7
  ```
  <br>
  Exemplo de JSON enviado
  
  ```
  {
  	"title": "POST DE TESTE ATUALIZADO",
    "description": "TESTE ATUALIZADO ",
    "body": "ESTE É UM POST DE TESTE ATUALIZADO"
  }
  ```
  <br>
  Exemplo de JSON Retornado
  
  ```
  {
      "id": "9ca1bcd4-835e-4b47-b274-e0268fdb9ac7",
      "title": "POST ATUALIZADO",
      "description": "POSTO DE TESTE ATUALIZADO",
      "body": "ESTE É UM POST DE TESTE ATUALIZADO",
      "createdAt": "2024-12-05T18:38:05.775401Z",
      "updatedAt": "2024-12-05T18:45:05.219877287Z"
  }
```

  ### **Deletar Post**

  - **Endpoint**: `/post/{id}`
  - **Método**: `DELETE`
  - **Código**: `200 OK`
  - **Descrição**: Apaga um Post do Banco de Dados <br>

  Exemplo de URL com UUID
  ```
  localhost:8080/post/9ca1bcd4-835e-4b47-b274-e0268fdb9ac7
  ```

  ## **Padrão de Erros Restful**

  O padrão de retorno da API segue o padrão Restful, retornando os campos: <br>
  - timestamp
  - nameStatus
  - code
  - error

  JSON de erro: <br>
  ```
{
    "timestamp": "2024-12-05T18:53:12.631344509Z",
    "name": "NOT_FOUND",
    "code": 404,
    "error": "Post not found"
}
```
  > Para os padrões de erros, é utilizado um builder para construir um objeto de resposta de erros


  
  

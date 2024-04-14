# Order Manager Application

Este é um aplicativo de processamento e consulta de pedidos desenvolvido com Java 17 e Spring Boot 3.

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3
- Banco de Dados Relacional: PostgreSQL
- Docker para construção da imagem da aplicação
- Docker Compose para rodar o banco de dados e o aplicativo localmente

## Instruções de Uso
### Pré-requisitos
- Docker instalado na máquina
### Executando a Aplicação
1. Clone o repositório do projeto:

   ```bash
      git clone https://github.com/mayc0n23/order-manager.git
   ```
2. Navegue até o diretório do projeto:

   ```bash
      cd order-manager
    ```
3. Construa a imagem da aplicação com Docker:

   ```bash
      docker build -t order-manager-app .
    ```
4. Execute o Docker Compose para rodar o banco de dados e a aplicação:

   ```bash
      docker-compose up
    ```

Agora a aplicação estará rodando localmente na porta 8080 juntamente com o banco de dados PostgreSQL.

## Documentação da API
A documentação da API pode ser acessada através do Swagger, disponível em:
- http://localhost:8080/swagger-ui/index.html#/
## Testes
Uma collection do postman com os testes da API está disponível no diretório `postman`. Importe a collection no Postman e execute os testes.
- https://github.com/mayc0n23/order-manager/tree/master/postman

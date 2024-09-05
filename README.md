# Gerenciador de clientes

# Sistema de Cadastro de Clientes - Backend

Este é o backend do sistema de cadastro de clientes, desenvolvido com Spring Boot, que oferece uma API RESTful para operações de CRUD (Criar, Ler, Atualizar e Deletar) de clientes, incluindo gestão de telefones, emails e redes sociais.

## Funcionalidades

- API para cadastrar, listar, editar e excluir clientes.
- Suporte a múltiplos telefones, emails e redes sociais por cliente.
- Paginação e ordenação de clientes.
- Validações de dados.
- Exceções customizadas para gerenciamento de erros.
- Integração com banco de dados MySQL.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para construir a API.
- **Spring Data JPA**: Para abstração de persistência de dados.
- **MySQL**: Banco de dados relacional.
- **Hibernate**: Implementação JPA para o mapeamento objeto-relacional (ORM).
- **Maven**: Gerenciador de dependências e build do projeto.
- **Spring Web**: Para criação da API RESTful.
- **Lombok**: Redução de boilerplate no código Java (getters, setters, etc.).

## Pré-requisitos

- **Java 17** ou superior.
- **Maven**.
- **MySQL**.

## Instalação

1. Clone o repositório:
   `git clone https://github.com/DiogoBatista1/Gerenciador_de_clientes.git`

2. Navegue até o diretório do projeto:
  `cd Gerenciador_de_clientes` 

3. Configure o banco de dados no arquivo application.properties:

Abra src/main/resources/application.properties e configure as propriedades de conexão com seu banco de dados MySQL:

```spring.datasource.url=jdbc:mysql://localhost:3306/clientes_db?useSSL=false
spring.datasource.username=usuario
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

4. Compile o projeto:
$ mvn clean install

5. Execute o projeto:
mvn spring-boot:run
A API estará disponível em `http://localhost:8080/api/v1/clientes`.

## Endpoints
### Clientes
- `GET /api/v1/clientes`: Lista todos os clientes (com paginação e busca).
- `GET /api/v1/clientes/{id}`: Obtém um cliente específico por ID.
- `POST /api/v1/clientes`: Cria um novo cliente.
- `PUT /api/v1/clientes/{id}`: Atualiza os dados de um cliente existente.
- `DELETE /api/v1/clientes/{id}`: Exclui um cliente.

## Paginação
Parâmetros de paginação podem ser enviados como query parameters:

`GET /api/v1/clientes?page=0&size=10`

## Pesquisa
Para buscar clientes por nome, utilize o parâmetro search:
`GET /api/v1/clientes?search=João`

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Licença
Este projeto está licenciado sob a MIT License. Consulte o arquivo [LICENSE](https://github.com/DiogoBatista1/Gerenciador_de_clientes/blob/main/LICENSE) para mais detalhes.

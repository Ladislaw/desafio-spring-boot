# desafio-spring-boot

Projeto feito para criar uma API Rest utilizando o Spring boot.

Stack: Java 8, Spring Boot, Spring Web, Spring Data, H2database, Lombok, ModelMapper, JUnit, Swagger.

Link da Documentação: localhost:9090/api/v1/swagger-ui.html

Base Url da API: localhost:9090/api/v1

Ações:

Cadastrar Cidade;
Cadastrar Cliente;
Buscar Cidade Por Nome;
Buscar Cidade Por Estado;
Buscar Cliente Por Nome;
Buscar Cliente Por Id;
Alterar Cliente;
Excluir Cliente;

Objeto Cidade:
```json
{
	"nome":"Salvador",
	"estado":"Bahia"
}
```

Objeto Cliente:
```json
{
	"nome":"Rodrigo",
	"sobrenome":"Silva",
	"sexo":"M",
	"dtNascimento":"19/07/1996",
	"idade":19,
	"cidade":"Salvador"
}
```

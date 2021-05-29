imagem
<h1 align="center">
    <img alt="Upload" src="https://github.com/JeffersonLuizCruz/api-upload/blob/main/src/main/resources/templates/upload.jpg" />
</h1>

<h3 align="center">
  REST API Upload e Download - BackEnd - Spring Boot
</h3>

<p align="center">Manipulando Imagem</p>

![GitHub repo size](https://img.shields.io/github/repo-size/JeffersonLuizCruz/financial)  ![Packagist License](https://img.shields.io/packagist/l/JeffersonLuizCruz/financial)  ![GitHub top language](https://img.shields.io/github/languages/top/JeffersonLuizCruz/financial)  ![GitHub language count](https://img.shields.io/github/languages/count/JeffersonLuizCruz/financial?label=Linguagem%20de%20Programa%C3%A7%C3%A3o)  ![GitHub followers](https://img.shields.io/github/followers/JeffersonLuizCruz?style=social)

<p align="center">
  <a href="#-sobre">Sobre o projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-diagrama">Diagrama de Classe</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-links">Links</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-contato">Contato</a>
</p>


## Tecnologia:
- [x] Java 8<br>
- [x] Spring Boot 2.4.5<br>
- [x] Spring Data - JPA/Hibernate<br>
- [x] Banco de Dados PostgreSQL<br>
- [x] Swagger2


## Construção do Projeto:
- [x] Criação de Interface Service (garantir baixo acoplamento)<br>
- [x] CRUD (ORM Hibernate - Ambiente de teste)<br>
- [x] Exception Personalizada
- [x] Consulta e Busca Paginada Inteligente
- [x] Serviço de Email


## Start do Projeto Local
```
server.error.include-stacktrace=never
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/test
spring.datasource.username=postgres
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.jackson.default-property-inclusion= non-null

contato.disco.raiz=/tmp/contato-disco/
contato.disco.diretorio-fotos=fotos

spring.servlet.multipart.enabled=true
spring.servlet.multipart.file-size-threshold=2KB
spring.servlet.multipart.max-file-size=200MB
spring.servlet.multipart.max-request-size=200MB

```
### [POST] save     | status 200 OK
<h1 align="center">
    <img src="https://github.com/JeffersonLuizCruz/api-upload/blob/main/src/main/resources/templates/POST-01.png" />
</h1>

### [POST] save     | status 400 BAD REQUEST
<h1 align="center">
    <img src="https://github.com/JeffersonLuizCruz/api-upload/blob/main/src/main/resources/templates/POST-ERROR-02.png" />
</h1>

### [GET] findById  | status 200 OK
<h1 align="center">
    <img src="https://github.com/JeffersonLuizCruz/api-upload/blob/main/src/main/resources/templates/GET-FIND-03.png" />
</h1>

### [GET] findById  | status 404 NOT FOUND
<h1 align="center">
    <img src="https://github.com/JeffersonLuizCruz/api-upload/blob/main/src/main/resources/templates/GET-FIND-ERROR-04.png" />
</h1>

### [GET] listAll   | status  200 OK | CONSULTA PAGINADA
<h1 align="center">
    <img src="https://github.com/JeffersonLuizCruz/api-upload/blob/main/src/main/resources/templates/GET-PAGE-05.png" />
</h1>

### [GET] listAll   | status  200 OK | CONSULTA PAGINADA E CAMPO DE BUSCA
<h1 align="center">
    <img src="https://github.com/JeffersonLuizCruz/api-upload/blob/main/src/main/resources/templates/GET-SEARCH-06.png" />
</h1>

### [GET] download   | status  200 OK | DOWNLOAD
<h1 align="center">
    <img src="https://github.com/JeffersonLuizCruz/api-upload/blob/main/src/main/resources/templates/GET-DON-07.png" />
</h1>

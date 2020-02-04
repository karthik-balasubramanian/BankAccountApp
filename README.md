# Bank Account App

This is a coding exercise requested from ANZ Wholesale Engineering team.

This project is created using <b>Java 8</b> and <b>SpringBoot</b>. Persistence is implemented using <b>H2</b> in memory database.

The APIs enables user to <b>GET</b> Accounts and <b>GET</b> Transactions for an Account. Other operations have been omitted for simplicity sake.

## Development server

To run project, go to BankAccountApplication.java and run as SpringBoot Application if using Eclipse/Intellij. To run from command prompt, execute `mvn spring-boot:run` from the prompt. 

Upon server start up, new Accounts and Transactions will be loaded from data.sql (H2 in memory database) located in src/main/resources.

APIs can be accessed via http://localhost:8080
Example:

1) <b>GET</b>
`localhost:8080/accounts/`

2) <b>GET</b> 
`localhost:8080/accounts/9467563426/transactions`

## Swagger Documentation

To view Swagger Documentation to see all APIs, go to 
http://localhost:8080/swagger-ui.html#/ 

## Unit Tests

To run Unit Test, go to the corresponding test under src/test.java and run as JUnit Test. 
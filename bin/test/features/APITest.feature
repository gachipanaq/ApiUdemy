Feature: Ejemplo de Request para Udemy

@API
Scenario: Prueba GET al endpoint
Given I send a GET request to the endpoint
Then I get a list of 10 users

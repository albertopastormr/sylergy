Feature: Sumar dos numeros

  Scenario: Inicio de la aplicacion
    Given Dos campos vacíos numéricos
    When Se introducen <a>, <b>
    And y se pulsa enviar
    Then Se muestra el resultado de la suma en un diálogo

  Examples:
  | a | b |
  | 1 | 2 |
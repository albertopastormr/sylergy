Feature: Sumar dos numeros

  Scenario Outline: Inicio de la aplicacion
    Given Dos campos vacíos numéricos
    When Se introducen "<a>" y "<b>"
    And y se pulsa enviar
    Then Se muestra el "<resultado>" de la suma en un diálogo

  Examples:
  | a | b | resultado |
  | 1 | 3 |     4     |
  | 1 | 4 |     5     |
  | 1 | 6 |     7     |

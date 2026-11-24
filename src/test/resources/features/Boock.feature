Feature: Gestión de reservas
  Como consumidor de la API de reservas
  Quiero consultar y crear reservas
  Para validar que el servicio responde correctamente

  Background:
    Given que el servicio de reservas está disponible

  Scenario: Obtener todas las reservas
    When consulto todas las reservas
    Then el código de respuesta debe ser 200
    And la lista de reservas no debe estar vacía

  Scenario Outline: Crear una reserva
    When creo una reserva a nombre de "<nombre>" "<apellido>" con precio <precio> <deposito>
    Then el código de respuesta debe ser 200
    And el campo "booking.firstname" debe ser "<nombre>"
    And el campo "booking.totalprice" debe ser <precio>

    Examples:
      | nombre | apellido | precio | deposito     |
      | Ana    | Torres   | 150    | con depósito |
      | Luis   | Pérez    | 300    | sin depósito |

  Scenario: Consultar una reserva existente
    Given que existe una reserva a nombre de "Carla" "Ruiz"
    When consulto la reserva creada
    Then el código de respuesta debe ser 200
    And el campo "firstname" debe ser "Carla"
    And el campo "lastname" debe ser "Ruiz"

  Scenario: Consultar una reserva inexistente
    When consulto la reserva con id 999999999
    Then el código de respuesta debe ser 404
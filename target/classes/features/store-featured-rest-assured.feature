@store
Feature: Validate store

  @orderPost
  Scenario Outline: Validate POST add order
    Given POST request to add a order
    Then the status code response - POST order request
    And POST order request body response contains the "<orderId>" and "<petId>"

    Examples:
      | orderId | petId |
      | 101101  | 101   |

  @orderGetById
  Scenario Outline: Validate GET order by ID
    Given the following GET request returns order pet by id
    Then the status code is 200 - GET order request by id
    And GET order by id request body response contains "<orderId>" and "<petId>"

    Examples:
      | orderId | petId |
      | 101101  | 101   |

  @orderDelete
  Scenario Outline: Validate DELETE order
    Given DELETE request delete order by id
    Then the status code is 200 - DELETE order request by id
    And DELETE order request by id body response contains the "<orderId>"

    Examples:
      | orderId |
      | 101101  |

  @inventoryGet
  Scenario: Validate GET order inventory
    Given the following GET request returns order inventory
    Then the status code is 200 - GET inventory request
    And GET order inventory body response request is not empty

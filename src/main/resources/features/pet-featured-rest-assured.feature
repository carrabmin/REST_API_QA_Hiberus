@pet
Feature: (e2e) Validate pets

  @petPost
  Scenario Outline: Validate POST add pet
    Given POST request to add pet
    Then the status code response - POST pet request
    And POST request body response contains the id "<id>" and name "<name>"
    Examples:
      | id  | name   |
      | 101 | maikel |

  @petGetByID
  Scenario Outline: Validate GET pet by ID
    Given the following GET request returns pet by id
    Then the status code is 200 - GET pet request by id
    And GET pet by id request body response contains "<id>" and "<name>"

    Examples:
      | id  | name   |
      | 101 | maikel |

  @petPut
  Scenario Outline: Validate PUT update pet
    Given PUT request update a pet
    Then the status code is 200 - PUT pet request
    And PUT pet request body response contains id "<id>" and name "<name>"

    Examples:
      | id  | name   |
      | 101 | faisek |

  @petDelete
  Scenario Outline: Validate DELETE pet
    Given DELETE request deletes pet by id
    Then   the status code is 200 - DELETE pet request by id
    And  DELETE pet request by id body response contains the id "<id>"

    Examples:
      | id  |
      | 101 |








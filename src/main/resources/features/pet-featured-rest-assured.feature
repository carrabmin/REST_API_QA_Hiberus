Feature: (e2e) Validate pets

  @pets
  Scenario: (e2e) Validate that the photoUrls is correct



  @photoUrl
  Scenario Outline: (e2e) Validate that the response that a pet contains the corresponding photoUrl
    Given the following get request that brings us the pet by id
    And the response is 200
    And the id is <id>
    And the body response contains category name "<categoryName>"
    And the body response contains name "<name>"
    Then the body reponse contains photoUrl "<photoUrl>"

    Examples:
    |id    |categoryName|name  |photoUrl              |
    |102023|gato        |maikel|https://www.gatos.com/|







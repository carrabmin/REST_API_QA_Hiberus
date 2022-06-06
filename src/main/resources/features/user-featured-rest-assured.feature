@user
Feature: Validate users

  @postUserWithArray
  Scenario: Validate POST create user with array
    Given POST user request to add user with array
    Then the status code response - POST user request with array
    And POST user request with array contains ok in the message

  @postUserWithList
  Scenario: Validate POST create user with list
    Given POST user request with list
    Then the status code response - POST user request with list
    And POST request with list body response contains ok in the message

  @getUserByName
  Scenario Outline: Validate GET user by name
    Given GET user request by name
    Then the status code is 200 - GET user request by name
    And GET user by id request body response contains name "<userName>"

    Examples:
      | userName |
      | Carlos   |

  @putUser
  Scenario Outline: Validate PUT update user
    Given PUT request update a user
    Then the status code is 200 - PUT user request
    And PUT request body response contains "<userId>" when user is updated

    Examples:
      | userId |
      | 1023   |

  @deleteUserByName
  Scenario Outline: Validate DELETE user
    Given DELETE request deletes pet by name
    Then the status code is 200 - DELETE pet request by name
    And DELETE user request by name body response contains the message "<name>"

    Examples:
      | name   |
      | Carlos |

  @getUserLogin
  Scenario: Validate get user login
    Given GET request user logging
    Then the status code is 200 - GET user logging request
    And GET logging user body response request contains contains 'logged in user session' in the message

  @getUserLogout
  Scenario: Validate get user log out
    Given the following GET request logout the user
    Then  the status code is 200 - GET user logout request
    And  GET logout user request body response contains ok in the message




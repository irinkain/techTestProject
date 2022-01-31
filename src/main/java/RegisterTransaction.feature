Feature: Testing Service which registers transactions for user
  Scenario: User clicks on spin button and starts registering transaction
    Given BET is registered
    When User wins
    Then Win request is received
    And Check session validation

  Scenario: SessionID is valid
    Given User’s session exists in system
    When User starts transaction
    Then Check if Bet request exists to win
    And Check win transaction

  Scenario: SessionID is not valid
    Given User’s session does not exist in system or its not valid
    When User starts transaction
    Then Register new deposit transaction
    And Return success code 10

  Scenario: Win transaction exists
    Given WIN is registered in the system
    When User win
    Then Return success code 10

  Scenario: User clicks on spin button and starts registering transaction incorrectly
    Given BET is not registered
    When User does not win
    Then Bet request does not exist
    And Check status code and error message

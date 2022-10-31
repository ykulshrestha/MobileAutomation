Feature: Verify Seller Chat Inbox

  @Test1
  Scenario: Verify presence of Floating chat cta
    Given User login on seller app
    And User lands on <tab> with <service> selected
    When User clicks on chat floating cta
    Then Chat Inbox Opens
    And Chat card for the user is visible


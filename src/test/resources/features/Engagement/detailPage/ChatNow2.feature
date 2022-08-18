Feature: Verify Chat Now

  @Test1
  Scenario: Logged in user successfully intiated chat
    Given User is on Detail Page
    When User click on Chat Now
    Then Chat screen is visible to user

  @Test1
  Scenario: Logged out user successfully intiated chat through login modal
    Given User is on Detail Page aa
    When User click on Chat Now aa
    Then Chat screen is visible to user aa



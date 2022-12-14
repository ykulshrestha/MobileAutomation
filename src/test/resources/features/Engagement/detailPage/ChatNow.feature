Feature: Verify Chat Now

  @Test1
  Scenario: Logged in user successfully intiated chat
    Given Logged in User is on Detail Page
    When User click on Chat Now
    Then Chat screen is visible to user

  @Test2
  Scenario: Logged out user successfully intiated chat through login modal
    Given User is on Detail Page
    When User click on Chat Now
    And User login through login modal
    Then Chat screen is visible to user

  @Test3
  Scenario: Logged out user successfully intiated chat through crf form
    Given User is on Detail Page
    When User click on Chat Now
    And User drop off login modal
    And User fill crf form
    And User enter otp
    And user click on contact
    Then Chat screen is visible to user

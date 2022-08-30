Feature: Verify Login flow

  @Test1
  Scenario: Login failed due to wrong password
    Given User opens login modal on profile page
    When User login by using number and password
    Then Login failed with toast


Feature: Buyer is able to send message

  @Test1
  Scenario: Buyer sends message to seller
    Given Logged in user is on Detail Page
    When Buyer click on Chat Now
    Then Buyer sends message to seller
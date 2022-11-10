Feature: Verify Chat Notification on Buyer side

  @Test2
  Scenario Outline: Buyer sends chat notification to seller
    Given Buyer completed onboarding for "<city>"
    And Buyer is on detail Page of "<service>" service of "<locality>"
    When Buyer click on Chat Now
    And Buyer logged in using login modal with "<number>" and "<password>"
    And Buyer sends message to seller
    Examples:
      |number|password|city|service|locality|
      |7317140612|12345|Ajmer|buy|Makadwali|


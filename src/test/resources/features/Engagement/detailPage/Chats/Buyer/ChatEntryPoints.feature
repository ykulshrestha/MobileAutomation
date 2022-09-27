Feature: Verify Buyer Side Chat Entry Points

  @Test1
  Scenario Outline: Logged out user successfully intiated chat from chat header icon using login via login modal
    Given Buyer is on detail Page of "<locality>" in "<city>"
    When Buyer click on chat header icon
    And Buyer logged in using login modal with "<Number>" and "<password>"
    Then Chat screen is visible to Buyer
    And sellerName should be visible on chat inbox
    And Property Name should be visible
    And Buyer sends message to seller

   Examples:
    |Number|password|city|locality|
    |9643930232|12345|Ajmer|Makadwali|

  @Test2
  Scenario Outline: Logged out user successfully intiated from Chat Now CTA using crf form
    Given Buyer is on detail Page of "<locality>" in "<city>"
    When Buyer click on Chat Now
    And Buyer login using crf form with "<Number>", "<Name>" and "<email>"
    Then Chat screen is visible to Buyer
    And Buyer sends message to seller

    Examples:
      |Number|Name|email|city|locality|
      |9871739261|Test|Test@gmail.com|Ajmer|Makadwali|

  Scenario: Logged in user successfully intiated chat from Chat for details CTA
    Given Logged in Buyer is on detail Page
    When Buyer click on Chat for details
    Then Chat screen is visible to Buyer
    And Buyer sends message to seller

  Scenario: Logged in user successfully intiated chat from Chat bottom floating cta
    Given Logged in Buyer is on detail Page
    When Buyer click on chat from bottom floating cta
    And Buyer clicks on housing chat from bottom tray
    Then Chat screen is visible to Buyer
    And Buyer sends message to seller

  Scenario: Logged in user successfully intiated chat from profile page
    Given Logged in Buyer is on Profile Page
    When Buyer click on chat on fav cards
    Then Chat screen is visible to Buyer
    And Buyer sends message to seller
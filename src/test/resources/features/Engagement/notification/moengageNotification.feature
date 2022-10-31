Feature: Verify Chat Inbox Moengage Notification

  @Test1
  Scenario Outline: Buyer successfully opened notification and lands to desired page
#    Given User logged in with "<Number>" and "<password>" onboarded for "<city>"
    And Chat Inbox notification is sent on "<Number>" and "<title>"
    When User clicks on notification having "<title>"
    Then Chat Inbox should be visible
    Examples:
      |Number|password|city|title|
      |7317140612|12345|Ajmer|Test|

  @Test2
  Scenario Outline: Seller successfully opened notification and lands to desired page
    Given seller logged in using "<number>" and "<password>" on seller App
    And Chat Inbox notification is sent on "<number>" and "<title>"
    When User clicks on notification having "<title>"
    Then Chat Inbox should be visible
    Examples:
      |number|password|title|
      |6111111111|housing@1234|title|

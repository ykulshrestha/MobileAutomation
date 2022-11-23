Feature: Verify Chat Inbox Moengage Notification

  @Test1
  Scenario Outline: Buyer successfully opened notification and lands to desired page
#    Given User logged in with "<Number>" and "<password>" onboarded for "<city>"
    And Chat Inbox notification is sent on "<Number>" and "<title>"
    When User clicks on notification having "<title>"
    Then Chat Inbox of "<ChatUserEnumName>" for "<Role>" role should be visible
    Examples:
      |Number|password|city|title|ChatUserEnumName|Role|
      |7317140612|12345|Ajmer|Test|SELLERONE|BUYER|

  @Test1
  Scenario Outline: Seller successfully opened notification and lands to desired page
#    Given seller logged in using "<number>" and "<password>" on seller App
    And Chat Inbox notification is sent on "<number>" and "<title>"
    When User clicks on notification having "<title>"
    Then Chat Inbox of "<ChatUserEnumName>" for "<Role>" role should be visible
    Examples:
      |number|password|title|ChatUserEnumName|Role|
      |6222222222|housing@1234|Test|PRABAL_SELLER|SELLER|

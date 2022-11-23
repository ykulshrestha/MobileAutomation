Feature: Verify Buyer Chat Inbox

  @Test1
  Scenario Outline: Verify presence of Floating chat cta
    Given User logged in with "<number>" and "<password>" onboarded for "<city>"
    When Buyer selects service "<service>"
    Then User clicks on chat floating cta
    And User should land on desired chat inbox of "<ChatUserEnumName>" for "<Role>"
    Examples:
      |number|password|city|service|ChatUserEnumName|Role|
      |7317140612|12345|Ajmer|buy|SELLERONE|BUYER|
      |7317140612|12345|Ajmer|rent|SELLERONE|BUYER|
      |7317140612|12345|Ajmer|plot|SELLERONE|BUYER|

#TODO: Test once chat floating cta is added
  @Test2
  Scenario Outline: Verify absence of Floating chat cta
    Given User logged in with "<number>" and "<password>" onboarded for "<city>"
    When Buyer selects service "<service>"
    And Chat floating cta should be absent
    Examples:
      |number|password|city|service|
      |7838246862|12345|Ajmer|commercial|
      |7838246862|12345|Ajmer|pg|

  @Test3
  Scenario Outline: Verify chat Inbox from buyer profile
    Given User logged in with "<number>" and "<password>" onboarded for "<city>"
    And Buyer is on profile page
    Then Buyer clicks on My Chats
    And User should land on desired chat inbox of "<ChatUserEnumName>" for "<Role>"
    Examples:
      |number|password|city|ChatUserEnumName|Role|
      |7317140612|12345|Ajmer|SELLERONE|BUYER|


Feature: Verify Chat Inbox InApp Notification

#  TODO: Bundle ID of housing app
  @inapp
  Scenario Outline: Buyer successfully opened notification and lands to desired page
    Given User logged in with "<Number>" and "<password>" onboarded for "<city>"
    And User Kills the app
    When User opens the app again
    And User clicks on MyChats button
    Then Chat Inbox of "<ChatUserEnumName>" for "<Role>" role should be visible
    Examples:
      |Number|password|city|ChatUserEnumName|Role|
      |7317141087|12345|Ajmer|PRABAL_SELLER|SELLER|

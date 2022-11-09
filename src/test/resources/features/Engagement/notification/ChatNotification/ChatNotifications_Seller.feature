Feature: Verify Chat Notification on seller side

  @Test2
  Scenario Outline: Seller receives notification from buyer
    Given seller logged in using "<number>" and "<password>" on seller App
    And Seller waits for chat notification from "<title>"
    When User clicks on notification having "<title>"
    Then Chat screen is visible to User for "<ChatUserEnumName>"

    Examples:
      |number|password|title|ChatUserEnumName|
      |6111111111|housing@1234|Sellerone|RAMAN_SELLER|


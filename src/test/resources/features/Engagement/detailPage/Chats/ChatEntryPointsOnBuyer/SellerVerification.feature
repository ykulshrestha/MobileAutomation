Feature: Seller is able to recieve messages

  @Test1
  Scenario Outline: Seller recieves message from buyer
    Given seller logged in using "<number>" and "<password>" on seller App
    When seller opens chat inbox
    Then "<Buyer>" message should be visible on chat inbox and chat thread
    And Seller replies back

    Examples:
      |number|password|Buyer|
      |6111111111|housing@1234|Sellerone|
      |8976857102|12345|Manthan Patel|
      |6111111111|housing@1234|Testnewuser|
      |6111111111|housing@1234|Testnewuser|
      |6111111111|housing@1234|Testnewuser|
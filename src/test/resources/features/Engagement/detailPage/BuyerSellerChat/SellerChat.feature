Feature: Seller is able to recieve messages

  @Test1
  Scenario: Seller recieves message from buyer
    Given Seller is logged in on seller App
    When seller opens chat inbox
    Then Buyer message should be visible
    And Seller replies back
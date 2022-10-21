Feature: Verify Buyer Side Chat Entry Points

  @Test1
  Scenario Outline: Logged out user successfully intiated chat from chat header icon using login via login modal
    Given Buyer completed onboarding for "<city>"
    And Buyer is on detail Page of "<service>" service of "<locality>"
    When Buyer click on chat header icon
    And Buyer logged in using login modal with "<Number>" and "<password>"
    Then Chat screen is visible to Buyer
    And sellerName should be visible on chat inbox
    And Property Name should be visible
    And Buyer sends message to seller

    #Seller= Raman Test (6111111111)
   Examples:
    |Number|password|city|locality|service
    |7317140612|12345|Ajmer|Makadwali|buy|


  @Test2
  Scenario Outline: Logged out user successfully intiated from Chat Now CTA using crf form
    Given Buyer completed onboarding for "<city>"
    And Buyer is on detail Page of "<service>" service of "<locality>"
    When Buyer click on Chat Now
    And Buyer login using crf form with "<Number>", "<Name>" and "<email>"
    Then Chat screen is visible to Buyer
    And Buyer sends message to seller

    #Seller= TestSeller(8976857102)
    Examples:
      |Number|Name|email|city|locality|service|
      |9871739261|Test|Test@gmail.com|Ajmer|Ghooghra|buy|

  @Test3
  Scenario Outline: Logged in user successfully intiated chat from Chat for details CTA
    Given User logged in with "<number>" and "<password>" onboarded for "<city>"
    And Buyer is on detail Page of "<service>" service of "<locality>"
    When Buyer click on Chat for details
    Then Chat screen is visible to Buyer
    And Buyer sends message to seller

        #Seller= Raman Test (6111111111) - Rent
    Examples:
      |number|password|city|locality|service|
      |9643930232|12345|Ajmer|Railway Quarters|rent|

  @Test4
  Scenario Outline: Logged in user successfully intiated chat from Chat bottom floating cta
    Given User logged in with "<number>" and "<password>" onboarded for "<city>"
    And Buyer is on detail Page of "<service>" service of "<locality>"
    When Buyer clicks on housing chat from bottom tray
    Then Chat screen is visible to Buyer
    And Buyer sends message to seller

            #Seller= Raman Test (6111111111) - Rent
    Examples:
      |number|password|city|locality|service|
      |9643930232|12345|Lonavala|Bhangarwadi|rent|

  @Test5
  Scenario Outline: Logged in user successfully intiated chat from profile page
    Given User logged in with "<number>" and "<password>" onboarded for "<city>"
    And Buyer is on detail Page of "<service>" service of "<locality>"
    When Buyer navigates back to profile page and click on chat of "<profile Section>"
    Then Chat screen is visible to Buyer
    And Buyer sends message to seller

                #Seller= Raman Test (6111111111) - Rent
    Examples:
      |number|password|city|locality|profile Section|service|
      |9643930232|12345|Hydrabad|kothapet|seen        |rent|
Feature: Validate existance of chat discovery points on various details page

  Scenario Outline: Validate presence of chat discovery points on resale properties
    Given Buyer completed onboarding for "<city>"
    And Buyer is on detail Page of "<service>" service of "<locality>"
    Then Chat entry points should be visible

    #Validate all types of detail page-
    #Buy- Owner properties- ghoogra- ajmer-1RK

    #Buy- Seller properties- Panchsheel greens 2- Noida
    #Buy- developer + Seller properties- Apartment in kishanggarh (3 BHK)

    #Buy- Agent properties- 1 RK-> makadwali-> ajmer
    #Rent- Owner properties- gegal - ajmer-1RK
    #Rent- Agent properties- ajmer-> railway quarter->1 RK
    Examples:
      |city|service|locality|
      |Ajmer|buy|ghooghra|
      |Ajmer|buy|makadwali|
      |Ajmer|rent|gegal|
      |Ajmer|rent|railway quarters|


  Scenario Outline: Validate presence of chat discovery points on new properties for seller and seller-developer properties
    Given Buyer completed onboarding for "<city>"
    And Buyer is on detail Page of "<project>"
    Then Chat entry points should be visible

    Examples:
      |city|project|
      |Noida|Panchsheel greens 2|
      |Ajmer|Apartment in kishangarh|


  Scenario Outline: Validate absence of chat discovery points on new properties developer properties
    Given Buyer completed onboarding for "<city>"
    And Buyer is on detail Page of "<project>"
    Then Chat entry points should not be visible

    Examples:
      |city|project|
      |Noida|Paramount Golfforeste Premium Apartments|
  # floatingcta found

  @Test1
  Scenario Outline: Validate absence of chat discovery points on Commercial
    Given Buyer completed onboarding for "<city>"
    And Buyer is on detail Page of other "<services>" service of "<locality>"
    Then Chat entry points should not be visible

    Examples:
      |city|services|locality|
      #|Noida|commercial|Sector 75|
      |Noida|pg|Sector 75|
  # commercial click is not working on first attempt




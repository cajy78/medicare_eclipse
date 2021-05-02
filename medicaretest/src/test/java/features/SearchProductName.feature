@Search
Feature: Enable searching for products via product name

  @SearchByProdName
  Scenario Outline: User searches for product via name
    Given user launches medicare site and navigates to product list
    When user enters <productname>
    Then the product should be displayed
    And if the product not found error message should be displayed

    Examples: 
      | productname |
      | Combiflame  |
      | Paracetamol |
      | Amoxicillin |

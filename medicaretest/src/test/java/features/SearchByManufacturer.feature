@Search
Feature: Enable searching for products via manufacturer name

@SearchByManufacturerName
  Scenario Outline: User searches for product via manufacturer name
    Given user accesses medicare site and navigates to product list
     When end user enters medicine manufacturer <manufacturername>
     Then the products should be displayed
      And if products not found error message should be displayed
  
    Examples: 
      | manufacturername | 
      | Cipla            | 
      | Sun Pharma       | 
      | Intas            | 
      | test             |
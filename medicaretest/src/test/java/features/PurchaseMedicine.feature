@PurchaseMedicine
Feature: Purchase medicines

  Scenario Outline: End user purchases product from medicare
    Given registered users signs in to website with <userId> and <passwrd>
    When user adds following products to cart
      | products      |
      | Paracetamol   |
      | Combiflame    |
      | Ciprofloxacin |
    Then site should allow user to purchase the products

    Examples: 
      | userId           | passwrd |
      | test@test823.com | 1234567 |

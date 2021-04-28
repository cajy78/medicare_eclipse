@AdminUser
Feature: Admin user add product

  Scenario Outline: Admin user adds a product
    Given admin user successfully signs in to the site with <usid> and <pswd>
    When user navigates to manage product and enters <prodname>, <brandname>, <desc>, <price>, <quantity>, <category>
    Then site should allow saving product to database
    And product should be visible in list of products

    Examples: 
      | usid         | pswd  | prodname  | brandname                | desc                   | price | quantity | category   |
      | vk@gmail.com | admin | Cecil     | Pharmaco                 | cold tablet            |    50 |      180 | Analgesics |
      | kn@gmail.com | 12345 | ABSyrup45 | PQCoughSyrupBrand45_name | PQSyrup45_Test product |    40 |      200 | Analgesics |
      | vk@gmail.com | admin | PQSyrup45 | AZCoughSyrupBrand45_name | ABSyrup45_Test product |    40 |      200 | test       |

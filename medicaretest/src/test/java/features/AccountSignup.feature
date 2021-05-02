@Account
Feature: Enable Users To Create Accounts

  @Signup
  Scenario Outline: User creates an account on Medicare
    Given user accesses medicare site and clicks on Sign Up
    When user adds personal details <firstname> <lastname> <email> <contactnumber> <password>
    And then enters additional addresses <addressl1> <addressl2> <city> <postcode> <state> <country>
    Then the site should verify details and allow user to signup and login with created account

    Examples: 
      | firstname | lastname  | email                  | contactnumber | password   | addressl1 | addressl2 | city    | postcode | state   | country |
      | Cajetan   | Fernandes | test@testing1992.com   |    7894561234 |    1234567 | line1     | line2     | CityAre |   400789 | Swindon | England |
      | Cajetan   | Fernandes | testing@testng1992.com |    9874561237 | testingPwd | Line123   |        53 | APCIty  |   965147 | Thane   | India   |

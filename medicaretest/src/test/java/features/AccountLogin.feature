@Account
Feature: Enable Users To Login To Account

  @Login
  Scenario Outline: User login to account on Medicare
    Given user attempts to login to site
    When credentials <emailaddress> and <pwd> are entered
    Then navigates to home page if credentials are correct or displays login error for incorrect credentials

    Examples: 
      | emailaddress | pwd    |
      | kn@gmail.com | 123456 |
      | vk@gmail.com | admin  |

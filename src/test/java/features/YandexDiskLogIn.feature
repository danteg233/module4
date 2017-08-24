Feature: LogInFeature

  Scenario Outline: Login with correct user_name
    Given I navigate to the login page as "https://disk.yandex.ru/"
    And I enter <userName> and <password> and click login button
    Then I should see mail page

    Examples:
      | userName     | password  |
      | strong.tt123 | kikiki123 |



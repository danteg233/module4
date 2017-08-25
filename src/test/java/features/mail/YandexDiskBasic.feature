Feature: YandexDisk basics
  Background:
    Given I have YandexDisk page as "https://disk.yandex.ru/"

  Scenario Outline: Login with correct user_name
    Given I have main login page
    And I enter <userName> and <password> and click login button
    Then I should see mail page

    Examples:
      | userName     | password  |
      | strong.tt123 | kikiki123 |


  Scenario: Drag and Drop file into trash
    Given I have YandexDisk main page
    And I drag one item and move it to the trash
    And I restore file back
    Then I log out and confirm my log out
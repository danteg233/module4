Feature: DragAndDropFeature

  Scenario: Drag and Drop file into trash
    Given I have YandexDisk page
    And I drag one item and move it to the trash
    And I restore file back
    Then I log out and confirm my log out
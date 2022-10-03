Feature: food feature

  @SearchIngredients
  Scenario: user gets ingredients by search
    When the user searches for ingredient meat
    Then all related ingredients are returned

  @SearchMealsByIngredient
  Scenario: user gets recipes by ingredient
    When the user requests meals that contain meat
    Then all meals that contain meat are returned
    And the response is stored in the database marked by meat
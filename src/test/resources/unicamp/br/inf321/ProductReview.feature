# Created by douglas at 3/8/25
Feature: Product Reviews
  As a multibags customer user
  admin3 wants to create, delete and update the reviews of a product

  Scenario: Should create a review of a product 4
    Given user is logged on the multibags application
      | email    | admin3@admin3.admin3 |
      | password | admin3               |
    When he selects the option to create a review from product "4"
      | description | "Ótimo produto! Qualidade excelente. Grupo 07 os brabos" |
      | rating      | 5.0                                                      |
      | language    | "pt"                                                     |
    Then the product review should be created with success

  Scenario: Should not create a review of a product that already had a review from this user
    Given user is logged on the multibags application
      | email    | admin3@admin3.admin3 |
      | password | admin3               |
    When he selects the option to create a review from product "4"
      | description | "segundo review no mesmo produto |
      | rating      | 1.0                              |
      | language    | "pt"                             |
    Then the product review should be return an error


  # Like expected this scenario will fail
  # because the application has a bug in this feature
  Scenario: Should update a review of a product 4
    Given user is logged on the multibags application
      | email    | admin3@admin3.admin3 |
      | password | admin3               |
    When he selects the option to see reviews from product "4"
    And get the reviewId
    And he selects the option to update a review from product "4"
      | description | "Terrivel o produto! Qualidade inexistente. Grupo 07 os brabos" |
      | rating      | 1.0                                                             |
      | language    | "pt"                                                            |
    Then the product review should be updated with success


  Scenario: Should delete a review of a product 4
    Given user is logged on the multibags application
      | email    | admin3@admin3.admin3 |
      | password | admin3 |
    When he selects the option to see reviews from product "4"
    And get the reviewId
    And he selects the option to delete a review from product "4"
    Then the product review should be deleted with success

  Scenario: Should get an error when delete a invalid review of a product 4
    Given user is logged on the multibags application
      | email    | admin3@admin3.admin3 |
      | password | admin3 |
    When he get an invalid ReviewId
    And he selects the option to delete a review from product "4"
    Then the product review should get an error when delete
# Created by douglas at 3/8/25
Feature: Product Reviews
  As a multibags customer user
  admin3 wants to create, delete and update the reviews of a product

  Scenario: Should create a review of a product 4
    Given user is logged on the multibags application
      | email    | admin3@admin3.admin3 |
      | password | admin3 |
    When he selects the option to create a review from product "4"
      | description | "Ótimo produto! Qualidade excelente. Grupo 07 os brabos" |
      | rating      | 5.0                                                      |
      | language    | "pt"                                                     |
    Then the product review should be created with success

  Scenario: Should delete a review of a product 4
    Given user is logged on the multibags application
      | email    | admin3@admin3.admin3 |
      | password | admin3 |
    When he selects the option to delete a review from product "4"
    Then the product review should be deleted with success
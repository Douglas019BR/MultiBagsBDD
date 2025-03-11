# Created by douglas at 3/8/25
Feature: List Product Reviews
  As a multibags customer user
  Jhon wants to see the reviews of a product

  Scenario: Should show reviews of a product
    Given user is logged on the multibags application
      | email    | admin3@admin3.admin3 |
      | password | admin3 |
    When he selects the option to see reviews from product "2"
    Then the product reviews should be shown with success
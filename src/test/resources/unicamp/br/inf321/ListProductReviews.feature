# Created by douglas at 3/8/25
Feature: List Product Reviews
  As a multibags customer user
  Jhon wants to see the reviews of a product

  Scenario: Should show reviews of a product
    Given Jhon is logged on the multibags application
      | email    | o181804@g.unicamp.br |
      | password | aA#123456789         |
    When he selects the option to see reviews from product "2"
    Then the product reviews should be shown with success
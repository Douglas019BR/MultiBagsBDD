# API Testing Automation - Reviews API - Multibags BDD
## INF-321 UNICAMP 2024 BDD Activity

## Description

This project aims to automate tests for the Reviews API using Java 11, Cucumber, and Maven. The test focus is on review-related endpoints, ensuring their integrity and correct functionality. There are no frontend tests in this project.

## Tools Used

![Java11](https://img.shields.io/badge/Java-11-red?style=flat-square&logo=java)
Main language used for test development.

![Maven](https://img.shields.io/badge/Maven-3.8.5-blue?style=flat-square&logo=apache-maven)
Dependency and project build manager.

![Cucumber](https://img.shields.io/badge/Cucumber-7.0.0-green?style=flat-square&logo=cucumber)
Framework for writing automated tests in BDD (Behavior-Driven Development).

![JUnit](https://img.shields.io/badge/JUnit-5-orange?style=flat-square&logo=junit5)
Testing framework used as the base for scenario execution.

## Relevant URLs

* [MultiBags Website](http://multibags.1dt.com.br/shop/)
* [MultiBags Swagger](http://multibags.1dt.com.br/swagger-ui.html#/product-review-api)

## How to Run the Tests

1. Clone this repository:
```bash
git clone https://github.com/Douglas019BR/MultiBagsBDD.git
```

2. Navigate to the project folder:
```bash
cd MultiBagsBDD
```

3. Run the tests with Maven:
```bash
mvn clean test
```

## Note

Tests should be executed in the defined order. Although this violates the principle of test independence, this approach was adopted to avoid greater complexity, considering that there is no need for parallel execution.
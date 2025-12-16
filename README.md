# final_project

## Description

This repository contains a Final Project which contains API and Web UI test automation framework built with:

- **Cucumber** for Behavior-Driven Development (BDD) using Gherkin syntax  
- **Java** as the programming language  
- **Selenium WebDriver** to automate browser interactions  
- **Gradle** for build and dependency management  
- **Page Object Model (POM)** design pattern to separate UI logic from test scripts  

## Project Structure

src/
├── test/java/api/runner/apiRunner.java
├── test/java/api/steps/apiSteps.java
├── test/java/web/page/cartPage.java
├── test/java/web/page/checkoutPage.java
├── test/java/web/page/loginPage.java
├── test/java/web/page/productPage.java
├── test/java/web/runner/webRunner.java
├── test/java/web/steps/cartSteps.java
├── test/java/web/steps/checkoutSteps.java
├── test/java/web/steps/loginSteps.java
├── test/java/web/steps/productSteps.java
├── test/resources/features/api/api.feature
└── test/resources/features/web/web.feature

## Sample Test Case

This project consists of two testing frameworks: API testing and Web UI testing.

**API Tests**
e
The API test framework includes five sample test cases covering positive, negative, and edge scenarios:

1. Positive test case — valid credentials
2. Positive test case — update user data
3. Negative test case — invalid credentials
4. Negative test case — invalid GET request
5. Edge test case — boundary / edge values

**Web UI Tests**

The Web UI tests cover an end-to-end purchasing flow on the demo website:
🔗 https://www.saucedemo.com/

The test scenarios include:

1. Logging in to the website
2. Adding products to the shopping cart
3. Completing the checkout process

## How to Run the Tests

**Prerequisites**

Make sure the following are installed on your machine:
- Java JDK 8 or higher
- Gradle
- A supported browser (e.g., Google Chrome)
- Corresponding WebDriver available in your system PATH

**Run All Tests**

To execute all API and Web UI tests, run:
gradle test

**Run API Tests Only**

To run only the API tests:
gradle test --tests "*apiRunner"

**Run Web UI Tests Only**

To run only the Web UI tests:
gradle test --tests "*webRunner"

## Dependencies

org.junit.platform:junit-platform-launcher — JUnit Platform launcher for discovering and executing tests
io.cucumber:cucumber-java — Cucumber framework for Java
io.cucumber:cucumber-junit — Cucumber and JUnit integration
org.seleniumhq.selenium:selenium-java — Selenium WebDriver for browser automation in Java
junit:junit — JUnit 4 testing framework for unit and integration tests
io.rest-assured — REST Assured library for testing and validating REST APIs
org.json:json — Lightweight JSON parsing and generation library for Java

All dependencies are managed through Gradle (see build.gradle).

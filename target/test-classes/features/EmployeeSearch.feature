Feature: US-12345- Search an employee in HRMS

  Background:
    #Given user is navigated to HRMS application
    When user enters valid admin credential
    And user clicks on login button
    And user navigated to employee list page

  @regression @background
  Scenario: Search an employee by ID
    #Given user is navigated to HRMS application
    #When user enters valid admin credential
    #And user clicks on login button
    #And user navigated to employee list page
    When user enters valid employee id
    And user clicks on search button
    Then user is able to see employee information
# hash can be used to comment here, * can be used to replace gherkin Given, When, And, Then

  @smoke @background
  Scenario: Search an employee by name
    #Given user is navigated to HRMS application
    #When user enters valid admin credential
    #And user clicks on login button
    #And user navigated to employee list page
    When user enters valid employee name
    And user clicks on search button
    Then user is able to see employee information

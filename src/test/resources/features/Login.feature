Feature: Validation of Login Scenarios

  Background:
    #Given user is navigated to HRMS application

  @smoke @sprint12 @regression
  Scenario: Admin Login
    # Given user is navigated to HRMS application
    When user enters valid admin credential
    And user clicks on login button
    Then admin user is successfully logged in

    @smoke @regression @sprint12
  Scenario: ESS (Non Admin) Login
   # Given user is navigated to HRMS application
    When user enters valid ess username and password
    And user clicks on login button
    Then ess user is successfully logged in

     @smoke @regression
  Scenario: Invalid Login
    # Given user is navigated to HRMS application
    When user enters invalid username and password
    And user clicks on login button
    Then user see error message on the screen

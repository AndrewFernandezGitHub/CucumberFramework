Feature:Adding the employees in HRMS Application

  Background:
    When user enters valid admin credential
    And user clicks on login button
    Then admin user is successfully logged in
    When user clicks on PIM option
    And user clicks on add employee option

  @regression @123
  Scenario: Adding one employee from feature file
    #Given user is navigated to HRMS application
    #When user enters valid admin credential
    #And user clicks on login button
    #Then admin user is successfully logged in
    #When user clicks on PIM option
    #And user clicks on add employee option
    And user enters firstname middlename and lastname
    And user clicks on save button
    Then employee added succesfully

  @123
  Scenario: Adding one employee using cucumber feature file
    #When user enters valid admin credential
    #And user clicks on login button
    #Then admin user is successfully logged in
    #When user clicks on PIM option
    #And user clicks on add employee option
    And user enters "Andrew1" "Bob1" and "fernandez1"
    And user clicks on save button
    Then employee added succesfully

  @123 @test
  Scenario Outline: Adding multiple employees
    #When user enters valid admin credential
    #And user clicks on login button
    #Then admin user is successfully logged in
    #When user clicks on PIM option
    #And user clicks on add employee option
    And user provides "<firstName>" "<middleName>" and "<lastName>"
    And user clicks on save button
    Then employee added succesfully
    Examples:
      | firstName | middleName | lastName |
      | asel      | MS         | tolga    |
      | yazgil    | MS         | Kishan   |
      | tarik     | MS         | Mujeeb   |
      | nassir    | MS         | flowers  |

    @test @datatable
  Scenario: Add employee using data table
      When user provides multiple employee data and verify they are added
      |firstName|middleName|lastName|
      | asel      | MS         | tolga    |
      | yazgil    | MS         | Kishan   |
      | tarik     | MS         | Mujeeb   |
      | nassir    | MS         | flowers  |

      @test @excel
      Scenario:Adding multiple employees from excel file
        When user adds multiple employees from excel file using "EmployeeData" sheet and verify the user added



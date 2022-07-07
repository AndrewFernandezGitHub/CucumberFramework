package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.DBUtils;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    String empId;
    String firstName;
    String dbFirstName;
    String dbEmpId;

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        //older version
        click(employeeSearchPage.pimOption);
    }

    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        click(employeeSearchPage.addEmployeeOption);
    }

    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        sendText(addEmployeePage.firstNameField, "Andrew");
        sendText(addEmployeePage.middleNameField, "Bob");
        sendText(addEmployeePage.lastNameField, "Fernandez");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        click(addEmployeePage.saveButton);
    }

    @Then("employee added succesfully")
    public void employee_added_succesfully() {
        System.out.println("Employee Added");
    }

    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstNameValue, String middleNameValue, String lastNameValue) {
        sendText(addEmployeePage.firstNameField, firstNameValue);
        sendText(addEmployeePage.middleNameField, middleNameValue);
        sendText(addEmployeePage.lastNameField, lastNameValue);
    }

    @When("user provides {string} {string} and {string}")
    public void user_provides_and(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.firstNameField, firstName);
        sendText(addEmployeePage.middleNameField, middleName);
        sendText(addEmployeePage.lastNameField, lastName);
    }

    @When("user provides multiple employee data and verify they are added")
    public void user_provides_multiple_employee_data_and_verify_they_are_added(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> employeeNames = dataTable.asMaps();
        for (Map<String, String> employee : employeeNames) {
            System.out.println(employee);
            String firstNameValue = employee.get("firstName");
            String middleNameValue = employee.get("middleName");
            String lastNameValue = employee.get("lastName");
            System.out.println(firstNameValue + " " + middleNameValue + " " + lastNameValue);

            sendText(addEmployeePage.firstNameField, firstNameValue);
            sendText(addEmployeePage.middleNameField, middleNameValue);
            sendText(addEmployeePage.lastNameField, lastNameValue);
            click(addEmployeePage.saveButton);
            Thread.sleep(3000);

            //verification of adding an employee is HW
            click(employeeSearchPage.addEmployeeOption);

        }
    }

    @When("user adds multiple employees from excel file using {string} sheet and verify the user added")
    public void user_adds_multiple_employees_from_excel_file_using_sheet_and_verify_the_user_added(String sheetName) throws InterruptedException {
        List<Map<String, String>> newEmployees = ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, sheetName);
        Iterator<Map<String, String>> itr = newEmployees.iterator();//iterate through the map
        //checks whether the next element exist or not
        while (itr.hasNext()) {
            //it will return the key and value for the employees
            Map<String, String> mapNewEmp = itr.next();
            System.out.println(mapNewEmp.get("FirstName"));
            System.out.println(mapNewEmp.get("MiddleName"));
            System.out.println(mapNewEmp.get("LastName"));
            //filling all the fields from the data coming from excel file
            sendText(addEmployeePage.firstNameField,mapNewEmp.get("FirstName"));
            sendText(addEmployeePage.middleNameField, mapNewEmp.get("MiddleName"));
            sendText(addEmployeePage.lastNameField, mapNewEmp.get("LastName"));
            //it will fetch the employee id from attribute
            String empIdValue=addEmployeePage.empIdLocator.getAttribute("value");
            //upload the photograph
            sendText(addEmployeePage.photograph,mapNewEmp.get("Photograph"));
            if(!addEmployeePage.checkBox.isSelected()){
                click(addEmployeePage.checkBox);
            }
            sendText(addEmployeePage.createUserName, mapNewEmp.get("Username"));
            sendText(addEmployeePage.createPassword, mapNewEmp.get("Password"));
            sendText(addEmployeePage.confirmPassword, mapNewEmp.get("Password"));
            click(addEmployeePage.saveButton);

            Thread.sleep(3000);
            //to verify the employee, we will navigate to employee list option
            click(employeeSearchPage.empListOption);
            sendText(employeeSearchPage.idField, empIdValue);
            click(employeeSearchPage.searchButton);
            //it returning the data from row in results
            List<WebElement> rowData=driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i=0;i<rowData.size(); i++){
                String rowText =rowData.get(i).getText();
                System.out.println(rowText);
                String expectedData=empIdValue+" "+mapNewEmp.get("FirstName")+" "+mapNewEmp.get("MiddleName")+" "+mapNewEmp.get("LastName");
                Assert.assertEquals(expectedData,rowText);
            }
            click(employeeSearchPage.addEmployeeOption);
            Thread.sleep(2000);
        }
    }

    @And("user grabs employee id")
    public void userGrabsEmployeeId() {
        empId=addEmployeePage.empIdLocator.getAttribute("value");
        firstName=addEmployeePage.firstNameField.getAttribute("value");
    }

    @And("user query the database for same employee id")
    public void userQueryTheDatabaseForSameEmployeeId() {
       String query= "select * from hs_hr_employees where employee_id='"+empId+"'";
       //0 is row number 1
        List<Map<String,String>> tableData=DBUtils.getDataFromDB(query);
       dbFirstName=DBUtils.getDataFromDB(query).get(0).get("emp_firstname");
       dbEmpId=DBUtils.getDataFromDB(query).get(0).get("employee_id");

    }

    @Then("user verifies the result")
    public void userVerifiesTheResult() {
        System.out.println("First name from Front end"+firstName);
        System.out.println("First name from Back end"+dbFirstName);
        Assert.assertEquals(firstName,dbFirstName);
        Assert.assertEquals(empId,dbEmpId);
    }
}

package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.ConfigReader;

public class EmployeeSearchSteps extends CommonMethods {
    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndLaunchApplication();
    }

    @When("user enters valid admin credential")
    public void user_enters_valid_admin_credential() {
        //LoginPage login=new LoginPage();
        sendText(login.usernameBox, ConfigReader.getPropertyValue("username"));
        //WebElement userNameField = driver.findElement(By.id("txtUsername"));
        //userNameField.sendKeys(ConfigReader.getPropertyValue("username"));
        //sendText(userNameField, ConfigReader.getPropertyValue("username"));
       // WebElement passwordField = driver.findElement(By.name("txtPassword"));
        sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));
        //sendText(passwordField, ConfigReader.getPropertyValue("password"));
        //passwordField.sendKeys(ConfigReader.getPropertyValue("password"));
        //driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT,TimeUnit.SECONDS);
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        //LoginPage login=new LoginPage();
        click(login.loginBtn);
       // WebElement loginButton = driver.findElement(By.id("btnLogin"));
        //click(loginButton);
        //loginButton.click();
        //driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT,TimeUnit.SECONDS);
    }

    @When("user navigated to employee list page")
    public void user_navigated_to_employee_list_page() {
        //WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        //pimOption.click();
        click(employeeSearchPage.pimOption);
        //WebElement empListOption = driver.findElement(By.id("menu_pim_viewEmployeeList"));
        //click(empListOption);
        click(employeeSearchPage.empListOption);
        //empListOption.click();
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
       // WebElement searchID = driver.findElement(By.id("empsearch_id"));
        sendText(employeeSearchPage.idField, "8510142");
        //sendText(searchID, "8510142");
        //searchID.sendKeys("8510142");
    }

    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
       // WebElement searchButton = driver.findElement(By.id("searchBtn"));
        click(employeeSearchPage.searchButton);
        //click(searchButton);
        //searchButton.click();
    }

    @Then("user is able to see employee information")
    public void user_is_able_to_see_employee_information() {
        System.out.println("Result Displayed");
        //tearDown();
    }

    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
        //driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        //WebElement searchName = driver.findElement(By.xpath("(//*[@type='text'])[1]"));
        sendText(employeeSearchPage.nameField, "zubair");
        //sendText(searchName, "zubair");
        //searchName.sendKeys("Name520");
    }
}
package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//this will ensure proper order of execution, changing overall execution flow
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    //make sure to update bearer token when completing new tasks as they will expire
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTUzMzQ5MjEsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NTM3ODEyMSwidXNlcklkIjoiMzgxMyJ9.jxjle24E2vf9LYFFeOF0b5rwX1tJIZBKNC4Z3hvqcP0";
    static String employee_id;

    @Test
    public void aCreateEmployee() {
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"AndrewTestApi\",\n" +
                        "  \"emp_lastname\": \"LastNameTestApi\",\n" +
                        "  \"emp_middle_name\": \"MiddleNameTestApi\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2000-01-01\",\n" +
                        "  \"emp_status\": \"ProbationTestApi\",\n" +
                        "  \"emp_job_title\": \"QA Test Engineer API\"\n" +
                        "}");

        // .when is the endpoint of the api
        Response response = request.when().post("/createEmployee.php");
        //response.prettyPrint();
        //prettyPrint will print all the full details for the newly ccreated employee
        response.then().assertThat().statusCode(201);

        //hamcrest Matchers
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname", equalTo("AndrewTestApi"));
        //using jsonPath(), to specify the key in the body so that it returns the value against it
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }

    @Test
    public void bGetCreatedEmployee() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();

        response.then().assertThat().statusCode(200);
        String tempId = response.jsonPath().getString("employee.employee_id");
        System.out.println(tempId);
        Assert.assertEquals(tempId, employee_id);

    }

    @Test
    public void cUpdateEmployee() {
        RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "    \"employee_id\": \"" + employee_id + "\",\n" +
                        "  \"emp_firstname\": \"AndrewTestApi\",\n" +
                        "  \"emp_lastname\": \"LastNameTestChangeApi\",\n" +
                        "  \"emp_middle_name\": \"MiddleNameTestApi\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2000-01-01\",\n" +
                        "  \"emp_status\": \"Confirmed\",\n" +
                        "  \"emp_job_title\": \"Promoted\"\n" +
                        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void dGetUpdatedEmployee() {
        RequestSpecification request = given().header("Content-Type", "application/json").
                header("Authorization", token).queryParam("employee_id", employee_id);
        Response response = request.when().get("/getOneEmployee.php");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void eGetAllEmployees() {
        RequestSpecification request = given().header("Authorization", token).
                header("Content-Type", "application/json");
        Response response = request.when().get("/getAllEmployees.php");
        //prettyPrint returns string of full response
        String allEmployees = response.prettyPrint();
        //jsonPath() method vs jsonPath class
        //jsonPath is a class that contains method for converting the values into string json object
        //jsonPath() is a method that belongs to jsonPath class

        //creating object of jsonPath class, to segregate data
        JsonPath js = new JsonPath(allEmployees);

        //retrieving the total number of employees
        int count = js.getInt("Employees.size()");
        System.out.println(count);

        //to print only employee id of all employees
        for (int i = 0; i < count; i++) {
            String empID = js.getString("Employees[" + i + "].employee_id");
            System.out.println(empID);
        }
    }
}

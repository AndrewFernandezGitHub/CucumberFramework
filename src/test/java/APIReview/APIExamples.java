package APIReview;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class APIExamples {
//set the base URI
    String baseURI= RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";
    String Token="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NTY1MjcwNDAsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY1NjU3MDI0MCwidXNlcklkIjoiNDIyNCJ9.7NSxnvYqYpi-39tHb8MSDDIEg1CKXubr8tLWIFl7aEQ";

    //create an Employee in Syntax HRMS
    @Test
    public void createAnEmployee(){
        RequestSpecification request=given().header("Content-Type","application/json").header("Authorization",Token).body("{\n" +
                "  \"emp_firstname\": \"AndrewNewTest\",\n" +
                "  \"emp_lastname\": \"NewTestLast\",\n" +
                "  \"emp_middle_name\": \"NewTestMiddle\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2000-02-27\",\n" +
                "  \"emp_status\": \"New Emp\",\n" +
                "  \"emp_job_title\": \"QA Engineer 1\"\n" +
                "}");

        Response response=request.when().post("/createEmployee.php");

        //understanding GSON to decode JSON object
        //reading the response returned to us by restassured and converting it  into json element
        JsonElement json_element=new JsonParser().parse(response.asString());
        JsonObject json_data=json_element.getAsJsonObject();
        JsonElement key_message=json_data.get("Message");
        System.out.println(key_message);
        JsonElement Employee =json_data.get("Employee");
        System.out.println(Employee);
        JsonObject Employee_Details=Employee.getAsJsonObject();
        System.out.println(Employee_Details.get("emp_firstname"));
        System.out.println(Employee_Details.get("emp_middle_name"));
        System.out.println(Employee_Details.get("emp_birthday"));
        System.out.println(Employee_Details.get("emp_job_title"));

        JsonElement name;
        System.out.println(name=Employee_Details.get("emp_firstname"));

        Assert.assertEquals(Employee,Employee_Details);

    }
}

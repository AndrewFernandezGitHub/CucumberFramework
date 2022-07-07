package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload(){

        String createEmployee="{\n" +
                "  \"emp_firstname\": \"AndrewTestApi\",\n" +
                "  \"emp_lastname\": \"LastNameTestApi\",\n" +
                "  \"emp_middle_name\": \"MiddleNameTestApi\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2000-01-01\",\n" +
                "  \"emp_status\": \"ProbationTestApi\",\n" +
                "  \"emp_job_title\": \"QA Test Engineer API\"\n" +
                "}";
        return createEmployee;
    }
    //this function will return a string, passing the body from JSON object
    //passing data to add the employee
    public static String createEmployeePayloadViaJson(){
        JSONObject obj=new JSONObject();
        obj.put("emp_firstname","AndrewTestApi");
        obj.put("emp_lastname","LastNameTestApi");
        obj.put("emp_middle_name","MiddleNameTestApi");
        obj.put("emp_gender","M");
        obj.put("emp_birthday","2000-01-01");
        obj.put("emp_status","ProbationTestApi");
        obj.put("emp_job_title","QA Test Engineer API");
        return obj.toString();
    }
    public static String createEmployeeDynamic(String firstName,String lastName,String middleName,String gender, String dob, String status,String jobTitle){
        JSONObject obj=new JSONObject();
        obj.put("emp_firstname",firstName);
        obj.put("emp_lastname",lastName);
        obj.put("emp_middle_name",middleName);
        obj.put("emp_gender",gender);
        obj.put("emp_birthday",dob);
        obj.put("emp_status",status);
        obj.put("emp_job_title",jobTitle);
        return obj.toString();
    }
}

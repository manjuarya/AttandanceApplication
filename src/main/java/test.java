import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class test {

    public static void main( String[] args )
    {

        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("name", "manju");
        employeeDetails.put("id", "mla");

        JSONObject employeeDetails1 = new JSONObject();
        employeeDetails1.put("name", "avinash");
        employeeDetails1.put("id", "aa");

        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("name", "atharv");
        employeeDetails2.put("id", "aa");


        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeDetails);
        employeeList.add(employeeDetails1);
        employeeList.add(employeeDetails2);

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("members", employeeList);



       /* //First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Lokesh");
        employeeDetails.put("lastName", "Gupta");
        employeeDetails.put("website", "howtodoinjava.com");

        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employeeDetails);

        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Brian");
        employeeDetails2.put("lastName", "Schultz");
        employeeDetails2.put("website", "example.com");

        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("employee", employeeDetails2);

        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2); */

        //Write JSON file
        try (FileWriter file = new FileWriter("employees.json")) {

            file.write(employeeObject.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





/***
 * This is the Main class of this application
 */
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
// import org.json.simple.*;
import org.json.*;


class Controller {

    @SuppressWarnings("unchecked")
    public static void main( String[] args ) {

        boolean screenDone = false;

        HashMap<String, String> membersDetails = new HashMap<String, String>();
        Functions function = new Functions();

        String fileName = "member.json";
        membersDetails = function.accessDetailsFromAFile();

        while(!screenDone) {

            System.out.println("Welcome to the attendance application: \n" +
                               "Please select a below option:\n" +
                               "(1) Display members details.\n" +
                               "(2) Check the duplicate Id's\n" +
                               "(3) Take today's attendance.\n" +
                               "(4) Display attendance record.\n" +
                               "(5) Update or Add student's record.\n" +
                               "(0) Exit.\n");


            Scanner scannerObj = new Scanner(System.in);
            String choice = scannerObj.nextLine();

            switch (choice) {
                case "1":
                    function.displayMembersDetails(membersDetails);
                    break;
                case "2":
                    function.checkDuplicateIds(membersDetails);
                    break;
                case "3":
                    //function.checkDuplicateIds(studentsList);
                    break;
                case "4":
                    function.insertTheMemberDetails();
                    break;
                case "0":
                    screenDone=true;
                    break;
                default:
                    System.out.println("Invalid Selection!");
                    break; }
        }
    }
}

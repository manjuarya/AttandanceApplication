/**
 * This class keps the all methods
 * related to attendance.
 */


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Attendance {
    Scanner scannerObj = new Scanner(System.in);

    void takeAttendance(){
        ArrayList<AttendanceMember> attendanceDetails = new ArrayList<>();
        Functions function = new Functions();
        HashMap<String, String> membersDetails = function.accessDetailsFromAFile();

        for (String id : membersDetails.keySet()) {
            System.out.println( membersDetails.get(id) + "  is present?\n" +
                    "Please enter y for Yes n for No");
            String choice = scannerObj.nextLine();
            String attendance;
            if(choice.equals("y")){
                attendance = "Present";
                AttendanceMember member = new AttendanceMember(id, membersDetails.get(id), attendance);
                attendanceDetails.add(member);
            } else {
                attendance = "Absent";
                AttendanceMember member = new AttendanceMember(id, membersDetails.get(id), attendance);
                attendanceDetails.add(member);
            }
        }
        insertAttendanceIntoAFile( attendanceDetails);
    }

    @SuppressWarnings("unchecked")
    void insertAttendanceIntoAFile(ArrayList<AttendanceMember> todayAttendanceDetails){

        // create the JSONArray with JSONObject
        // memberDetails
        JSONArray memberList = new JSONArray();
        JSONObject typeOfList = new JSONObject();

        for (AttendanceMember member : todayAttendanceDetails) {

            // make the JSON object with
            // the key name and value id
            // both are string type
            JSONObject memberDetails = new JSONObject();
            memberDetails.put("name", member.getName());
            memberDetails.put("id", member.getId());
            memberDetails.put("status", member.getAttendance());

            memberList.add(memberDetails);
        }

        typeOfList.put("attendance", memberList);

        java.util.Date date=new java.util.Date();
        String dateString = date.toString();
        String formattedDate = dateString.substring(4,7) + dateString.substring(8,10) + dateString.substring(24) + "Attendance";

        //Write JSON file
        try (FileWriter file = new FileWriter(formattedDate+".json")) {

            file.write(typeOfList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    void accessDetailsFromAAttendanceFile(){

        Scanner scannerObj = new Scanner(System.in);

        File folder = new File("/Users/manjulataarya/Documents/pidAssigments/pid-exercise-2/attendanceFiles");
        File[] files = folder.listFiles();
        for (File file : files)
        {
            ArrayList<AttendanceMember> membersAttendanceDetails = new ArrayList<AttendanceMember>();

            System.out.println("Do you want to access file " + file.getName() + "" +
                    ". Press y for continue.");
            String choice = scannerObj.nextLine();
            if(choice.equals("y")){
                //JSON parser object to parse read file
                JSONParser jsonParser = new JSONParser();

                try (FileReader reader = new FileReader(file))
                {
                    //Read JSON file
                    Object obj = jsonParser.parse(reader);

                    JSONObject members = (JSONObject) obj;

                    JSONArray memberList = (JSONArray) members.get("attendance");
                    //System.out.println(memberList);

                    String name;
                    String id;
                    String attendance;

                    for(Object member : memberList){
                        JSONObject memberObject = (JSONObject) member;
                        name = (String) memberObject.get("name");
                        id = (String) memberObject.get("id");
                        attendance = (String) memberObject.get("status");
                        AttendanceMember memberDetail = new AttendanceMember(id, name, attendance);
                        membersAttendanceDetails.add(memberDetail);
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            displayAttendanceDetails( membersAttendanceDetails);
        }
    }

    void displayAttendanceDetails(ArrayList<AttendanceMember> attendanceList){

        System.out.println("ID   " + "NAME          " + "STATUS      ");
        for (AttendanceMember member : attendanceList) {
            System.out.println( member.getId() + " " + member.getName() +
                    " " + member.getAttendance());
        }
        System.out.println("\n");
    }
}

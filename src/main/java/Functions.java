import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

class Functions {

    public static void main(String[] args) {
        Functions function = new Functions();
        //function.writeIntoAFile();
        function.accessDetailsFromAFile();
    }

    void displayMembersDetails(HashMap<String, String> membersDetails){
        System.out.println("Member Name  " + "ID");
        for (String name : membersDetails.keySet()) {
            System.out.println( name + "  " + membersDetails.get(name));
        }
    }

    void checkDuplicateIds(HashMap<String, String> membersDetails){
        for (String name : membersDetails.keySet()) {
            System.out.println( name + "  " + membersDetails.get(name));
        }
    }

    @SuppressWarnings("unchecked")
    HashMap<String, String> accessDetailsFromAFile(){
        //JSON parser object to parse read file

        HashMap<String, String> membersDetailsFromFile = new HashMap<String, String>();

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("members.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONObject members = (JSONObject) obj;

            JSONArray memberList = (JSONArray) members.get("members");
            //System.out.println(memberList);

            String name = null;
            String id = null;

            for(Object member : memberList){
                JSONObject memberObject = (JSONObject) member;
                name = (String) memberObject.get("name");
                id = (String) memberObject.get("id");
                membersDetailsFromFile.put(id, name);
            }

            /*for (String s : membersDetailsFromFile.keySet()) {
                System.out.println(s + "  " + membersDetailsFromFile.get(s));
            }*/

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return membersDetailsFromFile;
    }


    @SuppressWarnings("unchecked")
    void addMemberIntoAFile(){
        HashMap<String, String>  memberDetailsFromFile = accessDetailsFromAFile();

        boolean screenDone = false;

        Scanner scannerObj = new Scanner(System.in);

        while(!screenDone) {
            // name of the member to be inserted into the file
            System.out.println("Please enter the name");
            String name = scannerObj.nextLine();

            // id of the member to be inserted into the file
            System.out.println("Please enter the id");
            String id = scannerObj.nextLine();

            memberDetailsFromFile.put(id, name);

            System.out.println("Please enter e to exit");
            String exit = scannerObj.nextLine();
            if (exit.equals("e")) {
                screenDone = true;
            }
        }

        // create the JSONArray with JSONObject
        // memberDetails
        JSONArray memberList = new JSONArray();
        JSONObject typeOfList = new JSONObject();

        for (String name : memberDetailsFromFile.keySet()) {

            // make the JSON object with
            // the key name and value id
            // both are string type
            JSONObject memberDetails = new JSONObject();
            memberDetails.put("name", memberDetailsFromFile.get(name));
            memberDetails.put("id", name);

            memberList.add(memberDetails);
        }
        typeOfList.put("members", memberList);
        //Write JSON file
        try (FileWriter file = new FileWriter("members.json")) {

            file.write(typeOfList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void removeMemberFromAFile(){
        HashMap<String, String>  memberDetailsFromFile = accessDetailsFromAFile();

        System.out.println("Please select a option:\n" +
                "(1) Remove by ID.\n" +
                "(2) Remove by Name\n" +
                "(0) Exit.\n");

        boolean screenDone = false;

        Scanner scannerObj = new Scanner(System.in);
        String choice = scannerObj.nextLine();

        switch (choice) {
            case "1":
                function.displayMembersDetails(membersDetails);
                break;
            case "2":
                function.checkDuplicateIds(membersDetails);
                break;
            case "0":
                screenDone=true;
                break;
            default:
                System.out.println("Invalid Selection!");
                break; }

        while(!screenDone) {
            // name of the member to be inserted into the file
            System.out.println("Please enter the name");
            String name = scannerObj.nextLine();
    }
}


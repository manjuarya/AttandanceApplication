import org.json.simple.parser.*;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class keeps all the methods
 * related to a member
 */

class Functions {

    public static void main(String[] args) {
        Functions function = new Functions();
        //function.writeIntoAFile();
        function.accessDetailsFromAFile();
    }

    void displayMembersDetails(){
        HashMap<String, String> membersDetails = accessDetailsFromAFile();

        System.out.println("ID  " + "NAME");
        for (String id : membersDetails.keySet()) {
            System.out.println( id + "  " + membersDetails.get(id));
        }
        System.out.println("\n");
        //System.out.println(membersDetails.size());
    }


    @SuppressWarnings("unchecked")
    void addMemberIntoAFile(){

        HashMap<String, String> membersDetails = accessDetailsFromAFile();
        boolean screenDone = false;

        Scanner scannerObj = new Scanner(System.in);

        while(!screenDone) {
            // name of the member to be inserted into the file
            System.out.println("Please enter the name");
            String name = scannerObj.nextLine();

            // id of the member to be inserted into the file
            System.out.println("Please enter the id");
            String id = scannerObj.nextLine();

            if(membersDetails.containsKey(id)){
                System.out.println("id " + id + " is already exist. Please enter another id.");
            } else{
                membersDetails.put(id, name);
            }

            System.out.println("Please enter to continue and e to exit");
            String exit = scannerObj.nextLine();
            if (exit.equals("e")) {
                screenDone = true;
            }
        }
        writeIntoAFileFromHashMap(membersDetails);
    }

    void writeIntoAFileFromHashMap(HashMap<String, String>  memberDetailsToBeWrittenIntoAFile){
        // create the JSONArray with JSONObject
        // memberDetails
        JSONArray memberList = new JSONArray();
        JSONObject typeOfList = new JSONObject();

        for (String id : memberDetailsToBeWrittenIntoAFile.keySet()) {

            // make the JSON object with
            // the key name and value id
            // both are string type
            JSONObject memberDetails = new JSONObject();
            memberDetails.put("name", memberDetailsToBeWrittenIntoAFile.get(id));
            memberDetails.put("id", id);

            memberList.add(memberDetails);
        }
        typeOfList.put("members", memberList);
        //Write JSON file
        try (FileWriter file = new FileWriter("temp.json")) {

            file.write(typeOfList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            File f = new File("members.json");           //file to be delete
            f.delete();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        try (FileWriter file = new FileWriter("members.json")) {

            file.write(typeOfList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void removeMemberFromAFile(){
        System.out.println("Please select a option:\n" +
                "(1) Remove by ID.\n" +
                "(2) Remove by Name\n");

        Scanner scannerObj = new Scanner(System.in);
        String choice = scannerObj.nextLine();

        switch (choice) {
            case "1":
                removeMember("ID");
                break;
            case "2":
                removeMember("Name");
                break;
            default:
                System.out.println("Invalid Selection!");
                break;
        }
    }

    void removeMember(String removeBy){

        HashMap<String, String> memberDetailsFromFile = accessDetailsFromAFile();
        boolean screenDone = false;

        Scanner scannerObj = new Scanner(System.in);

        if(removeBy.equals("ID")){
            while(!screenDone) {
                // name of the member to be inserted into the file
                System.out.println("Please enter the Id which you want to remove");
                String idToBeRemoved = scannerObj.nextLine();
                if(memberDetailsFromFile.containsKey(idToBeRemoved)){
                    memberDetailsFromFile.remove(idToBeRemoved);
                } else{
                    System.out.println("File does not contain " + idToBeRemoved + " id.");
                }
                System.out.println("Do you want to remove more Members. " +
                                   "Press y for Yes and n for No ");
                String choice = scannerObj.nextLine();
                if(choice.equals("n")){
                    screenDone = true;
                }
            }
        }
        if(removeBy.equals("Name")){
            while(!screenDone) {
                // name of the member to be inserted into the file
                System.out.println("Please enter the Name which you want to remove");
                String nameToBeRemoved = scannerObj.nextLine();
                if(memberDetailsFromFile.containsValue(nameToBeRemoved)){
                    String idOfNameToBeRemoved = null;
                    for (String id : memberDetailsFromFile.keySet()) {
                        if(memberDetailsFromFile.get(id).equals(nameToBeRemoved)){
                            idOfNameToBeRemoved = id;
                        }
                    }
                    memberDetailsFromFile.remove(idOfNameToBeRemoved);
                } else{
                    System.out.println("File does not contain " + nameToBeRemoved + " name.");
                }
                System.out.println("Do you want to remove more members. " +
                        "Press y for Yes and n for No ");
                String choice = scannerObj.nextLine();
                if(choice.equals("n")){
                    screenDone = true;
                }
            }
        }
        writeIntoAFileFromHashMap(memberDetailsFromFile);
    }

    @SuppressWarnings("unchecked")
    HashMap<String, String> accessDetailsFromAFile(){

        HashMap<String, String> membersDetailsFromFile = new HashMap<String, String>();

        //JSON parser object to parse read file
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
}


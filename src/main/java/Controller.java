/***
 * This is the Main class of this application
 */
import java.util.*;



class Controller {

    @SuppressWarnings("unchecked")
    public static void main( String[] args ) {

        System.out.println("Welcome to the attendance application:");

        boolean screenDone = false;

        Functions function = new Functions();
        Attendance attendance = new Attendance();

        while(!screenDone) {

            System.out.println("Please select a below option:\n" +
                               "(1) Display members details.\n" +
                               "(2) Add member to the file.\n" +
                               "(3) Remove member from the file.\n" +
                               "(4) Take today's attendance.\n" +
                               "(5) Display attendance record.\n" +
                               "(0) Exit.\n");


            Scanner scannerObj = new Scanner(System.in);
            String choice = scannerObj.nextLine();

            switch (choice) {
                case "1":
                    function.displayMembersDetails();
                    break;
                case "2":
                    function.addMemberIntoAFile();
                    break;
                case "3":
                    function.removeMemberFromAFile();
                    break;
                case "4":
                    attendance.takeAttendance();
                    break;
                case "5":
                    attendance.accessDetailsFromAAttendanceFile();
                    break;
                case "6":
                    //attendance.takeAttendance();
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

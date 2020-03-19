/**
 * This class is the part of attendance class
 * to keep the attendance data.
 */

public class AttendanceMember {

    private String id;
    private String name;
    private String attendance;

    public AttendanceMember(){
    }

    public AttendanceMember(String id, String name, String attendance) {
        this.id = id;
        this.name = name;
        this.attendance = attendance;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return "AttendanceMember{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", attendance='" + attendance + '\'' +
                '}';
    }
}

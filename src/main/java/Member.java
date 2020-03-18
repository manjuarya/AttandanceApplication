/*****
 * This class hold the details of a student
 */


public class Member {
    private String name;
    private String id;

    /**
     * create a student without the
     * @param
     */
    public Member() {
    }
    /**
     * create a student with the
     * @param id and name
     */
    public Member(String name, String id){
        this.name = name;
        this.id = id;
    }

    public String getMemberName() {
        return name;
    }

    public void setMemberName(String studentName) {
        this.name = studentName;
    }

    public String getMemberId() {
        return id;
    }

    public void setMemberId(String studentId) {
        this.id = studentId;
    }

    @Override
    public String toString() {
        return name + " " + id;
    }
}

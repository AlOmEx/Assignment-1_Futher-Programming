package rmit.w1;

public class StudentEnrollment {
    private String SID;
    private String CID;
    private String semester;
    public StudentEnrollment(String SID, String CID, String semester) {
        this.SID = SID;
        this.CID = CID;
        this.semester = semester;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getCID() {
        return CID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }


}

package rmit.w1;

import java.util.Date;

public class Student implements ListManagement {
    private String studentID;
    private String studentName;
    private Date birthDate;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Student(String studentID, String studentName, Date birthDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.birthDate = birthDate;
    }
}

package rmit.w1;

import java.util.Date;

public class Student implements ListManagement{
    private String studentID;
    private String studentName;
    private String birthDate;

    public Student(String studentID, String studentName, String birthDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.birthDate = birthDate;
    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}

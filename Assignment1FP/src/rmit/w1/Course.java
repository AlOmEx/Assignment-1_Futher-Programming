package rmit.w1;

public class Course implements ListManagement{
    private String courseID;
    private String courseName;
    private String numOfCre;

    public Course(String courseID, String courseName, String numOfCre) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.numOfCre = numOfCre;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getNumOfCre() {
        return numOfCre;
    }

    public void setNumOfCre(String numOfCre) {
        this.numOfCre = numOfCre;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", courseName='" + courseName + '\'' +
                ", numOfCre='" + numOfCre + '\'' +
                '}';
    }
}

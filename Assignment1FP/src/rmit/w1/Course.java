package rmit.w1;

public class Course implements ListManagement{
    private String courseID;
    private String courseName;
    private int numOfCre;

    public Course(String courseID, String courseName, int numOfCre) {
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

    public int getNumOfCre() {
        return numOfCre;
    }

    public void setNumOfCre(int numOfCre) {
        this.numOfCre = numOfCre;
    }
}

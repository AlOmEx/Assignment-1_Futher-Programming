package rmit.w1;

import java.util.ListIterator;

public class StudentEnrollmentManagement implements StudentEnrollmentManager {
    public StudentEnrollmentManagement() {
        super();
    }

    @Override
    public void Add(String sid, String cid, String semester) {
        if(!studentAvailability(sid)){
            System.out.println("Student is not found in the archive");
        }
        else if(!courseAvailability(cid)){
            System.out.println("Course is not found in the archive");
        }
        else if(enrollmentAvailability(sid,cid,semester)){
            System.out.println("Enrollment has already taken place");
        }
        else{
            StudentEnrollment enrollment = new StudentEnrollment(sid,cid,semester);
            listOfEnrollments.add(enrollment);
        }
    }

    @Override
    public void updateExisting(String sid, String oldCid, String newCid, String semester) {
        if(!studentAvailability(sid)){
            System.out.println("STUDENT IS NOT FOUND IN THE ARCHIVE");
        }
        else if(!courseAvailability(oldCid)){
            System.out.println("COURSE IS NOT FOUND IN THE ARCHIVE");
        }
        else if(!enrollmentAvailability(sid,oldCid,semester)){
            System.out.println("ENROLLMENT IS NOT FOUND IN THE ARCHIVE");
        }
        else{
            ListIterator<StudentEnrollment> list = listOfEnrollments.listIterator();
            for (StudentEnrollment listOfEnrollment : listOfEnrollments) {
                list.next();
                if (listOfEnrollment.getSID().equalsIgnoreCase(sid) &&
                        listOfEnrollment.getCID().equalsIgnoreCase(oldCid) &&
                        listOfEnrollment.getSemester().equalsIgnoreCase(semester)) {
                    list.remove();
                    Add(sid, newCid, semester);
                }
            }
        }
    }

    @Override
    public void Delete(String sid, String cid, String semester) {
        if(!studentAvailability(sid)){
            System.out.println("Student is not found in archive");
        }
        else if(!courseAvailability(cid)){
            System.out.println("Course is not found in the archive");
        }
        else if(!enrollmentAvailability(sid,cid,semester)){
            System.out.println("Enrollment is not found in the archive");
        }
        ListIterator<StudentEnrollment> list = listOfEnrollments.listIterator();
        for (StudentEnrollment listOfEnrollment : listOfEnrollments) {
            list.next();
            if (listOfEnrollment.getSID().equalsIgnoreCase(sid) &&
                    listOfEnrollment.getCID().equalsIgnoreCase(cid) &&
                    listOfEnrollment.getSemester().equalsIgnoreCase(semester)) {
                list.remove();
                System.out.println("Successfully Removed!");
            }

        }
    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

    }

    public boolean studentAvailability(String SID) {
        int studentChecker = 0;
        while (ListManagement.listOfStudents.size() > studentChecker) {
            if (ListManagement.listOfStudents.get(studentChecker).getStudentID().equalsIgnoreCase(SID)) {
                return true;
            } else {
                studentChecker = studentChecker + 1;
            }
        }
        return false;
    }
    public boolean courseAvailability(String CID) {
        int courseChecker = 0;
        while (ListManagement.listOfCourses.size() > courseChecker) {
            if (ListManagement.listOfCourses.get(courseChecker).getCourseID().equalsIgnoreCase(CID)) {
                return true;
            } else {
                courseChecker = courseChecker + 1;
            }
        }
        return false;
    }
    public boolean enrollmentAvailability(String sid, String cid, String Semester) {
        int enrollmentChecker = 0;
        while (StudentEnrollmentManager.listOfEnrollments.size() > enrollmentChecker) {
            if (StudentEnrollmentManager.listOfEnrollments.get(enrollmentChecker).getSID().equalsIgnoreCase(sid) &&
                    StudentEnrollmentManager.listOfEnrollments.get(enrollmentChecker).getCID().equalsIgnoreCase(cid) &&
                    StudentEnrollmentManager.listOfEnrollments.get(enrollmentChecker).getSemester().equalsIgnoreCase(Semester)) {
                return true;
            } else {
                enrollmentChecker = enrollmentChecker + 1;
            }
        }
        return false;
    }
    public boolean studentIDValidate(String sid, String firstChar){
        String newSID = sid.toUpperCase();
        if(!newSID.matches("^[A-Z]\\d{6}")){
            System.out.println("SID DOES NOT FOLLOW GIVEN PATTERN");
            return false;
        }
        else if(!sid.split("")[0].equalsIgnoreCase(firstChar)){
            System.out.println("SID IS NOT FOR STUDENT");
            return false;
        }
        else {
            return true;
        }

    }
    public boolean courseIDValidate(String cid){
        String newCID = cid.toUpperCase();
        if (newCID.matches("^[A-Z]{3}\\d{4}")||newCID.matches("^[A-Z]{4}\\d{4}")){
            return true;
        }
        else {
            System.out.println("CID DOES NOT FOLLOW GIVEN PATTERN");
            return false;
        }

    }
    public boolean semesterValidate(String semester){
        String newSemester = semester.toUpperCase();
        if(!newSemester.matches("^\\d{4}[A-Z]")){
            System.out.println("INPUT DOES NOT FOLLOW GIVEN PATTERN");
            return false;
        }
        else {
            return true;
        }
    }
    public boolean manuallyAddStudent(String input) {
        if (!input.contains(",")) {
            System.out.println("PLEASE ENTER VALUE WITH COMMA IN BETWEEN");
            return false;

        }
        else if (!studentIDValidate(input.split(",")[0], "S")){
            System.out.println("INVALID STUDENT'S ID");
            return false;

        }
        else if(studentAvailability(input.split(",")[0])){
            System.out.println("STUDENT ALREADY EXIST");
            return false;
        }
        else {
            System.out.println("PASSED VALIDATION");
            Student student = new Student(input.split(",")[0], input.split(",")[1], input.split(",")[2]);
            ListManagement.listOfStudents.add(student);
            return true;
        }
    }
    public boolean manuallyAddCourse(String input) {
        if (!input.contains(",")) {
            System.out.println("PLEASE ENTER VALUE WITH COMMA IN BETWEEN");
            return false;

        } else if (!courseIDValidate((input.split(",")[0]))){
            System.out.println("INVALID COURSE'S ID");
            return false;
        }
        else if(courseAvailability(input.split(",")[0])){
            System.out.println("COURSE ALREADY EXIST");
            return false;
        }
        else {
            System.out.println("PASSED VALIDATION");
            Course course = new Course(input.split(",")[0], input.split(",")[1], input.split(",")[2]);
            ListManagement.listOfCourses.add(course);
            return true;
        }
    }
    public boolean manuallyAddEnrollment(String input){
        if(!input.contains(",")){
            System.out.println("PLEASE ENTER VALUE WITH COMMA IN BETWEEN");
            return false;
        }
        else if(!studentAvailability(input.split(",")[0])&&!studentIDValidate(input.split(",")[0], "S")) {
            System.out.println("STUDENT EITHER HAVE NOT YET BEEN ADDED OR EXISTED");
            return false;
        }
        else if(!courseAvailability(input.split(",")[1])&&!courseIDValidate(input.split(",")[1])){
            System.out.println("COURSE EITHER HAVE NOT YET BEEN ADDED OR EXISTED");
            return false;
        }
        else{
            System.out.println("PASSED VALIDATION");
            StudentEnrollment studentEnrollment = new StudentEnrollment(input.split(",")[0], input.split(",")[1], input.split(",")[2]);
            StudentEnrollmentManager.listOfEnrollments.add(studentEnrollment);
            return true;
        }
    }


}

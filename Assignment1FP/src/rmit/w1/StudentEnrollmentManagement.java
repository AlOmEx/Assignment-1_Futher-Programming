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
    public void Update(String sid, String oldCid, String newCid, String semester) {
        if(!studentAvailability(sid)){
            System.out.println("Student is not found in archive");
        }
        else if(!courseAvailability(oldCid)){
            System.out.println("Course is not found in the archive");
        }
        else if(!enrollmentAvailability(sid,oldCid,semester)){
            System.out.println("Enrollment is not found in the archive");
        }
        else{
            ListIterator<StudentEnrollment> list = listOfEnrollments.listIterator();
            for (int i = 0; i < listOfEnrollments.size(); i++) {
                list.next();
                if (listOfEnrollments.get(i).getSID().equalsIgnoreCase(sid) &&
                        listOfEnrollments.get(i).getCID().equalsIgnoreCase(oldCid) &&
                        listOfEnrollments.get(i).getSemester().equalsIgnoreCase(semester)) {
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
        for (int i = 0; i < listOfEnrollments.size(); i++) {
            list.next();
            if (listOfEnrollments.get(i).getSID().equalsIgnoreCase(sid) &&
                    listOfEnrollments.get(i).getCID().equalsIgnoreCase(cid) &&
                    listOfEnrollments.get(i).getSemester().equalsIgnoreCase(semester)) {
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

}

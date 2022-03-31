package rmit.w1;

import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.IntStream;

public class StudentEnrollmentManagement implements StudentEnrollmentManager {
    public StudentEnrollmentManagement() {
        super();
    }

    @Override
    public void Add(String sid, String cid, String semester) {
        //check if the student is in the Student List
        if (!studentAvailability(sid)) {
            System.out.println("STUDENT IS NOT FOUND IN THE ARCHIVE");
        }

        //check if the course is found in the Course List
        else if (!courseAvailability(cid)) {
            System.out.println("COURSE IS NOT FOUND IN THE ARCHIVE");
        }

        //check if the enrollment has already take place
        else if (enrollmentAvailability(sid, cid, semester)) {
            System.out.println("ENROLLMENT HAS ALREADY TAKEN PLACE");
        }

        //if all conditions above has passed, continue to add student to Enrollment List
        else {
            System.out.println("SUCCESSFULLY ADDED");
            StudentEnrollment enrollment = new StudentEnrollment(sid, cid, semester);
            listOfEnrollments.add(enrollment);
        }
    }

    @Override
    public void Update() {
        Scanner scanner = new Scanner(System.in);
        while (true){

            //Ask user for ID
            System.out.println("PLEASE ENTER YOUR SID");
            String userID = scanner.nextLine();
            for (int user = 0; user < ListManagement.listOfStudents.size(); user++) {
                if(ListManagement.listOfStudents.get(user).getStudentID().equalsIgnoreCase(userID.toUpperCase())){
                    System.out.println("WELCOME"+" "+ ListManagement.listOfStudents.get(user).getStudentName().toUpperCase());
                    System.out.println("\n");
                }
            }

            //Print out all courses based on user's ID
            System.out.println("YOUR CURRENT COURSES ARE: ");
            int position;
            for (int i = 0; i < StudentEnrollmentManager.listOfEnrollments.size(); i++) {
                if(StudentEnrollmentManager.listOfEnrollments.get(i).getSID().equalsIgnoreCase(userID)){
                    position = i;
                    for (int j = 0; j < ListManagement.listOfCourses.size(); j++) {
                        if(StudentEnrollmentManager.listOfEnrollments.get(position).getCID().equalsIgnoreCase(ListManagement.listOfCourses.get(j).getCourseID())){
                            System.out.println(ListManagement.listOfCourses.get(j).getCourseID()+" "+ListManagement.listOfCourses.get(j).getCourseName());
                        }
                    }
                }
            }

            //Main menu
            System.out.println("\n");
            System.out.println("CHOOSE THE FOLLOWING METHODS");
            System.out.println("1. ADD AN ENROLLMENT");
            System.out.println("2. DELETE AN ENROLLMENT");
            System.out.println("3. RETURN TO MAIN MENU");
            System.out.println("YOUR OPTION IS: ");
            String userInnerChoice = scanner.nextLine();
            if(userInnerChoice.equalsIgnoreCase("1")){
                //Ask user for input
                System.out.println("PLEASE ENTER THE FOLLOWING VALUES WITH COMMA IN BETWEEN");
                System.out.println("COURSE ID, SEMESTER");
                String userAddInput = scanner.nextLine();
                //Check student's ID patterns
                if(!studentIDValidate(userID,"S")){
                    break;
                }
                //Check course's ID patterns
                else if(!courseIDValidate(userAddInput.split(",")[0])){
                    break;
                }
                //Check semester pattern
                else if(!semesterValidate(userAddInput.split(",")[1])){
                    break;
                }

                //If all conditions above passed, use Add method to add in Enrollment List
                else {
                    Add(userID,userAddInput.split(",")[0],userAddInput.split(",")[1]);
                    continue;
                }

            }
            if (userInnerChoice.equalsIgnoreCase("2")){
                //Ask user for input
                System.out.println("PLEASE ENTER THE FOLLOWING VALUES WITH COMMA IN BETWEEN");
                System.out.println("COURSE ID, SEMESTER");
                String userAddInput = scanner.nextLine();

                //Check student's ID patterns
                if(!studentIDValidate(userID,"S")){
                    break;
                }
                //Check course's ID patterns
                else if(!courseIDValidate(userAddInput.split(",")[0])){
                    break;
                }
                //Check semester pattern
                else if(!semesterValidate(userAddInput.split(",")[1])){
                    break;
                }
                //If all conditions above passed, use Delete method to delete enrollment
                else {
                    System.out.println("SUCCESSFULLY DELETE ENROLLMENT");
                    Delete(userID,userAddInput.split(",")[0],userAddInput.split(",")[1]);
                    continue;
                }

            }
            if (userInnerChoice.equalsIgnoreCase("3")){
                break;
            }
            else {
                System.out.println("INVALID INPUT. PLEASE TRY AGAIN");
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
    public void getOne(String sid, String cid, String semester) {
        int count = 0;
        assert sid != null;
        assert cid != null;
        assert semester != null;
        int position;
        // If parameter String sid is empty, run code for getting one Course from one Semester
        if (sid.isBlank()) {
            if (!courseIDValidate(cid)) {
                System.out.println("CID VALIDATE ERROR");
            } else if (!semesterValidate(semester)) {
                System.out.println("SEMESTER VALIDATE ERROR");
            } else if (!courseAvailability(cid)) {
                System.out.println("COURSE AVAILABILITY ERROR");
            } else {
                try {
                    while (true) {
                        if (listOfEnrollments.get(count).getSemester().equalsIgnoreCase(semester) && listOfEnrollments.get(count).getCID().equalsIgnoreCase(cid)) {
                            position = count;
                            int finalPosition = position;
                            IntStream.range(0, ListManagement.listOfCourses.size()).filter(course -> listOfEnrollments.get(finalPosition).getCID().equalsIgnoreCase(ListManagement.listOfCourses.get(course).getCourseID())).mapToObj(course -> ListManagement.listOfCourses.get(course).getCourseID() + " " + ListManagement.listOfCourses.get(course).getCourseName()).forEach(System.out::println);
                            break;
                        } else {
                            count++;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("HAVE REACHED THE END OF THE LIST AND NOT FOUND THE COURSE IN THE SEMESTER");
                }
            }
        }
        // If parameter String cid is empty, run code for getting one Student from one Semester
        else if (cid.isBlank()) {
            if (!studentIDValidate(sid, "S")) {
                System.out.println("SID VALIDATE ERROR");
            } else if (!semesterValidate(semester)) {
                System.out.println("SEMESTER VALIDATE ERROR");
            } else if (!studentAvailability(sid)) {
                System.out.println("STUDENT AVAILABILITY ERROR");
            } else {
                try {
                    while (true) {
                        if (listOfEnrollments.get(count).getSemester().equalsIgnoreCase(semester) && listOfEnrollments.get(count).getSID().equalsIgnoreCase(sid)) {
                            position = count;
                            for (int student = 0; student < ListManagement.listOfStudents.size(); student++) {
                                if (ListManagement.listOfStudents.get(student).getStudentID().equalsIgnoreCase(listOfEnrollments.get(position).getSID())) {
                                    System.out.println(ListManagement.listOfStudents.get(student).getStudentID() + " " + ListManagement.listOfStudents.get(student).getStudentName());
                                    break;
                                }
                            }
                            break;
                        } else {
                            count++;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("HAVE REACHED THE END OF THE LIST AND NOT FOUND THE STUDENT IN THE SEMESTER");
                }
            }
        }
        // If parameter String semester is empty, run code for getting one Student from one Course
        else if (semester.isBlank()) {
            if (!studentIDValidate(sid, "S")) {
                System.out.println("SID VALIDATE ERROR");
            }
            if (!courseIDValidate(cid)) {
                System.out.println("CID VALIDATE ERROR");
            } else if (!studentAvailability(sid)) {
                System.out.println("STUDENT AVAILABILITY ERROR");
            } else if (!courseAvailability(cid)) {
                System.out.println("COURSE AVAILABILITY ERROR");
            } else {
                try {
                    while (true) {
                        if (listOfEnrollments.get(count).getSID().equalsIgnoreCase(sid) && listOfEnrollments.get(count).getCID().equalsIgnoreCase(cid)) {
                            position = count;
                            for (int i = 0; i < listOfEnrollments.size(); i++) {
                                if (ListManagement.listOfStudents.get(i).getStudentID().equalsIgnoreCase(listOfEnrollments.get(position).getSID())) {
                                    System.out.println(ListManagement.listOfStudents.get(i).getStudentID() + " " + ListManagement.listOfStudents.get(i).getStudentName());
                                    break;
                                }
                            }
                            break;
                        } else {
                            count++;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("HAVE REACHED THE END OF THE LIST AND NOT FOUND THE STUDENT IN THE COURSE");
                }
            }

        }
        // If all parameters are filled, run code for getting one Enrollment
        else if (!sid.isBlank() && !cid.isBlank() && !semester.isBlank()) {
            for (StudentEnrollment listOfEnrollment : listOfEnrollments) {
                if (listOfEnrollment.getSID().equalsIgnoreCase(sid) &&
                        listOfEnrollment.getCID().equalsIgnoreCase(cid) &&
                        listOfEnrollment.getSemester().equalsIgnoreCase(semester)) {
                    System.out.println();
                }
            }
        }
    }

    @Override
    public void getAll(String sid, String cid, String semester) {

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
        // Check if input from user has a comma in between inputs
        if (!input.contains(",")&&!input.matches("^[,][2]")) {
            System.out.println("PLEASE ENTER VALUE WITH COMMA IN BETWEEN");
            return false;

        }
        // Check student ID follows the pattern
        else if (!studentIDValidate(input.split(",")[0], "S")){
            System.out.println("INVALID STUDENT'S ID");
            return false;

        }
        // Check if student is in the List
        else if(studentAvailability(input.split(",")[0])){
            System.out.println("STUDENT ALREADY EXIST");
            return false;
        }
        //If all conditions passed, add student into Student List
        else {
            System.out.println("PASSED VALIDATION");
            Student student = new Student(input.split(",")[0], input.split(",")[1], input.split(",")[2]);
            ListManagement.listOfStudents.add(student);
            return true;
        }
    }
    public boolean manuallyAddCourse(String input) {
        // Check if input from user has a comma in between inputs
        if (!input.contains(",")&&!input.matches("^[,][2]")) {
            System.out.println("PLEASE ENTER VALUE WITH COMMA IN BETWEEN");
            return false;

        }
        // Check course ID follows the pattern
        else if (!courseIDValidate((input.split(",")[0]))){
            System.out.println("INVALID COURSE'S ID");
            return false;
        }
        // Check if course is in Course List
        else if(courseAvailability(input.split(",")[0])){
            System.out.println("COURSE ALREADY EXIST");
            return false;
        }

        // If all conditions passed, add course into Course List
        else {
            System.out.println("PASSED VALIDATION");
            Course course = new Course(input.split(",")[0], input.split(",")[1], input.split(",")[2]);
            ListManagement.listOfCourses.add(course);
            return true;
        }
    }
    public boolean manuallyAddEnrollment(String input){
        // Check if input from user has a comma in between inputs
        if(!input.contains(",")&&!input.matches("^[,][2]")){
            System.out.println("PLEASE ENTER VALUE WITH COMMA IN BETWEEN");
            return false;
        }
        // Check if student existed and student's ID is validated
        else if(!studentAvailability(input.split(",")[0])&&!studentIDValidate(input.split(",")[0], "S")) {
            System.out.println("STUDENT EITHER HAVE NOT YET BEEN ADDED OR EXISTED");
            return false;
        }
        // Check if course existed and course's ID is validated
        else if(!courseAvailability(input.split(",")[1])&&!courseIDValidate(input.split(",")[1])){
            System.out.println("COURSE EITHER HAVE NOT YET BEEN ADDED OR EXISTED");
            return false;
        }
        // If all conditions passed, add Enrollment to Enrollment List
        else{
            System.out.println("PASSED VALIDATION");
            StudentEnrollment studentEnrollment = new StudentEnrollment(input.split(",")[0], input.split(",")[1], input.split(",")[2]);
            StudentEnrollmentManager.listOfEnrollments.add(studentEnrollment);
            return true;
        }
    }

}

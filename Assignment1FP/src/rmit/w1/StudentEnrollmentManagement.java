package rmit.w1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
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
        //check if the student is in the Student List
        if (!studentAvailability(sid)) {
            System.out.println("STUDENT IS NOT FOUND IN THE ARCHIVE");
        }

        //check if the course is found in the Course List
        else if (!courseAvailability(cid)) {
            System.out.println("COURSE IS NOT FOUND IN THE ARCHIVE");
        }

        //check if the enrollment exist or not
        else if (!enrollmentAvailability(sid, cid, semester)) {
            System.out.println("ENROLLMENT IS NOT FOUND IN THE ARCHIVE");
        }

        //if all conditions above passed, remove enrollment from the Enrollment List
        listOfEnrollments.removeIf(items -> items.getSID().equalsIgnoreCase(sid) &&
                items.getCID().equalsIgnoreCase(cid) &&
                items.getSemester().equalsIgnoreCase(semester));
    }

    @Override
    public void getOne(String sid, String cid, String semester) {
        if(enrollmentAvailability(sid, cid, semester)){
            IntStream.range(0, ListManagement.listOfStudents.size()).filter(student -> ListManagement.listOfStudents.get(student).getStudentID().equalsIgnoreCase(sid)).mapToObj(student -> ListManagement.listOfStudents.get(student).getStudentID() + " " + ListManagement.listOfStudents.get(student).getStudentName()).forEach(System.out::println);
            IntStream.range(0, ListManagement.listOfCourses.size()).filter(course -> ListManagement.listOfCourses.get(course).getCourseID().equalsIgnoreCase(cid)).mapToObj(course -> ListManagement.listOfCourses.get(course).getCourseID() + " " + ListManagement.listOfCourses.get(course).getCourseName()).forEach(System.out::println);
            System.out.println("Semester"+" "+ semester);
            System.out.println("\n");
        }
        else {
            System.out.println("ENROLLMENT NOT FOUND");
        }


    }

    @Override
    public void getAll(String sid, String cid, String semester) {
        Scanner scanner1 = new Scanner(System.in);
        assert sid != null;
        assert cid != null;
        assert semester != null;
        ArrayList<String> temporary = new ArrayList<>();
        ArrayList<String> Student = new ArrayList<>();
        ArrayList<String> Course = new ArrayList<>();
        ArrayList<String> courseSem = new ArrayList<>();
        if(sid.isBlank()&&cid.isBlank()&&semester.isBlank()){
            System.out.println("ENROLLMENT'S INFORMATION");
            for (int i = 0; i < StudentEnrollmentManager.listOfEnrollments.size(); i++) {
                System.out.printf("%-25s",StudentEnrollmentManager.listOfEnrollments.get(i).getSID());
                System.out.printf("%-30s",StudentEnrollmentManager.listOfEnrollments.get(i).getCID());
                System.out.println(StudentEnrollmentManager.listOfEnrollments.get(i).getSemester());
            }
        }
        // If parameters String sid and String cid both are empty, run code for finding All Courses of a Semester
        else if (sid.isBlank() && cid.isBlank()&&!semester.isBlank()) {
            try {

                System.out.println("YOUR SEMESTER HAS THESE COURSES: ");
                for (int course = 0; course < listOfEnrollments.size(); course++) {
                    for (int j = 0; j < listOfEnrollments.size(); j++) {
                        // Loop through Enrollment List with filtering the same course
                        if (listOfEnrollments.get(course).getSemester().equalsIgnoreCase(semester) && !temporary.contains(listOfEnrollments.get(course).getCID())) {
                            temporary.add(listOfEnrollments.get(course).getCID());
                            // Add Course to the first temporary ArrayList
                        }
                    }

                }
                for (int courseName = 0; courseName < ListManagement.listOfCourses.size(); courseName++) {
                    for (String s : temporary) {
                        if (ListManagement.listOfCourses.get(courseName).getCourseID().equalsIgnoreCase(s)) {
                            System.out.println(ListManagement.listOfCourses.get(courseName).getCourseID() + " " + ListManagement.listOfCourses.get(courseName).getCourseName());
                            String lineOfData = ListManagement.listOfCourses.get(courseName).getCourseID() + "," + ListManagement.listOfCourses.get(courseName).getCourseName() + "," + ListManagement.listOfCourses.get(courseName).getNumOfCre();
                            courseSem.add(lineOfData);
                            // Add Course's Information into the second temporary ArrayList
                        }
                    }


                }
            }
            // If reaches to end of the list and have not found a match, print out an error
            catch (IndexOutOfBoundsException e) {
                System.out.println("HAVE REACHED THE END OF THE LIST AND NOT FOUND ANY COURSE");
            }
            while (true) {
                // Ask if user choose to export data to CSV file
                System.out.println("EXPORT DATA TO CSV FILE?");
                System.out.println("1. YES");
                System.out.println("2. NO");
                String choice = scanner1.nextLine();
                if (choice.equalsIgnoreCase("1")) {
                    // Write Courses information to CSV file
                    try {
                        String fileName = "List of All courses in semester" + " " + semester.toUpperCase();
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"));
                        writer.write("CourseID" + "," + "CourseName" + "," + "NumberOfCredit" + "\n");
                        for (String s : courseSem) {
                            writer.write(s);
                        }
                        writer.close();
                        System.out.println("GENERATED SUCCESSFULLY");
                        System.out.println("\n");
                        break;
                    } catch (Exception e) {
                        System.out.println("ERROR");
                        System.out.println(e);
                        break;
                    }
                }
                if (choice.equalsIgnoreCase("2")) {
                    System.out.println("FINISHED");
                    break;
                } else {
                    System.out.println("INVALID INPUT. PLEASE TRY AGAIN");
                }
            }
        }
        // If parameter String sid is empty, run code to find All Courses that a Student has enrolled in
        else if (cid.isBlank()) {
            try {
                System.out.println("YOUR STUDENT IS ENROLLED IN: ");
                for (StudentEnrollment listOfEnrollment : listOfEnrollments) {
                    if (listOfEnrollment.getSID().equalsIgnoreCase(sid) && listOfEnrollment.getSemester().equalsIgnoreCase(semester)) {
                        // Run through Enrollment List to find Object that matches with user's input
                        for (int courseName = 0; courseName < ListManagement.listOfCourses.size(); courseName++) {
                            if (ListManagement.listOfCourses.get(courseName).getCourseID().equalsIgnoreCase(listOfEnrollment.getCID())) {
                                // Run through Course List to check for a match and get Course Name and ID
                                System.out.println(ListManagement.listOfCourses.get(courseName).getCourseID() + " " + ListManagement.listOfCourses.get(courseName).getCourseName());
                                String lineOfData = ListManagement.listOfCourses.get(courseName).getCourseID() + "," + ListManagement.listOfCourses.get(courseName).getCourseName() + "," + ListManagement.listOfCourses.get(courseName).getNumOfCre();
                                // Add into a temporary ArrayList
                                Course.add(lineOfData);
                            }
                        }
                    }
                }
            }
            // If reaches to end of the list and have not found a match, print out an error
            catch (IndexOutOfBoundsException e) {
                System.out.println("HAVE REACHED THE END OF THE LIST AND NOT FOUND ANY COURSE");
            }
            while (true) {
                // Ask if user choose to export data to CSV file
                System.out.println("EXPORT DATA TO CSV FILE?");
                System.out.println("1. YES");
                System.out.println("2. NO");
                String choice = scanner1.nextLine();
                if (choice.equalsIgnoreCase("1")) {
                    // Write Courses information to CSV file
                    try {
                        String fileName = "List of All courses belongs to" + " " + sid.toUpperCase() + " " + "in semester" + semester.toUpperCase();
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"));
                        writer.write("CourseID" + "," + "CourseName" + "," + "NumberOfCredit" + "\n");
                        for (String s : Course) {
                            writer.write(s);
                        }
                        writer.close();
                        System.out.println("GENERATED SUCCESSFULLY");
                        System.out.println("\n");
                        break;
                    } catch (Exception e) {
                        System.out.println("ERROR");
                        System.out.println(e);
                        break;
                    }
                }
                if (choice.equalsIgnoreCase("2")) {
                    System.out.println("FINISHED");
                    break;
                } else {
                    System.out.println("INVALID INPUT. PLEASE TRY AGAIN");
                }
            }


        }
        // If parameter String sid is empty, run cod to find All Students belongs in a Course
        else if (sid.isBlank()) {
            try {
                System.out.println("YOUR COURSE HAS THESE STUDENTS: ");
                for (StudentEnrollment listOfEnrollment : listOfEnrollments) {
                    if (listOfEnrollment.getCID().equalsIgnoreCase(cid) && listOfEnrollment.getSemester().equalsIgnoreCase(semester)) {
                        // Run through Enrollment List to find Object that matches with user's input
                        for (int studentName = 0; studentName < ListManagement.listOfStudents.size(); studentName++) {
                            if (ListManagement.listOfStudents.get(studentName).getStudentID().equalsIgnoreCase(listOfEnrollment.getSID())) {
                                // Run through Student List to check for a match and get Student Name and ID
                                System.out.println(ListManagement.listOfStudents.get(studentName).getStudentID() + " " + ListManagement.listOfStudents.get(studentName).getStudentName());
                                String lineOfData = ListManagement.listOfStudents.get(studentName).getStudentID() + "," + ListManagement.listOfStudents.get(studentName).getStudentName() + "," + ListManagement.listOfStudents.get(studentName).getBirthDate();
                                // Add into a temporary ArrayList
                                Student.add(lineOfData);
                            }
                        }
                    }
                }
            }
            // If reaches to end of the list and have not found a match, print out an error
            catch (IndexOutOfBoundsException e) {
                System.out.println("HAVE REACHED THE END OF THE LIST AND NOT FOUND ANY STUDENT");
            }
            while (true) {
                // Ask if user choose to export data to CSV file
                System.out.println("EXPORT DATA TO CSV FILE?");
                System.out.println("1. YES");
                System.out.println("2. NO");
                String choice = scanner1.nextLine();
                if (choice.equalsIgnoreCase("1")) {
                    try {
                        // Write Students information to CSV file
                        String fileName = "List of Students of course" + " " + cid.toUpperCase() + "in semester" + " " + semester.toUpperCase();
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"));
                        writer.write("StudentID" + "," + "StudentName" + "," + "StudentBirthdate" + "\n");
                        for (String s : Student) {
                            writer.write(s + "\n");
                        }
                        writer.close();
                        System.out.println("GENERATED SUCCESSFULLY");
                        System.out.println("\n");
                        break;
                    } catch (Exception e) {
                        System.out.println("ERROR");
                        System.out.println(e);
                        break;
                    }
                }
                if (choice.equalsIgnoreCase("2")) {
                    System.out.println("FINISHED");
                    break;
                } else {
                    System.out.println("INVALID INPUT. PLEASE TRY AGAIN");
                }
            }
        }


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

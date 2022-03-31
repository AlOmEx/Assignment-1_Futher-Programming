package rmit.w1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("WELCOME TO THE STUDENT ENROLLMENT MANAGEMENT SYSTEM");
        StudentEnrollmentManagement systemRun = new StudentEnrollmentManagement();
        Scanner scanner = new Scanner(System.in);
        String userSelection;

        while (true){
            System.out.println("PLEASE CHOOSE ONE OF THE OPTION TO POPULATE THE SYSTEM: ");
            System.out.println("1. MANUALLY INPUT DATA INTO THE SYSTEM");
            System.out.println("2. SYSTEM PROCEED TO POPULATE AUTOMATICALLY WITH GIVEN DATA");
            System.out.println("3. CONTINUE WITH MANAGEMENT");
            System.out.println("YOUR OPTION IS: ");
            userSelection = scanner.nextLine();
            if(userSelection.equals("1")){
                while (true){
                    System.out.println("PLEASE CHOOSE YOUR OPTIONS: ");
                    System.out.println("1. ADD VALUE TO STUDENT");
                    System.out.println("2. ADD VALUE TO COURSE");
                    System.out.println("3. ADD VALUE TO ENROLLMENT");
                    System.out.println("4. RETURN TO MAIN MENU");
                    System.out.println("YOUR OPTION IS: ");
                    String innerSelection = scanner.nextLine();
                    if(innerSelection.equalsIgnoreCase("1")){
                        while (true){
                            System.out.println("CURRENT LIST: ");
                            for (int i = 0; i < ListManagement.listOfStudents.size(); i++) {
                                System.out.printf("%-25s",ListManagement.listOfStudents.get(i).getStudentID());
                                System.out.printf("%-30s",ListManagement.listOfStudents.get(i).getStudentName());
                                System.out.println(ListManagement.listOfStudents.get(i).getBirthDate());

                            }
                            System.out.println("PLEASE ENTER STUDENT'S INFORMATION");
                            System.out.println("WHICH INCLUDE THE FOLLOWING: STUDENT'S ID, STUDENT'S NAME, STUDENT'S BIRTHDATE (COMMA BETWEEN EACH VALUE)");
                            String inputForStudent = scanner.nextLine();
                            if (!systemRun.manuallyAddStudent(inputForStudent)){
                                break;
                            }
                            System.out.println("DO YOU WISH TO CONTINUE ADDING MORE VALUE? Y/N");
                            String confirmation = scanner.nextLine();
                            if (confirmation.equalsIgnoreCase("Y")){
                                continue;
                            }
                            if(confirmation.equalsIgnoreCase("N")){
                                System.out.println("SUCCESSFULLY ADDED ALL STUDENTS");
                                break;
                            }
                        }
                        continue;
                    }
                    if(innerSelection.equalsIgnoreCase("2")) {
                        while (true) {
                            System.out.println("CURRENT LIST: ");
                            for (int i = 0; i < ListManagement.listOfCourses.size(); i++) {
                                System.out.printf("%-25s",ListManagement.listOfCourses.get(i).getCourseID());
                                System.out.printf("%-40s",ListManagement.listOfCourses.get(i).getCourseName());
                                System.out.println(ListManagement.listOfCourses.get(i).getNumOfCre());

                            }
                            System.out.println("PLEASE ENTER COURSE'S INFORMATION");
                            System.out.println("WHICH INCLUDE THE FOLLOWING: COURSE'S ID, COURSE'S NAME, NUMBER OF CREDIT (COMMA BETWEEN EACH VALUE)");
                            String inputForCourse = scanner.nextLine();
                            if (!systemRun.manuallyAddCourse(inputForCourse)) {
                                break;
                            }

                            System.out.println("DO YOU WISH TO CONTINUE ADDING MORE VALUE? Y/N");
                            String confirmation = scanner.nextLine();
                            if (confirmation.equalsIgnoreCase("Y")) {
                                continue;
                            }
                            if (confirmation.equalsIgnoreCase("N")) {
                                System.out.println("SUCCESSFULLY ADDED ALL COURSES");
                                break;
                            }
                        }
                        continue;
                    }
                    if(innerSelection.equalsIgnoreCase("3")){
                        while (true){
                            System.out.println("CURRENT LIST: ");
                            for (int i = 0; i < StudentEnrollmentManager.listOfEnrollments.size(); i++) {
                                System.out.printf("%-25s",StudentEnrollmentManager.listOfEnrollments.get(i).getSID());
                                System.out.printf("%-30s",StudentEnrollmentManager.listOfEnrollments.get(i).getCID());
                                System.out.println(StudentEnrollmentManager.listOfEnrollments.get(i).getSemester());
                            }
                            System.out.println("PLEASE ENTER ENROLLMENT'S INFORMATION");
                            System.out.println("WHICH INCLUDE THE FOLLOWING: STUDENT'S ID, COURSE ID, SEMESTER(COMMA BETWEEN EACH VALUE");
                            String inputForEnrollment = scanner.nextLine();
                            if(!systemRun.manuallyAddEnrollment(inputForEnrollment)){
                                break;
                            }
                            System.out.println("DO YOU WISH TO CONTINUE ADDING MORE VALUE? Y/N");
                            String confirmation = scanner.nextLine();
                            if (confirmation.equalsIgnoreCase("Y")) {
                                continue;
                            }
                            if (confirmation.equalsIgnoreCase("N")) {
                                System.out.println("SUCCESSFULLY ADDED ALL ENROLLMENTS");
                                break;
                            }
                        }
                        continue;
                    }
                    if(innerSelection.equalsIgnoreCase("4")){
                        break;
                    }
                }
                continue;
            }
            if (userSelection.equals("2")){
                try {
                    File sourceData = new File("default.csv");
                    Scanner fileReader = new Scanner(sourceData);
                    while (fileReader.hasNextLine()){

                        String data = fileReader.nextLine();
                        if(!systemRun.studentAvailability(data.split(",")[0])){
                            Student student = new Student(data.split(",")[0],data.split(",")[1],data.split(",")[2]);
                            ListManagement.listOfStudents.add(student);

                        }
                        if(!systemRun.courseAvailability(data.split(",")[3])){
                            Course course = new Course(data.split(",")[3], data.split(",")[4], data.split(",")[5]);
                            ListManagement.listOfCourses.add(course);

                        }
                        StudentEnrollment enrollment = new StudentEnrollment(data.split(",")[0],data.split(",")[3], data.split(",")[6]);
                        StudentEnrollmentManager.listOfEnrollments.add(enrollment);
                    }
                    fileReader.close();
                    System.out.println("GENERATED SUCCESSFULLY");
                    System.out.println("\n");
                    continue;
                }

                catch (FileNotFoundException e){
                    e.printStackTrace();
                    System.out.println("ERROR, FILE NOT FOUND");
                }

            }
            if(userSelection.equalsIgnoreCase("3")){
                System.out.println("STUDENT'S INFORMATION");
                for (int i = 0; i < ListManagement.listOfStudents.size(); i++) {
                    System.out.printf("%-25s",ListManagement.listOfStudents.get(i).getStudentID());
                    System.out.printf("%-30s",ListManagement.listOfStudents.get(i).getStudentName());
                    System.out.println(ListManagement.listOfStudents.get(i).getBirthDate());

                }
                System.out.println("\n");
                System.out.println("COURSE'S INFORMATION");
                for (int i = 0; i < ListManagement.listOfCourses.size(); i++) {
                    System.out.printf("%-25s",ListManagement.listOfCourses.get(i).getCourseID());
                    System.out.printf("%-40s",ListManagement.listOfCourses.get(i).getCourseName());
                    System.out.println(ListManagement.listOfCourses.get(i).getNumOfCre());

                }
                System.out.println("\n");
                System.out.println("ENROLLMENT'S INFORMATION");
                for (int i = 0; i < StudentEnrollmentManager.listOfEnrollments.size(); i++) {
                    System.out.printf("%-25s",StudentEnrollmentManager.listOfEnrollments.get(i).getSID());
                    System.out.printf("%-30s",StudentEnrollmentManager.listOfEnrollments.get(i).getCID());
                    System.out.println(StudentEnrollmentManager.listOfEnrollments.get(i).getSemester());
                }
                break;
            }
            else {
                System.out.println("INVALID INPUT. PLEASE TRY AGAIN");
            }

        }

    }
}

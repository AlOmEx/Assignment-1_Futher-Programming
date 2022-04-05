package com.company;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StudentEnrolmentManagementTest {
    StudentEnrollmentManagement systemRun = new StudentEnrollmentManagement();
    int currentList = StudentEnrollmentManagement.listOfEnrollments.size();
    Student student = new Student("S101312", "Alex Mike", "10/13/1998");
    Course course = new Course("PHYS1230", "Introductory Human Physiology", "4");
    Course course1 = new Course("COSC4030", "Theory of Computation", "5");
    Course course2 = new Course("COSC3321", "Artificial Intelligence", "3");


    @Test
    void add() {
        ListManagement.listOfStudents.add(student);
        ListManagement.listOfCourses.add(course);
        systemRun.Add("S101312", "PHYS1230", "2020C");
        System.out.println(currentList);
        int modifiedList = StudentEnrollmentManagement.listOfEnrollments.size();
        assertEquals(currentList, modifiedList - 1);
        currentList = StudentEnrollmentManagement.listOfEnrollments.size();
        systemRun.Add("s3838104", "COSC2040", "2020C");
        modifiedList = StudentEnrollmentManagement.listOfEnrollments.size();
        assertEquals(currentList, modifiedList);
        assertFalse(systemRun.enrollmentAvailability("s3838104", "COSC2040", "2020C"));

    }

    @Test
    void delete() {
        ListManagement.listOfStudents.add(student);
        ListManagement.listOfCourses.add(course);
        ListManagement.listOfCourses.add(course1);
        ListManagement.listOfCourses.add(course2);
        systemRun.Add("S101312", "PHYS1230", "2020C");
        currentList = StudentEnrollmentManagement.listOfEnrollments.size();
        systemRun.Add("S101312", "COSC4030", "2020C");
        int modifiedList;
        systemRun.Delete("S101312", "COSC4030", "2020C");
        modifiedList = StudentEnrollmentManagement.listOfEnrollments.size();
        assertEquals(modifiedList, currentList);
    }

    @Test
    void getOne() {
        ListManagement.listOfStudents.add(student);
        ListManagement.listOfCourses.add(course);
        systemRun.Add("S101312", "PHYS1230", "2020C");
        String data = systemRun.getOne("S101312", "PHYS1230", "2020C");
        assertTrue(data.contains("S101312") && data.contains("PHYS1230") && data.contains("2020C"));

    }


    @Test
    void enrollmentAvailability() {
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
        }

        catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("ERROR, FILE NOT FOUND");
        }
        String sid = "S101312";
        String cid = "COSC4030";
        String semester = "2020C";
        assertTrue(systemRun.enrollmentAvailability(sid,cid,semester));
    }

    @Test
    void studentIDValidate() {
        ListManagement.listOfStudents.add(student);
        String sid = "s383810";
        String sid1 = "s3sddsd38104";
        systemRun.studentIDValidate(sid);
        assertTrue(systemRun.studentIDValidate(sid));
        assertFalse(systemRun.studentIDValidate(sid1));
    }

    @Test
    void courseIDValidate() {
        String cid = "COSC2040";
        String cid1 = "24531122";
        assertTrue(systemRun.courseIDValidate(cid));
        assertFalse(systemRun.courseIDValidate(cid1));
    }

}
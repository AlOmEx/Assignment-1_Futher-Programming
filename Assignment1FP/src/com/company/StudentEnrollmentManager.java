package com.company;

import java.util.ArrayList;

public interface StudentEnrollmentManager {
    ArrayList<StudentEnrollment> listOfEnrollments = new ArrayList<>();
    void Add(String sid, String cid, String semester);
    void Update();
    void Delete(String sid, String cid, String semester);
    void getOne(String sid, String cid, String semester);
    void getAll(String sid, String cid, String semester);

}

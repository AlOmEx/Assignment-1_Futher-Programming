package rmit.w1;

import java.util.ArrayList;

public interface StudentEnrollmentManager {
    ArrayList<StudentEnrollment> listOfEnrollments = new ArrayList<>();
    void Add(String sid, String cid, String semester);
    void Update(String sid, String oldCid, String newCid, String semester);
    void Delete(String sid, String cid, String semester);
    void getOne();
    void getAll();

}

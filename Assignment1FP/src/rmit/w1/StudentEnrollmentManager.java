package rmit.w1;

import java.util.ArrayList;

public interface StudentEnrollmentManager {
    ArrayList<StudentEnrollment> listOfEnrollments = new ArrayList<>();
    void Add();
    void Update();
    void Delete();
    void getOne();
    void getAll();

}

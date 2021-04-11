package studentInfo;

import java.util.HashMap;
import java.util.Map;

public class StudentDirectory {
    private static final String DIR_BASENAME = "studentDir";
    private DataFile db;

    public StudentDirectory(){
        db = new DataFile.open(DIR_BASENAME);
    }

    public void add(Student student) {
        db.add(student.getId(), student);
    }

    public Student findById(String id) {
        return (Student) db.findBy(id);
    }


    public void close() {
        db.close();
    }

    public void remove() {
        db.deleteFile();
    }
}

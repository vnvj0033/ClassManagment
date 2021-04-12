package studentInfo;

import db.DataFile;

import java.io.IOException;

public class StudentDirectory {
    private static final String DIR_BASENAME = "studentDir";
    private DataFile db;

    public StudentDirectory() throws IOException {
        db = DataFile.open(DIR_BASENAME);
    }

    public void add(Student student) throws IOException {
        db.add(student.getId(), student);
    }

    public Student findById(String id) throws IOException {
        return (Student) db.findBy(id);
    }


    public void close() throws IOException {
        db.close();
    }

    public void remove() {
        db.deleteFiles();
    }
}

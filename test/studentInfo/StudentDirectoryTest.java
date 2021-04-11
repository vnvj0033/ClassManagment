package studentInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentDirectoryTest {
    private StudentDirectory dir;

    @BeforeEach
    void setUp() throws IOException {
        dir = new StudentDirectory();
    }

    @Test
    void testDown() {
        dir.close();
        dir.remove();
    }

    @Test
    void testRandomAccess() throws  IOException{
        final int numberOfStudent = 10;
        for (int i = 0; i < numberOfStudent; i++)
            addStudent(dir, i);
        dir.close();

        dir = new StudentDirectory();
        for (int i = 0; i < numberOfStudent; i++)
            verifyStudentLookup(dir, i);
    }

    private void addStudent(StudentDirectory directory, int i) throws IOException {
        String id = ""+i;
        Student student = new Student(id);
        student.setId(id);
        student.addCredits(i);
        directory.add(student);
    }

    private void verifyStudentLookup(StudentDirectory directory, int i) throws IOException {
        String id = ""+i;
        Student student = directory.findById(id);
        assertEquals(id, student.getLastName());
        assertEquals(id, student.getId());
        assertEquals(i, student.getCredits());
    }

    @Test
    void testStoreAndRetrieve() throws IOException {
        final int numberOfStudents = 10;

        for (int i = 0; i <numberOfStudents; i++ )
            addStudent(dir, i);

        for (int i = 0; i < numberOfStudents; i++)
            verifyStudentLookup(dir, i);
    }


}

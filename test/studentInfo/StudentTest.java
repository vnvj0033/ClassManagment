package studentInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    void testCreate() {
        String name = "John";
        Student student = new Student(name);
        assertEquals(student.getName(), name);

        name = "상엽";
        student = new Student(name);
        assertEquals(student.getName(), name);
    }

    @Test
    void testStudentStatus() {
        Student student = new Student("a");
        assertEquals(0, student.getCredit());
        assertFalse(student.isFullTime());

        student.addCredit(3);
        assertEquals(3, student.getCredit());
        assertFalse(student.isFullTime());

        student.addCredit(4);
        assertEquals(7, student.getCredit());
        assertFalse(student.isFullTime());

        student.addCredit(5);
        assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredit());
        assertTrue(student.isFullTime());
    }

    @Test
    void testInState() {
        Student student = new Student("상엽");

        student.setState("계룡");
        assertFalse(student.isInState());

        student.setState("대전");
        assertTrue(student.isInState());
    }

    private static final double GRADE_TOLERANCE = 0.05;
    @Test
    void testCalculateGpa() {
        Student student = new Student("a");

        assertGpa(student, 0.0);
        student.addGrade(Student.Grade.A);
        assertGpa(student, 4.0);
        student.addGrade(Student.Grade.B);
        assertGpa(student, 3.5);
        student.addGrade(Student.Grade.C);
        assertGpa(student, 3.0);
        student.addGrade(Student.Grade.D);
        assertGpa(student, 2.5);
        student.addGrade(Student.Grade.F);
        assertGpa(student, 2.0);
    }

    private void assertGpa(Student student, double grade) {
        assertEquals(grade, student.getGpa(), GRADE_TOLERANCE);
    }
}

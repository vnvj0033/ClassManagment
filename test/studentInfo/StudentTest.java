package studentInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student firstStudent = new Student(firstStudentName);
        assertEquals(firstStudent.getName(), firstStudentName);
        assertEquals(firstStudent.getFirstName(), "Jane");
        assertEquals(firstStudent.getLastName(), "Doe");
        assertEquals(firstStudent.getMiddleName(), "");

        final String secondStudentName = "Blow";
        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudent.getName(), secondStudentName);
        assertEquals(secondStudent.getFirstName(), "");
        assertEquals(secondStudent.getLastName(), "Blow");
        assertEquals(secondStudent.getMiddleName(), "");

        final String thirdStudentName = "Raymond Douglas Davies";
        Student thirdStudent = new Student(thirdStudentName);
        assertEquals(thirdStudent.getName(), thirdStudentName);
        assertEquals(thirdStudent.getFirstName(), "Raymond");
        assertEquals(thirdStudent.getLastName(), "Davies");
        assertEquals(thirdStudent.getMiddleName(), "Douglas");
    }

    @Test
    void testStudentStatus() {
        Student student = new Student("a");
        assertEquals(0, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(3);
        assertEquals(3, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(4);
        assertEquals(7, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(5);
        assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredits());
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

    @Test
    public void testCalculateHonorsStudentGpa(){
        assertGpa(createHonorsStudent(),0.0);
        assertGpa(createHonorsStudent(Student.Grade.A),5.0);
        assertGpa(createHonorsStudent(Student.Grade.B),4.0);
        assertGpa(createHonorsStudent(Student.Grade.C),3.0);
        assertGpa(createHonorsStudent(Student.Grade.D),2.0);
        assertGpa(createHonorsStudent(Student.Grade.F),0.0);
    }

    @Test
    void testCharges() {
        Student student = new Student("a");
        student.addCharge(500);
        student.addCharge(200);
        student.addCharge(399);
        assertEquals(1099, student.totalCharges());
    }

    @Test
    void testBadlyFormattedName() {
        String studentName = "a b c d";
        try {
            new Student(studentName);
            fail("expected excepion from 4-part name");
        }catch (StudentNameFormaException expectedException){
            assertEquals(
                    String.format(Student.TOO_MANY_NAME_PARTS_MSG,
                            studentName, Student.MAX_NAME_PARTS),
                    expectedException.getMessage());
        }
    }

    private Student createHonorsStudent() {
        Student student = new Student("a");
        student.setGradingStrategy(new HonorsGradingStrategy());
        return student;
    }

    private Student createHonorsStudent(Student.Grade grade) {
        Student student = createHonorsStudent();
        student.addGrade(grade);
        return student;
    }

    private void assertGpa(Student student, double grade) {
        assertEquals(grade, student.getGpa(), GRADE_TOLERANCE);
    }
}

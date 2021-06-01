package studentInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DateUtil;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseSessionTest extends SessionTest{

    static final int CREDITS = 3;

    Session sut;
    Student student;
    Student student2;
    Date startDate;

    @BeforeEach
    void setUp() {
        startDate = DateUtil.createDate(2003, 1, 6);
        sut = createSession(createCourse(), startDate);
        sut.setNumberOfCredits(CourseSessionTest.CREDITS);
        student = new Student("준성");
        student2 = new Student("상민");
    }

    @Test
    void testCourseDates() {
        Date startDate = DateUtil.createDate(2003, 1, 6);
        Session session = createSession(createCourse(), startDate);
        Date sixteenWeeksOut = DateUtil.createDate(2003, 4, 25);
        assertEquals(session.getEndDate(), sixteenWeeksOut);
    }

    private Course createCourse() {
        return new Course("ENGL", "101");
    }

    @Test
    void testCreate() {
        CourseSession.resetCount();
        createSession(createCourse(), new Date());
        assertEquals(1, CourseSession.getCount());
        createSession(createCourse(), new Date());
        assertEquals(2, CourseSession.getCount());
    }

    @Test
    void testGetStudents() {
        sut.enroll(student);
        assertEquals(sut.get(0), student);

        sut.enroll(student2);
        assertEquals(sut.get(1), student2);
    }

    @Test
    void testEnrollStudents() {
        sut.enroll(student);
        assertEquals(CourseSessionTest.CREDITS, student.getCredits());
        assertEquals(sut.getNumberOfStudents(), 1);

        sut.enroll(student2);
        assertEquals(CourseSessionTest.CREDITS, student2.getCredits());
        assertEquals(sut.getNumberOfStudents(), 2);
    }

    @Test
    void testCount() {
        CourseSession.resetCount();
        createSession(createCourse(), new Date());
        assertEquals(1, CourseSession.getCount());
        createSession(createCourse(), new Date());
        assertEquals(2, CourseSession.getCount());
    }

    protected Session createSession(Course course, Date date){
        return CourseSession.create(course, date);
    }
}

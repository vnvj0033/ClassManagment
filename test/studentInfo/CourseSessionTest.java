package studentInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseSessionTest {

    static final int CREDITS = 3;

    CourseSession sut;
    Student student;
    Student student2;
    Date startDate;

    @BeforeEach
    void setUp() {
        startDate = DateUtil.createDate(2003, 1, 6);
        sut = CourseSession.create("전산학부", "101", startDate);
        sut.setNumberOfCredits(CourseSessionTest.CREDITS);
        student = new Student("준성");
        student2 = new Student("상민");
    }

    @Test
    void testCreate() {
        assertEquals(sut.getDepartment(), "전산학부");
        assertEquals(sut.getNumber(), "101");
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
        assertEquals(CourseSessionTest.CREDITS, student.getCredit());
        assertEquals(sut.getNumberOfStudunt(), 1);

        sut.enroll(student2);
        assertEquals(CourseSessionTest.CREDITS, student2.getCredit());
        assertEquals(sut.getNumberOfStudunt(), 2);
    }

    @Test
    void testCourseDates() {
        Date endDate = DateUtil.createDate(2003, 4, 25);
        assertEquals(sut.getEndDate(), endDate);
    }

    @Test
    void testCount() {
        CourseSession.resetCount();
        CourseSession.create("","",new Date());
        assertEquals(1, CourseSession.getCount());
        CourseSession.create("","",new Date());
        assertEquals(2, CourseSession.getCount());
    }
}

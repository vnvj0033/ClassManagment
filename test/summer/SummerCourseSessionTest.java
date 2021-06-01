package summer;

import org.junit.jupiter.api.Test;
import studentInfo.Course;
import util.DateUtil;
import studentInfo.Session;
import studentInfo.SessionTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummerCourseSessionTest extends SessionTest {

    @Test
    void testEndDate(){
        Date startDate = DateUtil.createDate(2003, 6, 9);
        Session session = createSession(new Course("ENGL", "200"), startDate);
        Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
        assertEquals(eightWeeksOut, session.getEndDate());
    }

    protected Session createSession(Course course, Date date){
        return SummerCourseSession.create(course, date);
    }
}

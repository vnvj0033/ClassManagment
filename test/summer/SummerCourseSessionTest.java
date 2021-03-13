package summer;

import org.junit.jupiter.api.Test;
import studentInfo.CourseSession;
import studentInfo.DateUtil;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummerCourseSessionTest {

    @Test
    void testEndDate(){
        Date startDate = DateUtil.createDate(2003, 6, 9);
        CourseSession session = SummerCourseSession.create("ENGL", "200", startDate);
        Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
        assertEquals(eightWeeksOut, session.getEndDate());
    }
}

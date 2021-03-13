package summer;

import org.junit.jupiter.api.Test;
import studentInfo.DateUtil;
import studentInfo.Session;
import studentInfo.SessionTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SummerCourseSessionTest extends SessionTest {

    @Test
    void testEndDate(){
        Date startDate = DateUtil.createDate(2003, 6, 9);
        Session session = createSession("ENGL", "200", startDate);
        Date eightWeeksOut = DateUtil.createDate(2003, 8, 1);
        assertEquals(eightWeeksOut, session.getEndDate());
    }

    protected Session createSession(String department, String number, Date startDate){
        return SummerCourseSession.create(department, number, startDate);
    }
}

package studentInfo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static studentInfo.RosterRepoter.NEWLINE;

import java.util.Date;

public class CourseReportTest {

    @Test
    void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(CourseSession.create("ENGL","101",date));
        report.add(CourseSession.create("CZEC","200",date));
        report.add(CourseSession.create("ITAL","410",date));
        report.add(CourseSession.create("CZEC","220",date));
        report.add(CourseSession.create("ITAL","330",date));

        assertEquals("CZEC 200" + NEWLINE +
                            "CZEC 220" + NEWLINE +
                            "ENGL 101" + NEWLINE +
                            "ITAL 330" + NEWLINE +
                            "ITAL 410" + NEWLINE,
                            report.text());


    }
}

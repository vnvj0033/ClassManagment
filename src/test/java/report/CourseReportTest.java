package report;

import org.junit.jupiter.api.Test;
import studentInfo.Course;
import studentInfo.CourseSession;
import studentInfo.Session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static report.RepoetConstant.NEWLINE;

import java.util.Date;

public class CourseReportTest {

    @Test
    void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(create("ENGL", "101", date));
        report.add(create("CZEC", "200", date));
        report.add(create("ITAL", "410", date));
        report.add(create("CZEC", "220", date));
        report.add(create("ITAL", "330", date));

        assertEquals("CZEC 200" + NEWLINE +
                        "CZEC 220" + NEWLINE +
                        "ENGL 101" + NEWLINE +
                        "ITAL 330" + NEWLINE +
                        "ITAL 410" + NEWLINE,
                report.text());


    }

    private Session create(String name, String number, Date date) {
        return CourseSession.create(new Course(name, number), date);
    }
}

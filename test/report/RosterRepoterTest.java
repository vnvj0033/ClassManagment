package report;

import org.junit.jupiter.api.Test;
import studentInfo.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RosterRepoterTest {

    @Test
    void testRosterReport() throws IOException {
        Session session = CourseSession.create(new Course("ENGL", "101"), DateUtil.createDate(2003, 1, 6));

        session.enroll(new Student("A"));
        session.enroll(new Student("B"));

        Writer writer = new StringWriter();
        new RosterRepoter(session).writeReport(writer);
        String rosterReport = writer.toString();

        assertEquals(
                String.format(
                        RosterRepoter.ROSTER_REPORT_HEADER +
                        "A%n" +
                        "B%n" +
                        RosterRepoter.ROSTER_REPORT_FOOTER, 2
                ), rosterReport);
    }
}

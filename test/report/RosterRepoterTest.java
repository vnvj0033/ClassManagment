package report;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studentInfo.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RosterRepoterTest {
    private Session session;

    @BeforeEach
    void setUp() {
        session = CourseSession.create(new Course("ENGL", "101"), DateUtil.createDate(2003, 1, 6));

        session.enroll(new Student("A"));
        session.enroll(new Student("B"));
    }

    @Test
    void testRosterReport() throws IOException {
        Writer writer = new StringWriter();
        new RosterRepoter(session).writeReport(writer);
        assertReportContents(writer.toString());
    }

    @Test
    void testFiledReport() throws IOException {
        final String filename = "testFiledReport.txt";
        new RosterRepoter(session).writeReport(filename);

        StringBuffer buffer = new StringBuffer();
        String line;

        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while ((line = reader.readLine()) != null)
            buffer.append(String.format(line + "%n"));
        reader.close();

        assertReportContents(buffer.toString());

    }

    private void assertReportContents(String rosterReport) {
        assertEquals(String.format(
                RosterRepoter.ROSTER_REPORT_HEADER +
                        "A%n" +
                        "B%n" +
                        RosterRepoter.ROSTER_REPORT_FOOTER, session.getNumberOfStudents()
        ), rosterReport);
    }
}

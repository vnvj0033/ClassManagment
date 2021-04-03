package report;

import org.junit.jupiter.api.Test;
import studentInfo.Course;
import studentInfo.CourseSession;
import studentInfo.Session;
import studentInfo.Student;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RosterRepoterTest {

    @Test
    void testRosterReport() {
        Student student = new Student("준성");
        Student student2 = new Student("상민");

        Session session = CourseSession.create(new Course("정보통신", "101"), new Date());
        session.enroll(student);
        session.enroll(student2);

        RosterRepoter repoter = new RosterRepoter(session);

        String report = RosterRepoter.ROSTER_REPORT_HEADER +
                student.getName() +
                RosterRepoter.NEWLINE +
                student2.getName() +
                RosterRepoter.NEWLINE +
                RosterRepoter.ROSTER_REPORT_FOOTER +
                "2" +
                RosterRepoter.NEWLINE;

        assertEquals(repoter.getReport(), report);
    }
}

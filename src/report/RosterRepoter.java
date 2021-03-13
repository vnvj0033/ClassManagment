package report;

import studentInfo.CourseSession;
import studentInfo.Student;

import java.util.ArrayList;

public class RosterRepoter {
    static final String NEWLINE = System.lineSeparator();
    static final String ROSTER_REPORT_HEADER = "Student" + NEWLINE + "-" + NEWLINE;
    static final String ROSTER_REPORT_FOOTER = NEWLINE + "# student = ";

    private CourseSession session;

    public RosterRepoter(CourseSession session) {
        this.session = session;
    }

    public String getReport() {
        StringBuilder buffer = new StringBuilder();

        writeHeader(buffer);
        writeBody(buffer);
        writeFooter(buffer);

        return buffer.toString();
    }

    private void writeHeader(StringBuilder buffer) {
        buffer.append(ROSTER_REPORT_HEADER);
    }

    private void writeBody(StringBuilder buffer) {
        ArrayList<Student> students = session.getAllStudents();

        for(Student student: students) {
            buffer.append(student.getName() + NEWLINE);
        }
    }

    private void writeFooter(StringBuilder buffer) {
        buffer.append(ROSTER_REPORT_FOOTER + session.getAllStudents().size() +
                NEWLINE);
    }

}

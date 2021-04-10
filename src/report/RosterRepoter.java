package report;

import studentInfo.Session;
import studentInfo.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class RosterRepoter {

    static final String ROSTER_REPORT_HEADER = "Student%n-%n";
    static final String ROSTER_REPORT_FOOTER = "%n# student = %d%n";

    private Session session;
    private Writer writer;

    public RosterRepoter(Session session) {
        this.session = session;
    }

    void writeReport(Writer writer) throws IOException {
        this.writer = writer;
        writeHeader();
        writeBody();
        writeFooter();
    }

    void writeReport(String filename) throws IOException{
        Writer bufferWriter = new BufferedWriter(new FileWriter(filename));
        try {
            writeReport(bufferWriter);
        }finally {
            bufferWriter.close();
        }

    }

    private void writeHeader() throws IOException {
        writer.write(String.format(ROSTER_REPORT_HEADER));
    }

    private void writeBody() throws IOException {
        ArrayList<Student> students = session.getAllStudents();

        for (Student student : students) {
            writer.write(String.format(student.getName() + "%n"));
        }
    }

    private void writeFooter() throws IOException {
        writer.write(String.format(ROSTER_REPORT_FOOTER, session.getAllStudents().size()));
    }
}

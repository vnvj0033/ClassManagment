package report;

import studentInfo.CourseSession;

import java.util.ArrayList;
import java.util.Collections;

import static report.RosterRepoter.NEWLINE;


public class CourseReport {
    private ArrayList<CourseSession> sessions = new ArrayList<CourseSession>();

    public void add(CourseSession courseSession) {
        sessions.add(courseSession);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder builder = new StringBuilder();
        for (CourseSession session : sessions){
            builder.append(session.getDepartment() +
                    " " +
                    session.getNumber() +
                    NEWLINE);
        }
        return builder.toString();
    }
}

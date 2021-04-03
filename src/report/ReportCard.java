package report;

import studentInfo.Student;

import java.util.EnumMap;
import java.util.Map;

public class ReportCard {
    static final String A_MESSAGE = "Excellent";
    static final String B_MESSAGE = "Very good";
    static final String C_MESSAGE = "Hmmm...";
    static final String D_MESSAGE = "Youre not trying";
    static final String F_MESSAGE = "Loser";

    private Map<Student.Grade, String> message = null;

    public String getMessage(Student.Grade grade) {
        return getMessages().get(grade);
    }

    public Map<Student.Grade, String> getMessages() {
        if (message == null)
            loadMessages();
        return message;
    }

    private void loadMessages() {
        message = new EnumMap<>(Student.Grade.class);
        message.put(Student.Grade.A, A_MESSAGE);
        message.put(Student.Grade.B, B_MESSAGE);
        message.put(Student.Grade.C, C_MESSAGE);
        message.put(Student.Grade.D, D_MESSAGE);
        message.put(Student.Grade.F, F_MESSAGE);
    }
}

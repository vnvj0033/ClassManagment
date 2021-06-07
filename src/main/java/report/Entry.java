package report;

import studentInfo.Student;

import java.util.Objects;

// this class only need test
public class Entry {

    private Student.Grade grade;
    private String message;

    public Entry(Student.Grade grade, String message) {
        this.grade = grade;
        this.message = message;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Entry that = (Entry) object;
        return grade == that.grade &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        final int hashMultiplier = 41;
        int result = 7;
        result = result * hashMultiplier + grade.hashCode();
        result = result * hashMultiplier + message.hashCode();
        return result;
    }
}

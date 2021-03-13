package studentInfo;

import java.util.*;

public class CourseSession extends Session{

    public static int count;

    public static CourseSession create(String department, String number, Date startDate) {
        return new CourseSession(department, number, startDate);
    }

    protected CourseSession(String department, String number, Date startDate) {
        super(department, number, startDate);
        CourseSession.incrementCount();
    }

    private static void incrementCount() {
        CourseSession.count++;
    }

    public static void resetCount() {
        CourseSession.count = 0;
    }

    public static int getCount() {
        return CourseSession.count;
    }

    protected int getSessionLength() {
        return 16;
    }

}

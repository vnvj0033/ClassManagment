package studentInfo;

import java.util.*;

public class CourseSession extends Session{

    public static int count;

    public static Session create(Course course, Date startDate) {
        return new CourseSession(course, startDate);
    }

    protected CourseSession(Course course, Date startDate) {
        super(course, startDate);
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

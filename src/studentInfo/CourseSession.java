package studentInfo;

import java.util.*;

public class CourseSession implements Comparable<CourseSession> {

    public static int count = 0;
    private int numberOfCredits = 0;
    private String department;
    private String number;
    private ArrayList<Student> students = new ArrayList<>();
    private Date startDate;

    public static CourseSession create(String department, String number, Date startDate) {
        return new CourseSession(department, number, startDate);
    }

    protected CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
        CourseSession.incrementCount();
    }

    public static int getCount() {
        return CourseSession.count;
    }

    public static void resetCount() {
        CourseSession.count = 0;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    public void enroll(Student student) {
        student.addCredit(numberOfCredits);
        students.add(student);
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public int getNumberOfStudunt() {
        return students.size();
    }

    public Student get(int index) {
        return students.get(index);
    }

    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(getStartDate());
        int numberOfDays = getSessionLength() * 7 - 3; // 16 weeks * days per week - 3 days
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    protected Date getStartDate() {
        return startDate;
    }

    private static void incrementCount() {
        CourseSession.count++;
    }

    protected int getSessionLength() {
        return 16;
    }

    @Override
    public int compareTo(CourseSession session) {
        if (this.getDepartment().equals(session.getDepartment())) {
            return this.getNumber().compareTo(session.getNumber());
        }

        return this.getDepartment().compareTo(session.getDepartment());
    }
}

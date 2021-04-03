package studentInfo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

abstract public class Session implements Comparable<Session>, Iterable<Student> {

    private static int count = 0;
    private ArrayList<Student> students = new ArrayList<>();
    private Date startDate;
    private int numberOfCredits;
    private URL url;
    private Course course;

    protected Session(Course course, Date startDate) {
        this.course = course;
        this.startDate = startDate;
    }

    @Override
    public int compareTo(Session that) {
        int compare = this.getDepartment().compareTo(that.getDepartment());
        if (compare != 0)
            return compare;
        return this.getNumber().compareTo(that.getNumber());
    }

    public void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public String getDepartment() {
        return course.getDepartment();
    }

    public String getNumber() {
        return course.getNumber();
    }

    public int getNumberOfStudents() {
        return students.size();
    }

    public void enroll(Student student) {
        student.addCredits(numberOfCredits);
        students.add(student);
    }

    public Student get(int index) {
        return students.get(index);
    }

    protected Date getStartDate() {
        return startDate;
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    abstract protected int getSessionLength();

    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(getStartDate());
        int numberOfDays = getSessionLength() * 7 - 3; // 16 weeks * days per week - 3 days
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    public void setUrl(String urlString) throws SessionException {
        try {
            this.url = new URL(urlString);
        }catch (MalformedURLException e){
            log(e);
            throw new SessionException(e);
        }
    }

    public URL getUrl() {
        return url;
    }

    public double averageGpaForPartTimeStudents() {
        double total = 0.0;
        int count = 0;

        for (Student student : students) {
            if (student.isFullTime())
                continue;
            count++;
            total += student.getGpa();
        }
        if (count == 0) return 0.0;
        return total / count;
    }

    public Iterator<Student> iterator() {
        return students.iterator();
    }

    private void log(Exception e) {
        e.printStackTrace();
    }
}

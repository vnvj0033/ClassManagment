package studentInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Student {

    public enum Flag {
        ON_CAMPUS(1),
        TAX_EXEMPT(2),
        MINOR(4),
        TROUBLEMAKER(8);

        private int mask;

        Flag(int mask) {
            this.mask = mask;
        }

    }
    public enum Grade {
        A(4),
        B(3),
        C(2),
        D(1),
        F(0);
        private int points;
        int getPoints() {
            return points;
        }

        Grade(int points) {
            this.points = points;
        }

    }
    final static String TOO_MANY_NAME_PARTS_MSG = "Student name '%s' contains more than %d parts";
    final static int MAX_NAME_PARTS = 3;
    final static int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    final static Logger logger = Logger.getLogger(Student.class.getName());
    final static String IN_STATE = "대전";
    private int settings = 0x0;

    private final String name;

    private String id;

    private String firstName = "";

    private String middleName = "";
    private String lastName;
    private int credits = 0;
    private String state = "";
    private ArrayList<Grade> grades = new ArrayList<>();
    private boolean isHonors = false;
    private GradingStrategy gradingStrategy = new BasicGradingStrategy();
    private List<Integer> charges = new ArrayList();

    public Student(final String fullName) {
        this.name = fullName;
        credits = 0;
        List<String> nameParts = split(fullName);
        if (nameParts.size() > MAX_NAME_PARTS) {
            String message = String.format(TOO_MANY_NAME_PARTS_MSG, fullName, MAX_NAME_PARTS);
            logger.info(message);
            throw new StudentNameFormaException(message);
        }
        setName(nameParts);
    }

    public void set(Flag... flags) {
        for (Flag flag : flags)
            settings |= flag.mask;
    }

    public void unset(Flag... flags) {
        for (Flag flag : flags)
            settings &= ~flag.mask;
    }

    public boolean isOn(Flag flag) {
        return (settings & flag.mask) == flag.mask;
    }

    public boolean isOff(Flag flag) {
        return !isOn(flag);
    }

    public void addCharge(int charge) {
        charges.add(charge);
    }

    public int totalCharges() {
        int total = 0;
        for (int charge : charges) {
            total += charge;
        }
        return total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    public void addCredits(int credit) {
        this.credits += credit;
    }

    public int getCredits() {
        return credits;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isInState() {
        return IN_STATE.equals(this.state);
    }

    public void setHonors() {
        isHonors = true;
    }

    public double getGpa() {
        Student.logger.fine("begin getGpa " + System.currentTimeMillis());
        if (grades.isEmpty())
            return 0.0;
        double total = 0.0;
        for (Grade grade : grades)
            total += gradingStrategy.getGradePointsFor(grade);
        double result = total / grades.size();
        Student.logger.fine("end getGpa " + System.currentTimeMillis());
        return result;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    void setGradingStrategy(GradingStrategy gradingStrategy) {
        this.gradingStrategy = gradingStrategy;
    }

    int gradPointsFor(Grade grade) {
        return gradingStrategy.getGradePointsFor(grade);
    }

    private List<String> split(String fullName) {
        List<String> results = new ArrayList<>();
        for (String name : fullName.split(" "))
            results.add(name);
        return results;
    }

    private String removeLast(List<String> list) {
        if (list.isEmpty())
            return "";
        return list.remove(list.size() - 1);
    }

    private List<String> tokenize(String string) {
        List<String> result = new ArrayList<>();

        StringBuffer word = new StringBuffer();
        int index = 0;
        while (index < string.length()) {
            char ch = string.charAt(index);
            if (ch != ' ')
                word.append(ch);
            else if (word.length() > 0) {
                result.add(word.toString());
                word = new StringBuffer();
            }
            index++;
        }
        return result;
    }

    private int gradePointsFor(Grade grade) {
        return gradingStrategy.getGradePointsFor(grade);
    }

    private void setName(List<String> nameParts) {
        this.lastName = removeLast(nameParts);
        String name = removeLast(nameParts);
        if (nameParts.isEmpty())
            this.firstName = name;
        else {
            this.middleName = name;
            this.firstName = removeLast(nameParts);
        }
    }

    private void log(String message) {
        Logger logger = Logger.getLogger(getClass().getName());
        logger.info(message);
    }
}

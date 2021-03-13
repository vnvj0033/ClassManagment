package studentInfo;

import java.util.ArrayList;

public class Student{

    public enum Grade {A, B, C, D, F}

    final static int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    final static String IN_STATE = "대전";
    private final String name;
    private int credit = 0;
    private String state = "";
    private ArrayList<Grade> grades = new ArrayList<>();
    private boolean isHonors = false;
    private GradingStrategy gradingStrategy = new RegularGradingStrategy();

    public Student(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isFullTime() {
        return credit >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    public void addCredit(int credit) {
        this.credit += credit;
    }

    public int getCredit() {
        return credit;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isInState() {
        return IN_STATE.equals(this.state);
    }

    public void setHonors(){
        isHonors = true;
    }

    void setGradingStrategy(GradingStrategy gradingStrategy){
        this.gradingStrategy = gradingStrategy;
    }

    int gradPointsFor(Grade grade){
        return gradingStrategy.getGradePointsFor(grade);
    }

    public double getGpa() {
        if (grades.isEmpty())
            return 0.0;

        double total = 0.0;

        for (Grade grade : grades)
            total += gradingStrategy.getGradePointsFor(grade);

        return total / grades.size();
    }

    private int gradePointsFor(Grade grade) {
        return gradingStrategy.getGradePointsFor(grade);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }
}

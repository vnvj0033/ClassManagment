package studentInfo;

public class HonorsGradingStrategy extends BasicGradingStrategy {
    public int getGradePointsFor(Student.Grade grade) {
        int points = super.basicGradePointFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }
}

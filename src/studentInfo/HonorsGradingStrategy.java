package studentInfo;

public class HonorsGradingStrategy
        extends BasicGradingStrategy
        implements GradingStrategy{
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        int points = basicGradePointFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }
}

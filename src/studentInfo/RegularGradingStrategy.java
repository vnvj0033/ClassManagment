package studentInfo;

public class RegularGradingStrategy
        extends BasicGradingStrategy
        implements GradingStrategy{
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        return basicGradePointFor(grade);
    }
}

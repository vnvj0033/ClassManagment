package studentInfo;

public class RegularGradingStrategy extends BasicGradingStrategy{
    public int getGradePointsFor(Student.Grade grade) {
        return basicGradePointFor(grade);
    }
}

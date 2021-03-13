package studentInfo;

public class HonorsGradingStrategy implements GradingStrategy{
    @Override
    public int getGradePointsFor(Student.Grade grade) {
        int points = basicGradePointFor(grade);
        if (points > 0)
            points += 1;
        return points;
    }

    private int basicGradePointFor(Student.Grade grade) {
        switch (grade){
            case A: return 4;
            case B: return 3;
            case C: return 2;
            case D: return 1;
            default: return 0;
        }
    }
}

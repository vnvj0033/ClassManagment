package studentInfo;

abstract public class BasicGradingStrategy implements GradingStrategy {
    abstract public int getGradePointsFor(Student.Grade grade);
    int basicGradePointFor(Student.Grade grade) {
        switch (grade){
            case A: return 4;
            case B: return 3;
            case C: return 2;
            case D: return 1;
            default: return 0;
        }
    }
}

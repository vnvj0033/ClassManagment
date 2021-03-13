package studentInfo;

public class BasicGradingStrategy {
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

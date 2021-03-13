package studentInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SwitchTest {

    @Test
    void testSwitchResualt() {

        enum Score {fieldGoal, touchdow, extraPoint, twoPointConversion, safety}

        int totalPoints = 0;

        Score score = Score.touchdow;

        switch (score) {
            case fieldGoal:
                totalPoints += 3;
                break;
            case touchdow:
                totalPoints += 6;
                break;
            case extraPoint:
                totalPoints += 1;
                break;
            case twoPointConversion:
            case safety:
                totalPoints += 2;
                break;
        }
        assertEquals(6, totalPoints);
    }
}
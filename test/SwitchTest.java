import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SwitchTest {

    enum Score {fieldGoal, touchdow, extraPoint, twoPointConversion, safety}

    @Test
    void testSwitchResualt() {


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
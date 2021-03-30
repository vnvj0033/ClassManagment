package studentInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ScorerTest {
    @Test
    void testCaptureScore() {
        Scorer scorer = new Scorer();
        assertEquals(75, scorer.score("75"));
    }

    @Test
    void testBadScoreEntered() {
        Scorer scorer = new Scorer();
        try {
            scorer.score("abd");
            fail("expected NumberFormatException on bad input");
        }catch (NumberFormatException success){
        }
    }

}

package studentInfo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testIsValid() {
        Scorer scorer = new Scorer();
        assertTrue(scorer.isValid("75"));
        assertFalse(scorer.isValid("bd"));
    }

}

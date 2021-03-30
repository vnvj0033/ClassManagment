import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogginTest {
    @Test
    void testLoggingHierarchy() {
        Logger logger = Logger.getLogger("sis.studentInfo.Student");
        assertTrue(logger == Logger.getLogger("sis.studentInfo.Student"));

        Logger parent = Logger.getLogger("sis.studentInfo");
        assertEquals(parent, logger.getParent());
        assertEquals(Logger.getLogger("sis"), parent.getParent());
    }
}

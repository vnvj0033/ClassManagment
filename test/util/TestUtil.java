package util;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtil {
    public static void assertGone(String... filenames) {
        for (String filename : filenames)
            assertFalse(new File(filename).exists());
    }

    public static void delete(String filename) {
        File file = new File(filename);
        if (file.exists())
            assertTrue(file.delete());
    }
}

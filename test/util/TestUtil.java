package util;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

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

    public static void assertDateEquals(int year, int month, int day, Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        assertEquals(year, calendar.get(Calendar.YEAR));
        assertEquals(month, calendar.get(Calendar.MONTH));
        assertEquals(day, calendar.get(Calendar.DAY_OF_MONTH));
    }
}

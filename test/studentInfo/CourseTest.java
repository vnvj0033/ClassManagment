package studentInfo;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    @Test
    void testCreate() {
        Course course = new Course("CMSC", "120");
        assertEquals("CMSC", course.getDepartment());
        assertEquals("120", course.getNumber());
    }

    @Test
    void testEquality() {
        Course courseA = new Course("NURS", "201");
        Course courseAPrime = new Course("NURS", "201");
        assertEquals(courseA, courseAPrime);

        Course courseB = new Course("ARTH", "330");
        assertFalse(courseA.equals(courseB));

        // reflexivity
        assertEquals(courseA, courseA);

        //transitivity
        Course courseAPrime2 = new Course("NURS", "201");
        assertEquals(courseAPrime, courseAPrime2);
        assertEquals(courseA, courseAPrime2);

        // symmetry
        assertEquals(courseAPrime, courseA);

        //consistency
        assertEquals(courseA, courseAPrime);

        // comparison to null
        assertFalse(courseA.equals(null));

        // apples & oranges
        assertFalse(courseA.equals("CMSC-120"));

    }

    @Test
    void testHashCode() {
        Course courseA = new Course("NURS", "201");
        Course courseAPrime = new Course("NURS", "201");

        assertEquals(courseA.hashCode(), courseAPrime.hashCode());
        // consistency
        assertEquals(courseA.hashCode(), courseA.hashCode());
    }

    @Test
    void testHashCodePerformance() {
        final int count = 10000;
        long start = System.currentTimeMillis();
        Map<Course, String> map = new HashMap<>();
        for (int i = 0; i < count; i++) {
            Course course = new Course("C" + i, "" + i);
            map.put(course, "");
        }
        long stop = System.currentTimeMillis();
        long elapsed = stop - start;
        final long arbitraryThreshold = 200;
        assertTrue(elapsed < arbitraryThreshold, "elapsed time = " + elapsed);
    }


    @Test
    void testToString() {
        Course course = new Course("ENGL", "301");
        assertEquals("ENGL 301", course.toString());
    }

    @Test
    void testClone() {
        final String department = "CHEM";
        final String number = "400";
        final Date now = new Date();
        Course course = new Course(department, number);
        course.setEffectiveDate(now);
        Course copy = course.clone();
        assertFalse(copy == course);
        assertEquals(department, copy.getDepartment());
        assertEquals(number, copy.getNumber());
        assertEquals(now, copy.getEffectiveDate());
    }
}

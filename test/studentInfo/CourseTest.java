package studentInfo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}

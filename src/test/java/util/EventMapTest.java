package util;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventMapTest {

    @Test
    void testSingleElement() {
        EventMap<java.sql.Date, String> map = new EventMap<>();
        final java.sql.Date date = new java.sql.Date(new Date().getTime());
        final String value = "abc";
        map.put(date, value);

        List<String> values = map.get(date);
        assertEquals(value, values.get(0));
    }

    @Test
    void testGetPastEvents() {
        EventMap<Date, String> events = new EventMap<>();
        final Date today = new Date();
        final Date yesterday = new Date(today.getTime() - 86400000);
        events.put(today, "sleep");
        final String descriptionA = "birthday";
        final String descriptionB = "drink";
        events.put(yesterday, descriptionA);
        events.put(yesterday, descriptionB);
        List<String> descriptions = events.getPastEvents();
        assertTrue(descriptions.contains(descriptionA));
        assertTrue(descriptions.contains(descriptionB));
    }
}

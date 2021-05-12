package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultiHashMapTest {
    private static final Date today = new Date();
    private static final Date tomorrow = new Date(today.getTime() + 86400000);
    private static final String eventA = "wake up";
    private static final String eventB = "eat";

    private MultiHashMap<Date, String> events;

    @BeforeEach
    protected void setUp() {
        events = new MultiHashMap<>();
    }

    @Test
    void testCreate() {
        assertEquals(0, events.size());
    }

    @Test
    void testSingleEntry() {
        events.put(today, eventA);
        assertEquals(1, events.size());
        assertEquals(eventA, getSoloEvent(today));
    }

    @Test
    void testMultipleEntriesDifferentKey() {
        events.put(today, eventA);
        events.put(tomorrow, eventB);
        assertEquals(2, events.size());
        assertEquals(eventA, getSoloEvent(today));
        assertEquals(eventB, getSoloEvent(tomorrow));
    }

    @Test
    void testMultipleEntriesSameKey() {
        events.put(today, eventA);
        events.put(today, eventB);
        assertEquals(1, events.size());
        Collection<String> retrievedEvents = events.get(today);
        assertEquals(2, retrievedEvents.size());
        assertTrue(retrievedEvents.contains(eventA));
        assertTrue(retrievedEvents.contains(eventB));
    }

    private String getSoloEvent(Date date) {
        Collection<String> retrievedEvents = events.get(date);
        assertEquals(1, retrievedEvents.size());
        Iterator<String> it = retrievedEvents.iterator();
        return it.next();

    }
}

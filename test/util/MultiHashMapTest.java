package util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testFilter() {
        MultiHashMap<String, java.sql.Date> meetings = new MultiHashMap<>();

        meetings.put("iteration start", createSqlDate(2005, 9, 12));
        meetings.put("iteration start", createSqlDate(2005, 9, 26));
        meetings.put("VP blather", createSqlDate(2005, 9, 12));
        meetings.put("brown bags", createSqlDate(2005, 9, 14));

        MultiHashMap<String, java.sql.Date> mondayMeetings = new MultiHashMap<>();
        MultiHashMap.filter(mondayMeetings, meetings, new MultiHashMap.Filter<java.sql.Date>() {
            public boolean apply(java.sql.Date date) {
                return isMonday(date);
            }
        });

        assertEquals(2, mondayMeetings.size());
        assertEquals(2, mondayMeetings.get("iteration start").size());
        assertNull(mondayMeetings.get("brown bags"));
        assertEquals(1, mondayMeetings.get("VP blather").size());
    }

    private java.sql.Date createSqlDate(int year, int month, int day) {
        java.util.Date date = DateUtil.createDate(year, month, day);
        return new java.sql.Date(date.getTime());
    }

    private boolean isMonday(java.sql.Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    private String getSoloEvent(Date date) {
        Collection<String> retrievedEvents = events.get(date);
        assertEquals(1, retrievedEvents.size());
        Iterator<String> it = retrievedEvents.iterator();
        return it.next();
    }
}

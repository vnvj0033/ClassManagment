package clock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClockTest{
    private Clock clock;
    private Lock lock;
    private Condition receivedEnoughTics;

    @BeforeEach
    protected void setUp() {
        lock = new ReentrantLock();
        receivedEnoughTics = lock.newCondition();
    }

    @Test
    public void testClock() throws Exception{
        final int seconds = 2;
        final List<Date> tics = new ArrayList<>();
        ClockListener listener = createClockListener(tics,seconds);

        clock = new Clock(listener);
        lock.lock();
        try {
            receivedEnoughTics.await();
        } finally {
            lock.unlock();
        }
        clock.stop();
        verify(tics, seconds);
    }

    private ClockListener createClockListener(final List<Date> tics,final int seconds) {
        return new ClockListener() {
            private int count = 0;
            @Override
            public void update(Date date) {
                tics.add(date);
                if (++count == seconds) {
                    lock.lock();
                    try {
                        receivedEnoughTics.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
    }

    private void verify(List<Date> tics, int seconds) {
        assertEquals(seconds, tics.size());
        for (int i = 1; i < seconds; i++) {
            assertEquals(1, getSecondsFromLast(tics, i));
        }
    }

    private long getSecondsFromLast(List<Date> tics, int i) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(tics.get(i));
        int now = calendar.get(Calendar.SECOND);
        calendar.setTime(tics.get(i - 1));
        int then = calendar.get(Calendar.SECOND);
        if (now == 0)
            now = 60;
        return now - then;
    }
}
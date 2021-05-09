package search;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.LineWriter;
import util.TestUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchSchedulerTest {
    private int actualResultsCount = 0;

    @BeforeEach
    protected void setUp() throws Exception{
        TestUtil.delete(SearchTest.FILE);
        LineWriter.write(SearchTest.FILE, SearchTest.TEST_HTML);
    }

    @AfterEach
    protected void tearDown() throws Exception{
        TestUtil.delete(SearchTest.FILE);
    }

    @Test
    public void testRepeatedSearch() throws Exception{
        final int searchInterval = 3000;

        Search search = new Search(SearchTest.URL, "xxx");

        ResultsListener listener = new ResultsListener() {
            public void executed(Search search) {
                ++actualResultsCount;
            }
        };

        SearchScheduler scheduler = new SearchScheduler(listener);
        scheduler.repeat(search, searchInterval);

        final int expectedResultsCount = 3;
        Thread.sleep((expectedResultsCount - 1) * searchInterval + 1000);

        scheduler.stop();
        assertEquals(expectedResultsCount, actualResultsCount);
    }
}

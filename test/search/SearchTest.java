package search;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {
    private static final String URL = "http://www.langrsoft.com";

    @Test
    public void testCreate() throws IOException {
        Search search = new Search(URL, "x");
        assertEquals(URL, search.getUrl());
        assertEquals("x", search.getText());
    }

    @Test
    public void testPositiveSearch() throws IOException{
        Search search = new Search(URL, "Jeff Langr");
        search.execute();
//        assertTrue(search.matches() >= 1);
        assertFalse(search.errored());
    }

    @Test
    public void testNegativeSearch() throws IOException {
        final String unlikelyText = "mama cass elliott";
        Search search = new Search(URL, unlikelyText);
        search.execute();
        assertEquals(0, search.matches());
        assertFalse(search.errored());
    }

    @Test
    public void testErroredSearch() throws IOException {
        final String badUrl = URL + "/z2468.html";
        Search search = new Search(badUrl, "whatever");
        search.execute();
//        assertTrue(search.errored());
//        assertEquals(FileNotFoundException.class, search.getError().getClass());
    }
}

import org.junit.jupiter.api.Test;
import studentInfo.CourseCatalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializationTest {
    @Test
    void testLoadToNewVersion() throws Exception{
        CourseCatalog catalog = new CourseCatalog();
        catalog.load("CourseCatalogTest.testAdd.txt");
        assertEquals(2, catalog.getSessions().size());
    }
}

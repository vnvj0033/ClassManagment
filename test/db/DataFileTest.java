package db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DataFileTest {
    private static final String ID1 = "12345";
    private static final String ID2 = "23456";
    private static final String FILEBASE = "DataFileTest";

    private DataFile db;
    private TestData testData1;
    private TestData testData2;

    @BeforeEach
    protected void setUp() throws IOException {
        db = DataFile.create(FILEBASE);
        assertEquals(0, db.size());

        testData1 = new TestData(ID1, "datum1a", 1);
        testData2 = new TestData(ID2, "datum2a", 2);
    }

    @Test
    void testDown() {
        db.close();
        db.deleteFiles();
    }

    @Test
    void testAdd() throws IOException {
        db.add(ID1, testData1);
        assertEquals(1, db.size());

        db.add(ID2, testData2);
        assertEquals(2, db.size());

        assertTestDataEquals(testData1, (TestData) db.findBy(ID1));
        assertTestDataEquals(testData2, (TestData) db.findBy(ID2));
    }

    @Test
    void testPersistence() {
        db.add(ID1, testData1);
        db.add(ID2, testData2);
        db.close();

        db = DataFile.open(FILEBASE);
        assertEquals(2, db.size());

        assertTestDataEquals(testData1, (TestData) db.findBy(ID1));
        assertTestDataEquals(testData2, (TestData) db.findBy(ID2));

        db = DataFile.create(FILEBASE);
        assertEquals(0, db.size());
    }

    @Test
    void testKeyNotFound() throws IOException{
        assertNull(db.findBy(ID2));
    }

    void assertTestDataEquals(TestData expected, TestData actual) {
        assertEquals(expected.id, actual.id);
        assertEquals(expected.field1, actual.field1);
        assertEquals(expected.field2, actual.field2);
    }

    static class TestData implements Serializable {
        String id;
        String field1;
        int field2;
        TestData(String id, String field1, int field2){
            this.id = id;
            this.field1 = field1;
            this.field2 = field2;
        }
    }
}

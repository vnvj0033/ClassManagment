package util;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IOUtilTest {
    static final String FILENAME1 = "IOUtillTest1.txt";
    static final String FILENAME2 = "IOUtillTest2.txt";

    @Test
    public void testDeleteSingleFile() throws IOException{
        create(FILENAME1);
        assertTrue(IOUtil.delete(FILENAME1));
        TestUtil.assertGone(FILENAME1);
    }

    @Test
    public void testDeleteMultipleFiles() throws IOException {
        create(FILENAME1, FILENAME2);
        assertTrue(IOUtil.delete(FILENAME1, FILENAME2));
        TestUtil.assertGone(FILENAME1, FILENAME2);
    }

    @Test
    public void testDeleteNotFile() throws IOException {
        TestUtil.delete(FILENAME1);
        assertFalse(IOUtil.delete(FILENAME1));
    }

    @Test
    public void testDeletePartiallySuccessful() throws IOException {
        create(FILENAME1);
        TestUtil.delete(FILENAME2);
        assertFalse(IOUtil.delete(FILENAME1, FILENAME2));
        TestUtil.assertGone(FILENAME1);
    }

    private void create(String... filenames) throws IOException {
        for (String filename: filenames){
            TestUtil.delete(filename);
            new File(filename).createNewFile();
        }
    }
}

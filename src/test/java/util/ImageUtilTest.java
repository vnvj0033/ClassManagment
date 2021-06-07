package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ImageUtilTest {

    @Test
    public void testLoadImage() {
        assertNull(ImageUtil.create("/images/bogusFilename.gif"));
        assertNotNull(ImageUtil.create("/images/download.png"));
    }
}

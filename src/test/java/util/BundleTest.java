package util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BundleTest {
    private static final String KEY = "someKey";
    private static final String TEST_APPEND = "test";
    private String filename;
    private String existingBundleName;

    private void prepare() {
        TestUtil.delete(filename);
        existingBundleName = Bundle.getName();
        Bundle.setName(existingBundleName + TEST_APPEND);
    }

    @AfterEach
    protected void tearDown() {
        Bundle.setName(existingBundleName);
        TestUtil.delete(filename);
    }

    @Test
    public void testMessage() throws IOException {
        filename = getFilename();
        prepare();
        final String value = "open the door";
        writeBundle(value);
        assertEquals(value, Bundle.get(KEY));
    }

    @Test
    public void testLocalizedMessage() throws IOException {
        final String language = "es";
        final String country = "MX";
        filename = getFilename(language, country);
        prepare();
        Locale mexican = new Locale(language, country);
        Locale current = Locale.getDefault();
        try {
            Locale.setDefault(mexican);
            final String value = "abre la puerta";
            writeBundle(value);
            assertEquals(value, Bundle.get(KEY));
        } finally {
            Locale.setDefault(current);
        }
    }

    private void writeBundle(String value) throws IOException {
        LineWriter writer = new LineWriter();
        String record = String.format("%s=%s", KEY, value);
        writer.write(getFilename(), record);
    }

    private String getFilename(String language, String country) {
        StringBuilder builder = new StringBuilder();
        builder.append("./src/main/java/util/");
        builder.append(Bundle.DEFAULT_BASE_NAME);
        builder.append("Test");
        if (language.length() > 0) builder.append("_" + language);
        if (country.length() > 0) builder.append("_" + country);
        builder.append(".properties");
        return builder.toString();
    }

    private String getFilename() {
        return getFilename("", "");
    }
}
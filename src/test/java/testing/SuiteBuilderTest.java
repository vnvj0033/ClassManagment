package testing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuiteBuilderTest {
    @Test
    void testGatherTestClassNames() {
        SuiteBuilder builder = new SuiteBuilder();
        List<String> classes = builder.getherTestClassNames();
        assertTrue(classes.contains("testing.SuiteBuilder"));
    }
}

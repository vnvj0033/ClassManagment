package util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilTest {
    private static final String TEXT = "this is it, isnt it";

    @Test
    public void testOccurrencesOne() {
        assertEquals(1, StringUtil.occurrences(TEXT, "his"));
    }

    @Test
    public void testOccurrencesNone() {
        assertEquals(0, StringUtil.occurrences(TEXT, "smelt"));
    }

    @Test
    public void testOccurrencesMany() {
        assertEquals(3, StringUtil.occurrences(TEXT, "is"));
        assertEquals(2, StringUtil.occurrences(TEXT, "it"));
    }

    @Test
    public void testOccurrencesSearchStringTooLarge() {
        assertEquals(0, StringUtil.occurrences(TEXT, TEXT + "sdfas"));
    }

    @Test
    void testConcatenateList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");

        String output = StringUtil.concatenate(list);

        assertEquals(String.format("a%nb%n"), output);
    }

    @Test
    public void testConcatenateFormattedDecimals() {
        List<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("3.1416"));
        list.add(new BigDecimal("-1.4142"));

        String output = StringUtil.concatenateNumbers(list, 3);
        assertEquals(String.format("3.142%n-1.414%n"), output);
    }

    @Test
    public void testConcatenateFormattedIntegers() {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(17);

        String output = StringUtil.concatenateNumbers(list, 0);
        assertEquals(String.format("12%n17%n"), output);
    }

    @Test
    public void testStripPhoneNumber() {
        String input = "(719) 555-9353 (home)";
        assertEquals("7195559353", StringUtil.stripToDigits(input));
    }
}

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForLoopTest {
    @Test
    void testPalindrome() {
        assertFalse(ForLoop.isPalindrome("abcdef"));
        assertFalse(ForLoop.isPalindrome("abccda"));
        assertTrue(ForLoop.isPalindrome("abccba"));
        assertFalse(ForLoop.isPalindrome("abcxba"));
        assertTrue(ForLoop.isPalindrome("a"));
        assertTrue(ForLoop.isPalindrome("aa"));
        assertFalse(ForLoop.isPalindrome("ab"));
        assertTrue(ForLoop.isPalindrome(""));
        assertTrue(ForLoop.isPalindrome("aaa"));
        assertTrue(ForLoop.isPalindrome("aba"));
        assertTrue(ForLoop.isPalindrome("abbba"));
        assertTrue(ForLoop.isPalindrome("abba"));
        assertFalse(ForLoop.isPalindrome("abbaa"));
        assertFalse(ForLoop.isPalindrome("abcda"));
    }

    @Test
    void testForSkip() {
        StringBuilder builder = new StringBuilder();
        String string = "123456";
        for (int i = 0; i < string.length(); i += 2)
            builder.append(string.charAt(i));
        assertEquals("135", builder.toString());
    }

    @Test
    void testFibonacci() {
        assertEquals(0, ForLoop.fib(0));
        assertEquals(1, ForLoop.fib(1));
        assertEquals(1, ForLoop.fib(2));
        assertEquals(2, ForLoop.fib(3));
        assertEquals(3, ForLoop.fib(4));
        assertEquals(5, ForLoop.fib(5));
        assertEquals(8, ForLoop.fib(6));
        assertEquals(13, ForLoop.fib(7));
        assertEquals(21, ForLoop.fib(8));
        assertEquals(34, ForLoop.fib(9));
        assertEquals(55, ForLoop.fib(10));
    }

    @Test
    void testCimmas() {
        String sequence = "1,2,3,4,5";
        assertEquals(sequence, sequenceUsingDo(1, 5));
        assertEquals(sequence, sequenceUsingFor(1, 5));
        assertEquals(sequence, sequenceUsingWhile(1, 5));

        sequence = "8";
        assertEquals(sequence, sequenceUsingDo(8, 8));
        assertEquals(sequence, sequenceUsingFor(8, 8));
        assertEquals(sequence, sequenceUsingWhile(8, 8));
    }

    @Test
    void testEndTrim() {
        assertEquals("", endTrim(""));
        assertEquals(" x", endTrim(" x "));
        assertEquals("y", endTrim("y"));
        assertEquals("xaxa", endTrim("xaxa"));
        assertEquals("", endTrim(" "));
        assertEquals("xxx", endTrim("xxx   "));
    }

    @Test
    void testLabeledBreak() {
        List<List<String>> table = new ArrayList<>();

        List<String> row1 = new ArrayList<>();
        row1.add("5");
        row1.add("2");
        List<String> row2 = new ArrayList<>();
        row2.add("3");
        row2.add("4");

        table.add(row1);
        table.add(row2);
        assertTrue(found(table, "3"));
        assertFalse(found(table, "8"));
    }

    private boolean found(List<List<String>> table, String target) {
        boolean found = false;
        search:
        for (List<String> row : table)
            for (String value : row)
                if (value.equals(target)) {
                    found = true;
                    break search;
                }
        return found;
    }

    private String endTrim(String source) {
        int i = source.length();
        while (--i >= 0)
            if (source.charAt(i) != ' ')
                break;
        return source.substring(0, i + 1);
    }

    private String sequenceUsingDo(int start, int stop) {
        StringBuilder builder = new StringBuilder();
        int i = start;
        do {
            if (i > start)
                builder.append(',');
            builder.append(i);
        } while (++i <= stop);
        return builder.toString();
    }

    private String sequenceUsingFor(int start, int stop) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i <= stop; i++) {
            if (i > start)
                builder.append(',');
            builder.append(i);
        }
        return builder.toString();
    }

    private String sequenceUsingWhile(int start, int stop) {

        StringBuilder builder = new StringBuilder();
        int i = start;
        while (i <= stop) {
            if (i > start)
                builder.append(',');
            builder.append(i);
            i++;
        }
        return builder.toString();
    }
}
import org.junit.jupiter.api.Test;

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
        assertEquals(0, fib(0));
        assertEquals(1, fib(1));
        assertEquals(1, fib(2));
        assertEquals(2, fib(3));
        assertEquals(3, fib(4));
        assertEquals(5, fib(5));
        assertEquals(8, fib(6));
        assertEquals(13, fib(7));
        assertEquals(21, fib(8));
        assertEquals(34, fib(9));
        assertEquals(55, fib(10));
    }

    private int fib(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;
        int fib = 0;
        int nextFib = 1;
        int index = 0;
        int temp;
        do {
            temp = fib + nextFib;
            fib = nextFib;
            nextFib = temp;
        } while (++index < x);
        return fib;
    }

}
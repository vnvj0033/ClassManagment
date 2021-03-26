import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTest {
    @Test
    void testTwoDimensionalArrays() {
        final int rows = 3;
        final int cols = 4;
        int count = 0;
        int[][] matrix = new int[rows][cols];
        for (int x = 0; x < rows; x++)
            for (int y = 0; y < cols; y++)
                matrix[x][y] = count++;
        assertEquals(11, matrix[2][3]);
        assertEquals(6, matrix[1][2]);
    }

    @Test
    void testPartialDimensions() {
        final int rows = 3;
        int[][] matrix = new int[rows][];
        matrix[0] = new int[]{0};
        matrix[1] = new int[]{1, 2};
        matrix[2] = new int[]{3, 4, 5};
        assertEquals(matrix[1][0], 1);
        assertEquals(matrix[2][2], 5);
    }

    @Test
    void testArrayEquality() {
        int[] a = {1,2,3};
        int[] b = {1,2,3};
        assertFalse(a == b);
    }

    @Test
    void testArrayEquals() {
        int[] a = {1,2,3};
        int[] b = {1,2,3};
        assertFalse(a.equals(b));
    }

    @Test
    void testArraysEquals() {
        int[] a = {1,2,3};
        int[] b = {1,2,3};
        assertTrue(Arrays.equals(a,b));
    }
}
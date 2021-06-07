package util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtilTest {

    @Test
    public void testPad() {
        final int count = 5;
        List<Date> list = new ArrayList<>();
        final Date element = new Date();
        ListUtil.pad(list, element, count);
        assertEquals(count, list.size());
        for (int i = 0; i < count; i++)
            assertEquals(element, list.get(i), "unexpected element at " + i);
    }

    @Test
    public void testWildcardCapture() {
        List<String> names = new ArrayList<>();
        names.add("alpha");
        names.add("beta");
        inPlaceReverse(names);
        assertEquals("beta", names.get(0));
        assertEquals("alpha", names.get(1));
    }

    @Test
    private void inPlaceReverse(List<?> list) {
        int size = list.size();
        for (int i = 0; i < size / 2; i++)
            swap(list, i, size - 1 - i);
    }

    private static <T> void swap(List<T> list, int i, int opposite) {
        T temp = list.get(i);
        list.set(i, list.get(opposite));
        list.set(opposite, temp);
    }
}

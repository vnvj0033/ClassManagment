package util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtilTest {

    @Test
    public void testPad(){
        final int count = 5;
        List<Date> list = new ArrayList<>();
        final Date element = new Date();
        ListUtil.pad(list, element, count);
        assertEquals(count,list.size());
        for (int i = 0; i < count; i++)
            assertEquals(element, list.get(i), "unexpected element at " + i);
    }
}

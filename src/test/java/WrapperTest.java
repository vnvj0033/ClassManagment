import org.junit.jupiter.api.Test;
import studentInfo.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WrapperTest {
    @Test
    void testUnBoxing(){
        int x = new Integer(5);
        assertEquals(5, x);
    }

    @Test
    void testUnboxingMath(){
        assertEquals(10, new Integer(2) * new Integer(5));
    }

}
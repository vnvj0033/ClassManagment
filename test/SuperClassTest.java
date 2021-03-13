import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SuperClassTest {

    @Test
    void testConstructorCalls() {
        SuperClass superClass = new SubClass("parm");
        assertTrue(superClass.constructorWasCalled);
    }
}

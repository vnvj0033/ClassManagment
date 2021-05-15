package testing;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TestRunnerTest {
    public void singleMethodTest() {
        TestRunner runner = new TestRunner(SingleMethodTest.class);

        Set<Method> testMethods = runner.getTestMethods();
        assert 1 == testMethods.size() : "expected single test method";

        Iterator<Method> it = testMethods.iterator();
        Method method = it.next();

        final String testMethodsName = "testA";
        assert testMethodsName.equals(method.getName()) : "expected " + testMethodsName + "as test method";
        runner.run();
        assert 1 == runner.passed() : "expected 1 pass";
        assert 2 == runner.failed() : "expected no failures";
    }

    public void multipleMethodTest() {
        TestRunner runner = new TestRunner(MultipleMethodTest.class);
        runner.run();

        assert 2 == runner.passed() : "expected 2 pass";
        assert 0 == runner.failed() : "expected no failures";

        Set<Method> testMethods = runner.getTestMethods();
        assert 2 == testMethods.size() : "expected single test method";

        Set<String> methodNames = new HashSet<>();
        for (Method method : testMethods)
            methodNames.add(method.getName());

        final String testMethodNameA = "testA";
        final String testMethodNameB = "testB";

        assert methodNames.contains(testMethodNameA) : "expected " + testMethodNameA + " as test method";
        assert methodNames.contains(testMethodNameB) : "expected " + testMethodNameB + " as test method";
    }
}

class SingleMethodTest {
    public void testA() {
    }
}

class MultipleMethodTest {
    public void testB() {
    }
}

package testing;

import java.lang.reflect.Method;
import java.util.*;

public class TestRunnerTest {
    public static final String IGNORE_REASON1 = "because";

    private TestRunner runner;
    private static final String methodNameA = "testA";
    private static final String methodNameB = "testB";

    @TestMethod
    public void ignoreMethod() {
        runTests(IgnoreMethodTest.class);
        verifyTests(methodNameA, methodNameB);
        assertIgnoreReasons();
    }

    @TestMethod
    public void singleMethodTest() {
        runTests(SingleMethodTest.class);
        verifyTests(methodNameA);
    }

    @TestMethod
    public void multipleMethodTest() {
        runTests(MultipleMethodTest.class);
        verifyTests(methodNameA, methodNameB);
    }

    private void assertIgnoreReasons() {
        Map<Method, Ignore> ignoreMethod = runner.getIgnoredMethods();
        Map.Entry<Method, Ignore> entry = getSoleEntry(ignoreMethod);
        assert "testC".equals(entry.getKey().getName()) : "unexpected ignore method: " + entry.getKey();
        Ignore ignore = entry.getValue();
        assert IGNORE_REASON1.equals(ignore.value());
    }

    private <K, V> Map.Entry<K, V> getSoleEntry(Map<K, V> map) {
        assert 1 == map.size() : "expected one entry";
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        return it.next();
    }

    private void runTests(Class testClass) {
        runner = new TestRunner(testClass);
        runner.run();
    }

    private void verifyTests(String... expectedTestMethodeNames) {
        verifyNumberOfTests(expectedTestMethodeNames);
        verifyMethodNames(expectedTestMethodeNames);
        verifyCounts(expectedTestMethodeNames);
    }

    private void verifyCounts(String... testMethodeNames) {
        assert testMethodeNames.length == runner.passed() : "expected " + testMethodeNames.length + "passed";
        assert 0 == runner.failed() : "expected no failures";
    }

    private void verifyNumberOfTests(String... testMethodeNames) {
        assert testMethodeNames.length == runner.getTestMethods().size() : "expected " + testMethodeNames + " test method(s)";
    }

    private void verifyMethodNames(String... testMethodeNames) {
        Set<String> actualMethodNames = getTestMethodNames();
        for (String methodName : testMethodeNames)
            assert actualMethodNames.contains(methodName) : "expected " + methodName + " as test method";
    }

    private Set<String> getTestMethodNames() {
        Set<String> methodNames = new HashSet<>();
        for (Method method : runner.getTestMethods())
            methodNames.add(method.getName());
        return methodNames;
    }
}

class SingleMethodTest {
    @TestMethod
    public void testA() {
    }
}

class MultipleMethodTest {
    @TestMethod
    public void testA() {

    }

    @TestMethod
    public void testB() {
    }
}

class IgnoreMethodTest {
    @TestMethod public void testA() {}
    @TestMethod public void testB() {}

    @Ignore(TestRunnerTest.IGNORE_REASON1)
    @TestMethod public void testC() {}
}

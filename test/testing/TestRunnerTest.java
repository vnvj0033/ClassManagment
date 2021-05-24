package testing;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestRunnerTest {
    private TestRunner runner;
    private static final String methodNameA = "testA";
    private static final String methodNameB = "testB";

    @TestMethod
    public void ignoreMethod() {
        runTests(IgnoreMethodTest.class);
        verifyTests(methodNameA, methodNameB);
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

    @Ignore
    @TestMethod public void testC() {}
}

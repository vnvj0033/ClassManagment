package studentInfo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import security.Permission;
import security.PermissionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountFactoryTest {
    private List<Method> updateMethods;
    private List<Method> readOnlyMethods;

    @BeforeEach
    void setUp() throws Exception {
        updateMethods = new ArrayList<>();
        addUpdateMethod("setBankAba", String.class);
        addUpdateMethod("setBankAccountNumber", String.class);
        addUpdateMethod("setBankAccountType", Account.BankAccountType.class);
        addUpdateMethod("transferFromBank", BigDecimal.class);
        addUpdateMethod("credit", BigDecimal.class);

        readOnlyMethods = new ArrayList<>();
        addReadOnlyMethod("getBalance");
        addReadOnlyMethod("transactionAverage");

    }

    private void addUpdateMethod(String name, Class parmClass) throws Exception {
        updateMethods.add(Accountable.class.getDeclaredMethod(name, parmClass));
    }

    private void addReadOnlyMethod(String name) throws Exception {
        Class[] noParms = new Class[]{};
        readOnlyMethods.add(Accountable.class.getDeclaredMethod(name, noParms));
    }

    @Test
    public void testUpdateAccess() throws Exception {
        Accountable account = AccountFactory.create(Permission.UPDATE);
        for (Method method : readOnlyMethods)
            verifyNoException(method, account);
        for (Method method : updateMethods)
            verifyNoException(method, account);

    }

    @Test
    public void testReadOnlyAccess() throws Exception {
        Accountable account = AccountFactory.create(Permission.READ_ONLY);
        for (Method method : updateMethods)
            verifyException(PermissionException.class, method, account);
        for (Method method : readOnlyMethods)
            verifyNoException(method, account);
    }

    private void verifyException(Class exceptionType, Method method, Object object) throws Exception {
        try {
            method.invoke(object, nullParmsFor(method));
            fail("expected expection");
        } catch (InvocationTargetException e) {
            assertEquals(exceptionType, e.getCause().getClass(), "expected exception");
        }
    }

    private void verifyNoException(Method method, Object object) throws Exception {
        try {
            method.invoke(object, nullParmsFor(method));
        } catch (InvocationTargetException e) {
            assertFalse(PermissionException.class == e.getCause().getClass(), "unexpected permission exception");
        }
    }

    private Object[] nullParmsFor(Method method) {
        return new Object[method.getParameterTypes().length];
    }
}

package studentInfo;

import security.Permission;
import security.SecureProxy;

import java.lang.reflect.Proxy;

public class AccountFactory {

    public static Accountable create(Permission permission) {
        switch (permission) {
            case UPDATE:
                return new Account();
            case READ_ONLY:
                return createSecuredAccount();
        }
        return null;
    }

    private static Accountable createSecuredAccount() {
        SecureProxy secureAccount = new SecureProxy(new Account(), "credit", "setBankAba", "setBankAccountNumber", "setBankAccountType", "transferFromBank");
        return (Accountable) Proxy.newProxyInstance(    // newProxyInstance호출 인수화 되지 않았으니 Object를 반환
                Accountable.class.getClassLoader(),     // 첫번째 인수, 인터페이스 클래스의 로더
                new Class[]{Accountable.class},         // 두번째 인수, 동적프록시를 생성하는 인터패이스형 배열
                secureAccount);                         // 세번째 인수, 프록시 객체 InvocationHandler
    }

}
package studentInfo;

import com.jimbob.ach.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountTest {
    static final String ABA = "102000012";
    static final String ACCOUNT_NUMBER = "194431518811";

    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setBankAba(ABA);
        account.setBankAccountNumber(ACCOUNT_NUMBER);
        account.setBankAccountType(Account.BankAccountType.CHECKING);
    }

    @Test
    void testTransactions() {
        Account account = new Account();
        account.credit(new BigDecimal("0.10"));
        account.credit(new BigDecimal("11.00"));
        account.credit(new BigDecimal("2.99"));
        assertEquals(new BigDecimal("4.70"), account.transactionAverage());
    }

    @Test
    void testTransferFromBank() {
        account.setAch(createMockAch(AchStatus.SUCCESS));
        final BigDecimal amount = new BigDecimal("50.00");
        account.transferFromBank(amount);
        assertEquals(amount, account.getBalance());
    }

    @Test
    void testFailedTransferFromBank() {
        account.setAch(createMockAch(AchStatus.FAILURE));
        final BigDecimal amount = new BigDecimal("50.00");
        account.transferFromBank(amount);
        assertEquals(new BigDecimal("0.00"), account.getBalance());
    }

    @Test
    void testWithdraw() throws Exception {
        account.credit(new BigDecimal("100.00"));
        account.withdraw(new BigDecimal("40.00"));
        assertEquals(new BigDecimal("60.00"), account.getBalance());
    }

    @Test
    void testWithdrawInsufficientFunds() {
        account.credit(new BigDecimal("100.00"));
        account.withdraw(new BigDecimal("140.00"));
        assertEquals(new BigDecimal("100.00"), account.getBalance());
    }

    private Ach createMockAch(final AchStatus status){
        return new MockAch(){
            public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
                assertTrue(data.account.equals(AccountTest.ACCOUNT_NUMBER));
                assertTrue(data.aba.equals(AccountTest.ABA));

                AchResponse response = new AchResponse();
                response.timeStamp = new Date();
                response.traceCode = "1";
                response.status = status;
                return response;
            }
        };
    }
}

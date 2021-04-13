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
        Ach mockAch = new Ach() {
            @Override
            public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
                assertTrue(data.account.equals(AccountTest.ACCOUNT_NUMBER));
                assertTrue(data.aba.equals(AccountTest.ABA));

                AchResponse response = new AchResponse();
                response.timeStamp = new Date();
                response.traceCode = "1";
                response.status = AchStatus.SUCCESS;
                return response;
            }

            @Override
            public AchResponse markTransactionAsNSF(AchCredentials credentials, AchTransactionData data, String traceCode) {
                return null;
            }

            @Override
            public AchResponse refundTransaction(AchCredentials credentials, AchTransactionData data, String traceCode) {
                return null;
            }

            @Override
            public AchResponse issueCredit(AchCredentials credentials, AchTransactionData data) {
                return null;
            }

            @Override
            public AchResponse voidSameDayTransaction(AchCredentials credentials, AchTransactionData data, String traceCode) {
                return null;
            }

            @Override
            public AchResponse queryTransactionStatus(AchCredentials credentials, AchTransactionData data, String traceCode) {
                return null;
            }
        };

        account.setAch(mockAch);

        final BigDecimal amount = new BigDecimal("50.00");
        account.transferFormBank(amount);

        assertEquals(amount, account.getBalance());
    }
}

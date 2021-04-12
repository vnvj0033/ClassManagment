package studentInfo;

import com.jimbob.ach.*;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockAch implements Ach {

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
}
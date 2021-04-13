package studentInfo;

import com.jimbob.ach.*;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MockAch implements Ach {

    @Override
    public AchResponse issueDebit(AchCredentials credentials, AchTransactionData data) {
        return null;
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
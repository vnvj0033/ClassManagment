package studentInfo;

import com.jimbob.ach.Ach;
import com.jimbob.ach.AchCredentials;
import com.jimbob.ach.AchResponse;
import com.jimbob.ach.AchTransactionData;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {
    private BigDecimal balance = new BigDecimal("0.00");
    private int transactionCount = 0;
    private String bankAba;
    private String bankAccountNumber;
    private BankAccountType bankAccountType;
    private Ach ach;

    public enum BankAccountType {
        CHECKING("ck"), SAVINGS("sv");

        private String value;

        BankAccountType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }


    }

    public void credit(BigDecimal amount) {
        this.balance = balance.add(amount);
        transactionCount++;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal transactionAverage() {
        return balance.divide(new BigDecimal(transactionCount), RoundingMode.HALF_UP);
    }

    public void setBankAba(String bankAba) {
        this.bankAba = bankAba;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public void setBankAccountType(BankAccountType bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public void transferFormBank(BigDecimal amount) {
        AchCredentials credentials = createCredentials();

        AchTransactionData data = createData(amount);

        Ach ach = getAch();
        AchResponse achResponse = ach.issueDebit(credentials, data);

        credit(amount);
    }

    private AchCredentials createCredentials() {
        AchCredentials credentials = new AchCredentials();
        credentials.merchantId = "12355";
        credentials.userName = "sismerc1920";
        credentials.password = "pitseleh411";
        return credentials;
    }

    private AchTransactionData createData(BigDecimal amount) {
        AchTransactionData data = new AchTransactionData();
        data.description = "transfer from bank";
        data.amount = amount;
        data.account = bankAccountNumber;
        data.accountType = bankAccountType.toString();
        return data;
    }

    private Ach getAch() {
        return ach;
    }

    private void setAch(Ach ach) {
        this.ach = ach;
    }
}

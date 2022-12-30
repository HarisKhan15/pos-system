package domain;

import java.util.Date;

public class Transactions {
    private Integer TransactionId;
    private String UserId;
    private Date TransactionDate;
    private Double Amount;

    public Transactions(Integer transactionId, String userId, Date transactionDate, Double amount) {
        TransactionId = transactionId;
        UserId = userId;
        TransactionDate = transactionDate;
        Amount = amount;
    }

    public Integer getTransactionId() {
        return TransactionId;
    }

    public void setTransactionId(Integer transactionId) {
        TransactionId = transactionId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public Date getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        TransactionDate = transactionDate;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "TransactionId=" + TransactionId +
                ", UserId=" + UserId +
                ", TransactionDate=" + TransactionDate +
                ", Amount=" + Amount +
                '}';
    }


}

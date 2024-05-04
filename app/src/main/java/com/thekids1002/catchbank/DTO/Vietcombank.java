package com.thekids1002.catchbank.DTO;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.Date;

public class Vietcombank {
    public String accountNumber;
    public long depositAmount;
    public String transactionTime;
    public String accountBalanceAfterTransaction;
    public String referenceNumber;
    public String transactionContent;

    public Vietcombank(String accountNumber, long depositAmount, String transactionTime, String accountBalanceAfterTransaction, String referenceNumber, String transactionContent) {
        this.accountNumber = accountNumber;
        this.depositAmount = depositAmount;
        this.transactionTime = transactionTime;
        this.accountBalanceAfterTransaction = accountBalanceAfterTransaction;
        this.referenceNumber = referenceNumber;
        this.transactionContent = transactionContent;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public long getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(long depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getAccountBalanceAfterTransaction() {
        return accountBalanceAfterTransaction;
    }

    public void setAccountBalanceAfterTransaction(String accountBalanceAfterTransaction) {
        this.accountBalanceAfterTransaction = accountBalanceAfterTransaction;
    }


    public Vietcombank() {
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getTransactionContent() {
        return transactionContent;
    }

    public void setTransactionContent(String transactionContent) {
        this.transactionContent = transactionContent;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
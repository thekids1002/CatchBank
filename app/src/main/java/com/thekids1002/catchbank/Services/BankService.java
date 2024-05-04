package com.thekids1002.catchbank.Services;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.thekids1002.catchbank.DTO.Acbbank;
import com.thekids1002.catchbank.DTO.PostRequest;
import com.thekids1002.catchbank.DTO.Vietcombank;
import com.thekids1002.catchbank.Database.TransactionACBHelper;
import com.thekids1002.catchbank.Database.TransactionVCBHelper;
import com.thekids1002.catchbank.Utils.DateUtil;
import com.thekids1002.catchbank.Utils.LogUtil;

import java.util.ArrayList;

import okhttp3.internal.Util;

public class BankService {
    public static BankService gI = null;
    public static ArrayList<Vietcombank> vietcombanks = null;
    public static ArrayList<Acbbank> acbbanks = null;

    public static BankService gI() {
        if (gI == null) {
            gI = new BankService();
            vietcombanks = new ArrayList<>();
            acbbanks = new ArrayList<>();
        }
        return gI;
    }

    public BankService() {

    }

    public boolean insertVCB(Vietcombank vcb, Context context){
        ContentValues values = getContentValuesVCB(vcb);
        TransactionVCBHelper helper = new TransactionVCBHelper(context);
        return helper.insert(helper.TABLE_NAME, values) > 0;
    }

    public boolean insertACB(Acbbank vcb, Context context){
        return true;
    }

    @NonNull
    private static ContentValues getContentValuesVCB(Vietcombank vcb) {
        ContentValues values = new ContentValues();
        values.put("account_number", vcb.accountNumber);
        values.put("deposit_amount", vcb.depositAmount);
        values.put("transaction_time", vcb.transactionTime);
        values.put("account_balance", vcb.accountBalanceAfterTransaction);
        values.put("reference_number", vcb.referenceNumber);
        values.put("transaction_content", vcb.transactionContent);
        return values;
    }

    public Vietcombank getVietCombankEntity(PostRequest post){
        Vietcombank vcb = new Vietcombank();
        String text = post.message.toString();
        if(text != null || !text.isEmpty()){
            // Extract account number
            Pattern accountNumberPattern = Pattern.compile("VCB (\\d+)");
            Matcher accountNumberMatcher = accountNumberPattern.matcher(text);
            if (accountNumberMatcher.find()) {
                String accountNumber = accountNumberMatcher.group(1);
                vcb.setAccountNumber(accountNumber);
            }

            // Extract deposited amount
            Pattern amountPattern = Pattern.compile("\\+(\\d+(?:,\\d+)*) VND");
            Matcher amountMatcher = amountPattern.matcher(text);
            if (amountMatcher.find()) {
                String amount = amountMatcher.group(1).replace(",", "");
                vcb.setDepositAmount(Long.parseLong(amount));
            }

            // Extract transaction date and time
            Pattern datetimePattern = Pattern.compile("lúc (\\d{2}-\\d{2}-\\d{4} \\d{2}:\\d{2}:\\d{2})");
            Matcher datetimeMatcher = datetimePattern.matcher(text);
            if (datetimeMatcher.find()) {
                String datetime = datetimeMatcher.group(1);
                vcb.setTransactionTime(datetime);
            }

            // Extract balance after transaction
            Pattern balancePattern = Pattern.compile("Số dư (.+?) VND");
            Matcher balanceMatcher = balancePattern.matcher(text);
            if (balanceMatcher.find()) {
                String balance = balanceMatcher.group(1).replace(",", "");
                vcb.setAccountBalanceAfterTransaction(balance);
            }

            // Extract reference number
            Pattern refNumberPattern = Pattern.compile("Ref (\\d+\\.\\d+\\.\\d+)");
            Matcher refNumberMatcher = refNumberPattern.matcher(text);
            if (refNumberMatcher.find()) {
                String refNumber = refNumberMatcher.group(1);
                vcb.setReferenceNumber(refNumber);
            }

            // Extract sender
            Pattern senderPattern = Pattern.compile("\\.([^\\.]+) chuyen tien");
            Matcher senderMatcher = senderPattern.matcher(text);
            if (senderMatcher.find()) {
                String sender = senderMatcher.group(1);
                vcb.setTransactionContent(sender);
            }
        }
        return vcb;
    }

    public ArrayList<Vietcombank> getAllVcbs(Context context) {
        return new TransactionVCBHelper(context).getAllVCBData();
    }
}

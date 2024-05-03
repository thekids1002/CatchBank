package com.thekids1002.catchbank.DTO;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class PostRequest {
    public String message;
    public String bankName;


    public PostRequest(String message, String namebank) {
        this.message = message;
        this.bankName = namebank;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

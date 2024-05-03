package com.thekids1002.catchbank.DTO;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class PostRequest {
    public String message;
    public String namebank;


    public PostRequest(String message, String namebank) {
        this.message = message;
        this.namebank = namebank;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

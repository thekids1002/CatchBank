package com.thekids1002.catchbank.DTO;

import com.google.gson.Gson;

public class PostResponse {
    public String message;

    public PostResponse(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

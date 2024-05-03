package com.thekids1002.catchbank.Services.retrofit_services;

import com.thekids1002.catchbank.DTO.PostRequest;
import com.thekids1002.catchbank.DTO.PostResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;
public interface  RequestPostService {
    @POST("")
    Call<PostResponse> postBank(@Body PostRequest info);
}

package com.example.doanandroid02.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit = null;


    public static Retrofit getClient(String base_url){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(1000000, TimeUnit.MICROSECONDS)
                .writeTimeout(1000000, TimeUnit.MICROSECONDS)
                .connectTimeout(1000000, TimeUnit.MICROSECONDS)
                .retryOnConnectionFailure(true).protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit= new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit;
    }
}

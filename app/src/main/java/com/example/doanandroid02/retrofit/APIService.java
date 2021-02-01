package com.example.doanandroid02.retrofit;

public class APIService {

    private static String base_url = "http://127.0.0.1/DoAnLaravel_2/public/";

    public static DataClient getService(){

        return RetrofitClientInstance.getClient(base_url).create(DataClient.class);
    }
}

package com.iiuifyp.location.base.attendance.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iiuifyp.location.base.attendance.url.EndPoint;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //GSON support init
    private static Gson gson = new GsonBuilder().setLenient().create();
    //okhttp client  init
    private static OkHttpClient
            okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();
    //ini retorfit using singelton pattern
    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(EndPoint.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}


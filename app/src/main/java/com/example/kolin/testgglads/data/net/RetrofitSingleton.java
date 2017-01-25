package com.example.kolin.testgglads.data.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kolin on 25.01.2017.
 */

public class RetrofitSingleton {

    private static final String BASE_URL = "https://api.producthunt.com/v1/";
    private static Retrofit retrofit = null;

    private RetrofitSingleton() {
    }

    public static Retrofit getInstance() {
        if (retrofit == null) {

            HttpLoggingInterceptor interceptor =
                    new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BASIC);

            OkHttpClient client =
                    new OkHttpClient
                            .Builder()
                            .addInterceptor(interceptor)
                            .build();

            retrofit =
                    new Retrofit
                            .Builder()
                            .baseUrl(BASE_URL)
                            .client(client)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        }
        return retrofit;
    }
}

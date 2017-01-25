package com.example.kolin.testgglads.data.net;

/**
 * Created by kolin on 25.01.2017.
 */

public class ApiSingleton {

    private static Api api = null;
    public static final String TOKEN = "591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff";

    private ApiSingleton() {
    }

    public static Api getApi() {
        if (api == null){
            api = RetrofitSingleton.getInstance().create(Api.class);
        }
        return api;
    }


}

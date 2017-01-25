package com.example.kolin.testgglads.data.net;

import com.example.kolin.testgglads.data.entities.CategoryEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kolin on 25.01.2017.
 */

public interface Api {

    @GET("categories/?")
    Observable<CategoryEntity> getCategories (@Query("access_token") String token);

}

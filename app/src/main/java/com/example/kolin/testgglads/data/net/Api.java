package com.example.kolin.testgglads.data.net;

import com.example.kolin.testgglads.data.entities.ListCategoryEntity;
import com.example.kolin.testgglads.data.entities.ListPostEntity;
import com.example.kolin.testgglads.presentation.list.ListAdapter;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kolin on 25.01.2017.
 */

public interface Api {

    @GET("categories/?")
    Observable<ListCategoryEntity> getCategories (@Query("access_token") String token);

    @GET("categories/{category_name}/posts/?")
    Observable<ListPostEntity> getPosts(@Path("category_name") String categoryName,
                                        @Query("access_token") String token);
}

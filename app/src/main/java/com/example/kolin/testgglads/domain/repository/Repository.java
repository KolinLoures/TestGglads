package com.example.kolin.testgglads.domain.repository;


import com.example.kolin.testgglads.domain.model.Category;
import com.example.kolin.testgglads.domain.model.Post;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by kolin on 25.01.2017.
 */

public interface Repository {

    Observable<List<Category>> getCategories();

    Observable<List<Post>> getPosts(String categoryName);

}

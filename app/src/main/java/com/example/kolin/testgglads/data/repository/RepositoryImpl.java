package com.example.kolin.testgglads.data.repository;

import com.example.kolin.testgglads.data.entities.CategoryEntity;
import com.example.kolin.testgglads.data.net.Api;
import com.example.kolin.testgglads.data.net.ApiSingleton;
import com.example.kolin.testgglads.domain.model.Category;
import com.example.kolin.testgglads.domain.repository.Repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by kolin on 25.01.2017.
 */

public class RepositoryImpl implements Repository {

    private Api api;

    public RepositoryImpl() {
        this.api = ApiSingleton.getApi();
    }


    @Override
    public Observable<List<Category>> getCategories() {
        return api
                .getCategories(ApiSingleton.TOKEN)
                .flatMap(new Function<CategoryEntity, Observable<List<Category>>>() {
                    @Override
                    public Observable<List<Category>> apply(CategoryEntity categoryEntity) throws Exception {
                        return Observable.just(categoryEntity.getCategories());
                    }
                });
    }
}

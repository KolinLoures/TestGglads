package com.example.kolin.testgglads.data.repository;

import android.provider.ContactsContract;

import com.example.kolin.testgglads.data.DataMapper;
import com.example.kolin.testgglads.data.entities.ListCategoryEntity;
import com.example.kolin.testgglads.data.entities.ListPostEntity;
import com.example.kolin.testgglads.data.net.Api;
import com.example.kolin.testgglads.data.net.ApiSingleton;
import com.example.kolin.testgglads.domain.model.Category;
import com.example.kolin.testgglads.domain.model.Post;
import com.example.kolin.testgglads.domain.repository.Repository;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
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
                .flatMap(new Function<ListCategoryEntity, Observable<List<Category>>>() {
                    @Override
                    public Observable<List<Category>> apply(ListCategoryEntity listCategoryEntity) throws Exception {
                        return Observable.just(DataMapper.mapToCategory(listCategoryEntity.getCategories()));
                    }
                });
    }

    @Override
    public Observable<List<Post>> getPosts(String categoryName) {
        return api.getPosts(categoryName, ApiSingleton.TOKEN).flatMap(new Function<ListPostEntity, Observable<List<Post>>>() {
            @Override
            public Observable<List<Post>> apply(ListPostEntity listPostEntity) throws Exception {
                return Observable.just(DataMapper.mapToPost(listPostEntity.getPosts()));
            }
        });
    }
}

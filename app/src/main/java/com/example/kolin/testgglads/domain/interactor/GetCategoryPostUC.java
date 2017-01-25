package com.example.kolin.testgglads.domain.interactor;

import com.example.kolin.testgglads.data.repository.RepositoryImpl;
import com.example.kolin.testgglads.domain.model.Post;
import com.example.kolin.testgglads.domain.repository.Repository;
import com.example.kolin.testgglads.domain.usecase.AbstractUC;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by kolin on 25.01.2017.
 */

public class GetCategoryPostUC extends AbstractUC<List<Post>, String>{

    private Repository repository;

    public GetCategoryPostUC() {
        super();
        this.repository = new RepositoryImpl();
    }

    @Override
    protected Observable<List<Post>> buildObservable(String categoryName) {
        return repository.getPosts(categoryName);
    }
}

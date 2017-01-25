package com.example.kolin.testgglads.domain.interactor;

import com.example.kolin.testgglads.data.repository.RepositoryImpl;
import com.example.kolin.testgglads.domain.model.Category;
import com.example.kolin.testgglads.domain.repository.Repository;
import com.example.kolin.testgglads.domain.usecase.AbstractUC;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by kolin on 25.01.2017.
 */

public class GetCategoriesUC extends AbstractUC<List<Category>, Void> {

    private Repository repository;

    public GetCategoriesUC() {
        super();
        this.repository = new RepositoryImpl();
    }

    @Override
    protected Observable<List<Category>> buildObservable(Void params) {
        return repository.getCategories();
    }
}

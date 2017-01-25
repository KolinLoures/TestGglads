package com.example.kolin.testgglads.presentation.list;

import android.util.Log;

import com.example.kolin.testgglads.domain.interactor.GetCategoriesUC;
import com.example.kolin.testgglads.domain.model.Category;
import com.example.kolin.testgglads.presentation.AbstractPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by kolin on 25.01.2017.
 */

public class ListPresenter extends AbstractPresenter<ListView> {

    private static final String TAG = ListPresenter.class.getSimpleName();

    private GetCategoriesUC getCategoriesUC;

    public ListPresenter() {
        this.getCategoriesUC = new GetCategoriesUC();
    }

    public void loadCategories() {
        getCategories();
    }

    private void getCategories() {
        getCategoriesUC.execute(new CategoriesListObserver(), null);
    }

    private void showCategoriesSpinner(List<Category> categoryList) {
        if (!isViewAttache()) {
            Log.e(TAG, "View is detach");
            return;
        }

        getWeakReference().showCategories(transformCategoryToString(categoryList));
    }

    private final class CategoriesListObserver extends DisposableObserver<List<Category>> {

        @Override
        public void onNext(List<Category> value) {
            showCategoriesSpinner(value);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }


    private List<String> transformCategoryToString(List<Category> categoryList){
        List<String> list = new ArrayList<>();
        for (Category c: categoryList){
            list.add(c.getName());
        }
        return list;
    }
}

package com.example.kolin.testgglads.presentation.list;

import android.util.Log;

import com.example.kolin.testgglads.domain.interactor.GetCategoriesUC;
import com.example.kolin.testgglads.domain.interactor.GetCategoryPostUC;
import com.example.kolin.testgglads.domain.model.Category;
import com.example.kolin.testgglads.domain.model.Post;
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
    private GetCategoryPostUC getCategoryPostUC;

    private List<Category> currentCategories = new ArrayList<>();
    private List<Post> currentPosts = new ArrayList<>();
    private int currentCategoryId = -1;

    public ListPresenter() {
        this.getCategoriesUC = new GetCategoriesUC();
        this.getCategoryPostUC = new GetCategoryPostUC();
    }

    public void loadCategories() {
        getCategories();
    }

    public void loadCategoryPost(int position) {
        if (currentCategoryId != position) {
            if (!isViewAttache()) {
                Log.e(TAG, "View is detach");
                return;
            }

            getWeakReference().clearResult();

            currentCategoryId = position;
            showViewLoading();
            getPostsCategories(currentCategories.get(position).getSlug());
        }
    }

    private void getCategories() {
        getCategoriesUC.execute(new CategoriesListObserver(), null);
    }

    private void getPostsCategories(String categoryName) {
        getCategoryPostUC.execute(new PostsCategoriesObserver(), categoryName);
    }

    private void showViewLoading() {
        if (!isViewAttache()) {
            Log.e(TAG, "View is detach");
            return;
        }

        getWeakReference().setRefreshing(true);
    }

    private void hideViewLoading() {
        if (!isViewAttache()) {
            Log.e(TAG, "View is detach");
            return;
        }

        getWeakReference().setRefreshing(false);
    }

    private void showCategoriesSpinner(List<Category> categoryList) {
        if (!isViewAttache()) {
            Log.e(TAG, "View is detach");
            return;
        }

        currentCategories.clear();
        currentCategories.addAll(categoryList);
        getWeakReference().showCategories(transformCategoryToString(categoryList));
    }

    private void showPosts(List<Post> postList) {
        if (!isViewAttache()) {
            Log.e(TAG, "View is detach");
            return;
        }


        getWeakReference().showPostsCategories(postList);
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

    private final class PostsCategoriesObserver extends DisposableObserver<List<Post>> {
        @Override
        public void onNext(List<Post> value) {
            showPosts(value);
        }

        @Override
        public void onError(Throwable e) {
        }


        @Override
        public void onComplete() {
            hideViewLoading();
        }
    }

    public void disposeAll() {
        detacheView();
        getCategoriesUC.dispose();
        getCategoryPostUC.dispose();
    }

    private List<String> transformCategoryToString(List<Category> categoryList) {
        List<String> list = new ArrayList<>();
        for (Category c : categoryList) {
            list.add(c.getName());
        }
        return list;
    }

}
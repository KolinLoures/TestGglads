package com.example.kolin.testgglads.presentation;

import java.lang.ref.WeakReference;

/**
 * Created by kolin on 25.01.2017.
 */

public abstract class AbstractPresenter<T> {

    private WeakReference<T> weakReference = null;

    public void attacheView(T v) {
        weakReference = new WeakReference<T>(v);
    }

    public void detacheView() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    protected boolean isViewAttache(){
        return weakReference.get() != null && weakReference != null;
    }

    protected T getWeakReference(){
        return weakReference == null ? null : weakReference.get();
    }
}

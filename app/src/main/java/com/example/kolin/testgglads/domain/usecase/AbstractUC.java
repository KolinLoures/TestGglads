package com.example.kolin.testgglads.domain.usecase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kolin on 25.01.2017.
 */

public abstract class AbstractUC<T, P> {

    private CompositeDisposable compositeDisposable;

    public AbstractUC() {
        this.compositeDisposable = new CompositeDisposable();
    }

    protected abstract Observable<T> buildObservable(P params);

    public void execute(DisposableObserver<T> observer, P params) {
        if (observer != null) {
            DisposableObserver<T> disposableObserver =
                    buildObservable(params)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.newThread())
                    .subscribeWith(observer);

            if (compositeDisposable != null){
                compositeDisposable.add(disposableObserver);

            }
        }
    }

    public void dispose(){
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

    public void clearObservers(){
        if (!compositeDisposable.isDisposed()){
            compositeDisposable.clear();
        }
    }

}

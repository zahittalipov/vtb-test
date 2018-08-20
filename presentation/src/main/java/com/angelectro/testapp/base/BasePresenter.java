package com.angelectro.testapp.base;

public abstract class BasePresenter<T extends MvpView> {

    private T mMvpView;

    public BasePresenter(T mMvpView) {
        this.mMvpView = mMvpView;
    }

    public void onDestroy() {
        mMvpView = null;
    }

    protected T getMvpView() {
        return mMvpView;
    }
}


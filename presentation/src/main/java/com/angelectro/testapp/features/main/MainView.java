package com.angelectro.testapp.features.main;

import androidx.annotation.StringRes;
import com.angelectro.testapp.base.MvpView;

public interface MainView extends MvpView {
    void showMessage(@StringRes int msg);
}

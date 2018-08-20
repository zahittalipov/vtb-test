package com.angelectro.testapp.features.main;

import android.content.Context;

import com.angelectro.testapp.R;
import com.angelectro.testapp.base.BasePresenter;
import com.angelectro.testapp.service.MessagesRetrieverService;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;

class MainPresenter extends BasePresenter<MainView> {

    MainPresenter(MainView mMvpView) {
        super(mMvpView);
    }

    void startService(Context context) {
        int status = MessagesRetrieverService.startService(context, 0);
        getMvpView().showMessage(status == FirebaseJobDispatcher.SCHEDULE_RESULT_SUCCESS ? R.string.text_service_started : R.string.text_error_on_service_start);
    }
}

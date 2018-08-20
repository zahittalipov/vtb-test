package com.angelectro.testapp;

import android.app.Application;

import com.angelectro.testapp.di.AppComponent;
import com.angelectro.testapp.di.AppModule;
import com.angelectro.testapp.di.DaggerAppComponent;

public class MessageApp extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        initAppComponent();
    }

    private void initAppComponent() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return component;
    }
}

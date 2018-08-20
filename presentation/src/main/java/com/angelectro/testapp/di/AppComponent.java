package com.angelectro.testapp.di;

import com.angelectro.testapp.service.MessagesRetrieverService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetworkModel.class})
public interface AppComponent {

    void inject(MessagesRetrieverService messagesRetrieverService);
}

package com.angelectro.testapp.di;

import com.angelectro.data.repository.MessageRepositoryImpl;
import com.angelectro.data.service.VtbService;
import com.angelectro.data.service.VtbServiceImpl;
import com.angelectro.domain.repository.MessageRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class NetworkModel {

    @Singleton
    @Provides
    Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
    }

    @Singleton
    @Provides
    VtbService provideService(VtbServiceImpl vtbService) {
        return vtbService;
    }

    @Singleton
    @Provides
    MessageRepository provideMessageRepository(MessageRepositoryImpl messageRepository) {
        return messageRepository;
    }
}

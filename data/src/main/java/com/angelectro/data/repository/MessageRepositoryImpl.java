package com.angelectro.data.repository;


import com.angelectro.data.mapper.MessageEntityToModelMapper;
import com.angelectro.data.service.VtbService;
import com.angelectro.domain.model.MessageModel;
import com.angelectro.domain.repository.MessageRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

public class MessageRepositoryImpl implements MessageRepository {

    @Inject
    VtbService service;

    @Inject
    MessageEntityToModelMapper mapper;

    @Inject
    public MessageRepositoryImpl() {

    }

    @Override
    public Single<List<MessageModel>> getAll() {
        return service.getMessages()
                .toObservable()
                .flatMap(Observable::fromIterable)
                .map(mapper)
                .toList();
    }
}

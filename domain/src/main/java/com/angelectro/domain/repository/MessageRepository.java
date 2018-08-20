package com.angelectro.domain.repository;

import com.angelectro.domain.model.MessageModel;

import java.util.List;

import io.reactivex.Single;

public interface MessageRepository {
    Single<List<MessageModel>> getAll();
}

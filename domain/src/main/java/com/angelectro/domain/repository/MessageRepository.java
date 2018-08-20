package com.angelectro.domain.repository;

import com.angelectro.domain.data.MessageModel;

import java.util.List;

import io.reactivex.Single;

public interface MessageRepository {
    Single<List<MessageModel>> getAll();
}

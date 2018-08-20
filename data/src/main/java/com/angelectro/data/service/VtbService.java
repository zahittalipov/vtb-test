package com.angelectro.data.service;

import com.angelectro.data.entity.MessageEntity;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

public interface VtbService {
    Single<List<MessageEntity>> getMessages();
}

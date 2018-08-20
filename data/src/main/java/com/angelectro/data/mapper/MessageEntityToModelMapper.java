package com.angelectro.data.mapper;

import com.angelectro.data.entity.MessageEntity;
import com.angelectro.domain.data.MessageModel;

import javax.inject.Inject;

import io.reactivex.functions.Function;


public class MessageEntityToModelMapper implements Function<MessageEntity, MessageModel> {

    @Inject
    public MessageEntityToModelMapper() {
    }

    @Override
    public MessageModel apply(MessageEntity messageEntity) {
        return new MessageModel(
                messageEntity.getId(),
                messageEntity.getText(),
                messageEntity.getSubject(),
                messageEntity.getStartDateTime(),
                messageEntity.getEndDateTime()
        );
    }
}

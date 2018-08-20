package com.angelectro.data.service;


import com.angelectro.data.entity.MessageEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Single;

public class VtbServiceImpl implements VtbService {

    private final static String HTTP_URL = "https://disk.tsft.ru/vtb";

    @Inject
    Gson gson;

    @Inject
    public VtbServiceImpl() { }

    @Override
    public Single<List<MessageEntity>> getMessages() {

        return Single.fromCallable(this::getAllMessages);
    }

    private List<MessageEntity> getAllMessages() throws IOException {
        List<MessageEntity> messages;

        HttpsURLConnection connection = null;

        try {
            connection = (HttpsURLConnection) new URL(HTTP_URL).openConnection();
            connection.setRequestMethod("GET"); // установка метода получения данных -GET
            connection.setReadTimeout(30000); // установка таймаута перед выполнением - 10 000 миллисекунд
            connection.setConnectTimeout(30000);
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuilder builder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            messages = gson.fromJson(builder.toString(), new TypeToken<List<MessageEntity>>() {
            }.getType());

        } finally {
            if (connection != null) connection.disconnect();
        }

        return messages;
    }
}

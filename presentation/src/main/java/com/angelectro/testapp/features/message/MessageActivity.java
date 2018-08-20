package com.angelectro.testapp.features.message;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.angelectro.testapp.R;
import com.angelectro.testapp.base.MvpView;

public class MessageActivity extends AppCompatActivity implements MvpView {

    public static final String KEY_CONTENT = "content";

    TextView messageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        messageView = findViewById(R.id.text);
        onNewIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra(KEY_CONTENT);
        messageView.setText(message);
    }
}

package com.angelectro.testapp.features.main;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.angelectro.testapp.R;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter presenter;
    private TextView statusView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusView = findViewById(R.id.jobStatusText);

        presenter = new MainPresenter(this);
        presenter.startService(this);

    }

    @Override
    public void showMessage(int msg) {
        statusView.setText(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}

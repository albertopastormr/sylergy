package com.example.sylergy.activities.testsactivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.sylergy.R;
import com.example.sylergy.logs.LogException;
import com.example.sylergy.logs.Logs;
import com.example.sylergy.logs.LogsView;

public class TestViewLogsView extends AppCompatActivity {
    Button logButtonException;
    Button logButtonNoException;

    @Override
    protected void onCreate(Bundle savedInstace) {
        super.onCreate(savedInstace);
        setContentView(R.layout.test_log_view);

        logButtonException = findViewById(R.id.log_button_exception);
        logButtonException.setOnClickListener(listenerWithException);

        logButtonNoException = findViewById(R.id.log_button_no_exception);
        logButtonNoException.setOnClickListener(listenerWithoutException);
    }

    View.OnClickListener listenerWithException = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                throw new LogException(Logs.UNKNOWN_ERROR, TestViewLogsView.this);
            }
            catch (LogException e) {}
        }
    };

    View.OnClickListener listenerWithoutException = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LogsView view = new LogsView(Logs.UNKNOWN_ERROR);
            view.showInfo(TestViewLogsView.this);
        }
    };


}

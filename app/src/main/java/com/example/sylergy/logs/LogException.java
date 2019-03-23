package com.example.sylergy.logs;

import android.app.Activity;

public class LogException extends Exception {
    private LogsView logsView;

    public LogException(String log, Activity activity){
        super(log);
        this.logsView = new LogsView(log);
        logsView.showInfo(activity);
    }
    public LogException(String log){
        super(log);
        this.logsView = new LogsView(log);


    }
    public void showInfo(Activity activity){
        logsView.showInfo(activity);
    }

}

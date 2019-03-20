package com.example.sylergy.logs;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class LogsView{
    private String log;
    public LogsView(String log){
        this.log = log;
    }
    public void showInfo(Activity activity){
        this.onToast(activity);
    }
    private void onAlertDialog(Activity activity) {
        AlertDialog alert = new AlertDialog.Builder(activity).create();
        //alert.setTitle("WARNING");
        alert.setMessage(log);
        alert.show();
    }
    private void onToast(Activity activity) {
        Toast toast = Toast.makeText(activity, log, Toast.LENGTH_LONG);
        //toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }
}

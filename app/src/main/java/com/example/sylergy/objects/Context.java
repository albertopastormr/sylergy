package com.example.sylergy.objects;

import android.app.Activity;

import com.example.sylergy.activities.UpdateActivity;

public class Context {
    private String event;
    private Object data;
    private Activity activity;
    private UpdateActivity updtActivity;

    public Context(String event, Object data, Activity activity, UpdateActivity updtActivity) {
        this.data = data;
        this.event = event;
        this.activity = activity;
        this.updtActivity = updtActivity;
    }

    public Context(String event, Object data){
        this.event = event;
        this.data = data;
    }

    public String getEvent() {
        return event;
    }

    public Object getData() {
        return data;
    }

    public UpdateActivity getUpdtActivity(){ return this.updtActivity; }

    public Activity getActivity() {return this.activity; }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setUpdtActivity(UpdateActivity updtActivity) { this.updtActivity = updtActivity; }

    public void setActivity(Activity activity) {this.activity = activity; }
}

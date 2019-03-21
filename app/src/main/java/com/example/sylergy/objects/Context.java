package com.example.sylergy.objects;

import android.app.Activity;

import com.example.sylergy.activities.UpdateActivity;

public class Context {
    private String event;
    private Object data;
    private UpdateActivity activity;

    public Context(String event, Object data, UpdateActivity activity) {
        this.data = data;
        this.event = event;
        this.activity = activity;
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

    public UpdateActivity getActivity() {return this.activity; }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setActivity(UpdateActivity activity) {this.activity = activity; }
}

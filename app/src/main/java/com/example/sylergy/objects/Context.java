package com.example.sylergy.objects;

import android.app.Activity;

public class Context {
    private String event;
    private Object data;
    private Activity activity;

    public Context(String event, Object data, Activity activity) {
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

    public Activity getActivity() {return this.activity; }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setActivity(Activity activity) {this.activity = activity; }
}

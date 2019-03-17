package com.example.sylergy.objects;

public class Context {
    private String event;
    private Object data;

    public Context(String event, Object data) {
        this.data = data;
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public Object getData() {
        return data;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

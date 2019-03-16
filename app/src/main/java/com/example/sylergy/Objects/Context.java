package com.example.sylergy.Objects;

public class Context {
    private int event;
    private Object data;

    public Context(int event, Object data) {
        this.data = data;
        this.event = event;
    }

    public int getEvent() {
        return event;
    }

    public Object getData() {
        return data;
    }

    public void setEvent(int event) {
        this.event = event;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

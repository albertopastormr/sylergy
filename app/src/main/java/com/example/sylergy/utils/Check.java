package com.example.sylergy.utils;


public class Check {
    private boolean correct;
    private String log;
    public Check(boolean correct, String log){
        this.correct = correct;
        this.log = log;
    }
    public boolean getCorrect(){
        return correct;
    }
    public String getLog(){
        return log;
    }
}

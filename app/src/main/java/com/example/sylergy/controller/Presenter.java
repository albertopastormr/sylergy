package com.example.sylergy.controller;

import com.example.sylergy.command.Command;
import com.example.sylergy.objects.Context;

public class Presenter {
    private static Presenter instance;
    public static Presenter getInstance(){
        if(instance==null)
            instance=new Presenter();
        return instance;
    }
    public void action(Context context){
        Command command = CommandDispatcher.getInstance().dispatcher(context.getEvent());
        Context result = null;
        if(command!=null){
            result = command.execute(context.getData());
            ActivityDispatcher.getInstance().dispatcher(result);
        }
    }


}

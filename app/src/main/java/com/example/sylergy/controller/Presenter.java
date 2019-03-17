package com.example.sylergy.controller;

import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.command.Command;
import com.example.sylergy.objects.Context;

public class Presenter {
    private static Presenter instance;
    public static Presenter getInstance(){
        if(instance==null)
            instance=new Presenter();
        return instance;
    }
    public void action(Context context, UpdateActivity activitySource){
        Command command = CommandDispatcher.getInstance().dispatcher(context.getEvent());
        if(command!=null){
            command.execute(context.getData(), activitySource);
        }
    }


}

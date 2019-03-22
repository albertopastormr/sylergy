package com.example.sylergy.presenter;

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
        Command command = CommandDispatcher.getInstance().dispatchCommand(context);
        if(command!=null){
            try {
                command.execute(context);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //public void dispatchActivity(Context context){
    //    ActivityDispatcher.getInstance().dispatchActivity(context,context.getUpdtActivity());
    //}
}

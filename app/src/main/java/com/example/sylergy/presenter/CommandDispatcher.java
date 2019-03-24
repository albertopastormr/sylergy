package com.example.sylergy.presenter;

import com.example.sylergy.activities.MainActivity;
import com.example.sylergy.command.Command;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;

public class CommandDispatcher {
    private static CommandDispatcher instance;

    public static CommandDispatcher getInstance(){
        if(instance==null)
            instance=new CommandDispatcher();
        return instance;
    }

    //TODO repasar la forma en la que se obtiene el id
    public Command dispatchCommand(Context context){
        Command command=null;
        try {
            switch (context.getEvent()){
                case Events.SEARCH_PRODUCT :
                    int id = MainActivity.context
                            .getResources()
                            .getIdentifier(context.getEvent(), "string",
                                    MainActivity.context.getPackageName());

                    String element = MainActivity.context.getString(id);
                    command = (Command) Class.forName(element.trim())
                            .getConstructor()
                            .newInstance();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when dispatch the command");
        }

        return command;
    }
}

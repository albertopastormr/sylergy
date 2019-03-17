package com.example.sylergy.command;

import com.example.sylergy.activities.UpdateActivity;

public interface Command {
     void execute(Object data, UpdateActivity activitySource);
}

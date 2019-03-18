package com.example.sylergy.command;

import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.objects.Context;

public interface Command {
     void execute(Context context);
}

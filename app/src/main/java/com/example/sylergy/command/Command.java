package com.example.sylergy.command;

import com.example.sylergy.objects.Context;

public interface Command {
    public Context execute(Object data);
}

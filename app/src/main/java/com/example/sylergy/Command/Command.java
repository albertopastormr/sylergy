package com.example.sylergy.Command;

import com.example.sylergy.Objects.Context;

public interface Command {
    public Context execute(Object data);
}

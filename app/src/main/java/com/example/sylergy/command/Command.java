package com.example.sylergy.command;

import android.app.Activity;

import com.example.sylergy.objects.Context;

public interface Command {
     void execute(Context context, Activity activity) throws InterruptedException;
}

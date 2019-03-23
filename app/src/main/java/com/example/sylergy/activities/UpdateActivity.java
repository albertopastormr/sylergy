package com.example.sylergy.activities;

import com.example.sylergy.logs.LogException;
import com.example.sylergy.objects.Context;

public interface UpdateActivity {
    public void updateWithCommandResult(Context context)throws LogException;
}

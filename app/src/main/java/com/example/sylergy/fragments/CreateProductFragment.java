package com.example.sylergy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sylergy.R;
import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.logs.LogException;

import com.example.sylergy.objects.Context;

public class CreateProductFragment extends Fragment implements UpdateActivity {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_product, container, false);

        return view;
    }

    @Override
    public void updateWithCommandResult(Context context) throws LogException {

    }
}

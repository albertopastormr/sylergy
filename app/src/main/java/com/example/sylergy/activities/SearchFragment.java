package com.example.sylergy.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.sylergy.R;

public class SearchFragment extends Fragment {
    private SearchView searchView;
    private String[] items = new String[] { "Search by name" };
    private int select=0;
    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_name, container, false);
        searchView = view.findViewById(R.id.searchView);

        dialog = new AlertDialog.Builder(getActivity()).setTitle("Search by ...")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select=which;
                        setSearchBy();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();

        return view;
    }

    private void setSearchBy(){
        searchView.setQueryHint(items[select]);
    }

}

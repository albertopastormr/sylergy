package com.example.sylergy.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.sylergy.R;
import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.logs.LogException;
import com.example.sylergy.logs.Logs;
import com.example.sylergy.logs.LogsView;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;
import com.example.sylergy.presenter.Presenter;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.HashMap;

public class    SearchFragment extends Fragment implements UpdateActivity {
=======
import java.util.List;

public class SearchFragment extends Fragment implements UpdateActivity {
>>>>>>> deccc27657c038c7410aae384e17788728157460
    private SearchView searchView;
    private String[] items = new String[] { "Search by name" };
    private ProgressDialog draw;
    private int select=0;
    private AlertDialog dialog;

    ArrayList<Product> productList;

    private ProductsListAdapter adapter;
    private ListView listViewProduct;
    LayoutInflater inflater;

    View view;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.inflater = inflater;

        view = inflater.inflate(R.layout.fragment_search_name, container, false);
        searchView = view.findViewById(R.id.searchView);
        draw = new ProgressDialog(getActivity());
        draw.setCancelable(false);
        draw.setMessage("Searching...");

        listViewProduct = (ListView) view.findViewById(R.id.products_lv_list);
        productList = new ArrayList<>();

        dialog = new AlertDialog.Builder(getActivity()).setTitle("Search by ...")
                .setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select=which;
                        setSearchBy();
                        dialog.dismiss();
                    }
                }).create();
        dialog.setCancelable(false);
        dialog.show();

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        if (items[select].equals("")) {
                            LogsView advise = new LogsView(Logs.NO_SEARCH_NAME);
                            advise.showInfo(getActivity());
                            return false;
                        } else {
                            draw.show();
                            Presenter.getInstance()
                                    .action(new Context(Events.SEARCH_PRODUCT_NAME,
                                            query,
                                            SearchFragment.this));

                            return true;
                        }

                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                }
        );

        return view;
    }

    private void setSearchBy(){
        searchView.setQueryHint(items[select]);
    }

    @Override
    public void updateWithCommandResult(Context context) throws LogException {
        draw.hide();
        List<Product> result = (List<Product>) context.getData();


        adapter = new ProductsListAdapter(getActivity().getApplicationContext(), productList);
        listViewProduct.setAdapter(adapter);
    }
}

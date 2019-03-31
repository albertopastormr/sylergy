package com.example.sylergy.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.sylergy.R;
import com.example.sylergy.activities.MainActivity;
import com.example.sylergy.activities.ProductActivity;
import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.logs.LogException;
import com.example.sylergy.logs.Logs;
import com.example.sylergy.logs.LogsView;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;
import com.example.sylergy.presenter.Presenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchFragment extends Fragment implements UpdateActivity {
    public static final String OBJ = "OBJ"; //Used to the define the "key" we will use to send the found object to the other activity

    private ProgressDialog draw;

    ArrayList<Product> productList;

    private ProductsListAdapter adapter;
    private ListView listViewProduct;
    LayoutInflater inflater;

    View view;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        this.inflater = inflater;

        view = inflater.inflate(R.layout.fragment_search_name, container, false);
        SearchView searchView = view.findViewById(R.id.searchView);
        draw = new ProgressDialog(getActivity());
        draw.setCancelable(false);
        draw.setMessage("Searching...");

        listViewProduct = (ListView) view.findViewById(R.id.products_lv_list);
        productList = new ArrayList<>();

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        if (query.equals("")) {
                            LogsView advise = new LogsView(Logs.NO_SEARCH_NAME);
                            advise.showInfo(getActivity());
                            return false;
                        } else {
                            draw.show();
                            Presenter.getInstance()
                                    .action(new Context(Events.SEARCH_PRODUCT_NAME,
                                            query,
                                            SearchFragment.this));

                            // mIdlingResource!=null is in test, and stop it
                            if(MainActivity.mIdlingResource!=null)
                                MainActivity.mIdlingResource.setIdleState(false);
                            return true;
                        }
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                }
        );

        listViewProduct.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ProductActivity.class);
                intent.putExtra(OBJ, productList.get(position));
                startActivity(intent);
            }

        });

        return view;
    }

    @Override
    public void updateWithCommandResult(Context context) throws LogException {
        draw.hide();
        productList.clear();

        List<Product> result = (List<Product>) context.getData();

        if (result != null && result.size() > 0 && getActivity() != null){
            productList.addAll(result);
            if(productList.size() > 1) { //Its only necessary when there is more than one product found
                Collections.sort(productList, new Comparator<Product>() { //Alphabetically order
                    @Override
                    public int compare(Product o1, Product o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });
            }

            adapter = new ProductsListAdapter(getActivity().getApplicationContext(), productList);
            listViewProduct.setAdapter(adapter);
        }

        // mIdlingResource!=null is in test, and continue it
        if(MainActivity.mIdlingResource!=null)
            MainActivity.mIdlingResource.setIdleState(true);

        if(result == null) { //That means that there is no product with the given name
            LogsView advise = new LogsView(Logs.PRODUCT_NOT_FOUND);
            advise.showInfo(getActivity());
        }
    }
}

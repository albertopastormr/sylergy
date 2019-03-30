package com.example.sylergy.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sylergy.R;
import com.example.sylergy.objects.Product;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductsListActivity extends Fragment {

    private ProductsListAdapter adapter;
    private ListView listViewProduct;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_name, container, false);

        listViewProduct = (ListView) view.findViewById(R.id.products_lv_list);

        ArrayList<Product> productList = new ArrayList<>();

        Product product = new Product("Test_Product",
                "https://fotos01.lne.es/2018/09/23/690x278/el-alimento-con-el-que-adelgazaras-y-quem" +
                        "aras-grasas-si-los-comes-todos-los-dias.jpg",
                null, "Patatas con bacon" , new HashMap<String, Object>(){{put("1","Hola");}});

        productList.add(product);

        product = new Product("Test_Product",
                "https://cdn-3.expansion.mx/dims4/default/f71603e/2147483647/strip/true/crop" +
                        "/800x450+0+0/resize/800x450!/quality/90/?url=https%3A%2F%2Fcdn-3.expansion.mx%" +
                        "2Fd6%2F1c%2F5bc4a59b41c89bb2167cdd8cfa1d%2Famlo-fifi-rosa.gif",
                null, "Alcachofas" , new HashMap<String, Object>(){{put("1","Hola");}});

        productList.add(product);

        adapter = new ProductsListAdapter(inflater.getContext(), productList);
        listViewProduct.setAdapter(adapter);

        return view;
    }

}

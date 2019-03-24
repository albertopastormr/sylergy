package com.example.sylergy.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.sylergy.R;
import com.example.sylergy.objects.Product;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsListActivity extends AppCompatActivity implements OnItemClickListener{

    private ProductsListAdapter adapter;
    @BindView(R.id.textViewSearchResult)
    TextView textViewSearchResult;
    @BindView(R.id.productslist)
    RecyclerView productslist;

    List<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productslist);
        ButterKnife.bind(this);

        textViewSearchResult = findViewById(R.id.textViewSearchResult);
        configRecycleView();
        configAdapter();

        generateList(products);

    }

    private void generateList(List<Product> products) {
        for(Product p : products){
            adapter.add(p);
        }
    }


    private void configAdapter() {
        adapter = new ProductsListAdapter(new ArrayList<Product>(), this,this);
    }

    private void configRecycleView() {
        productslist.setLayoutManager(new LinearLayoutManager(this));
        productslist.setAdapter(adapter);
    }


    @Override
    public void onItemClick(Product product) {
        Intent intent = new Intent(this,ProductActivity.class);
        intent.putExtra("OBJ",product);
    }

    @Override
    public void onLongItemClick(Product product) {
        Intent intent = new Intent(this,ProductActivity.class);
        intent.putExtra("OBJ",product);
    }
}

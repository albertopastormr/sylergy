package com.example.sylergy.integration.product.dao.imp;

import android.support.annotation.NonNull;
import com.example.sylergy.integration.product.dao.DAOProduct;
import com.example.sylergy.integration.firebase.FirebaseUtil;

import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;
import com.example.sylergy.presenter.Presenter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOProductImp implements DAOProduct { ;

    @Override
    public Product readById(final Context context) {
        final List<Product> listProducts = new ArrayList<>();
        listProducts.add(null);

        DatabaseReference database = FirebaseUtil.getSpecifiedReference("Products");
        Query query = FirebaseUtil.getQueryByChild(database, "barcode")
                .equalTo(context.getData().toString());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listProducts.clear();
                Context newContext;
                if(dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        listProducts.add(d.getValue(Product.class));
                    }
                    newContext = new Context(Events.SEARCH_PRODUCT_OK, listProducts.get(0));
                    newContext.setActivity(context.getActivity());

                }
                else{
                    newContext = new Context(Events.SEARCH_PRODUCT_ERROR, null);
                    newContext.setActivity(context.getActivity());
                }

                Presenter.getInstance().dispatchActivity(newContext);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return listProducts.get(0);
    }

    @Override
    public Product readByName(final Context context) {
        final List<Product> productList = new ArrayList<>(); //We will store the products in this list.
        productList.add(null);

        String nameToFindSimilar = (String)context.getData(); //This is the name of the product we want to find in the database

        DatabaseReference databaseReference = FirebaseUtil.getSpecifiedReference("Products"); //This is a reference to our Firebase Database.
        Query findByNameQuery = databaseReference.orderByChild("name")
                .startAt(nameToFindSimilar)
                .endAt(nameToFindSimilar + "\uf8ff"); //We create the specific query

        findByNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                Context newContext;
                if(dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) { //We recolect our data
                        productList.add(d.getValue(Product.class));
                    }

                    newContext = new Context(Events.SEARCH_PRODUCT_NAME_OK, productList,context.getActivity());
                    newContext.setActivity(context.getActivity());
                }
                else {
                    newContext = new Context(Events.SEARCH_PRODUCT_NAME_ERROR, null);
                    newContext.setActivity(context.getActivity());
                }
                Presenter.getInstance().dispatchActivity(newContext);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        return productList.get(0);
    }

}

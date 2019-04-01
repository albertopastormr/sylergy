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
        //We will store the products in this list.
        final List<Product> productList = new ArrayList<>();
        productList.add(null);

        //This is the name of the product we want to find in the database
        final String nameToFindSimilar = (String)context.getData();

        final String nameToFindSimilarLower = nameToFindSimilar.toLowerCase();
        DatabaseReference databaseReference = FirebaseUtil.getSpecifiedReference("Products");
        //This is a reference to our Firebase Database.
        Query findByNameQuery = databaseReference.orderByChild("name")
                .startAt("*" + nameToFindSimilar) // With that the query is faster
                .endAt(nameToFindSimilar + "\uf8ff"); //We create the specific query*/

        findByNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                Context newContext = null;
                if(dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) { //We recolect our data

                        String resultLower = d.getValue(Product.class).getName().toLowerCase();
                        if (resultLower.contains(nameToFindSimilarLower))
                            productList.add(d.getValue(Product.class));

                    }

                    if(context.getEvent().equals(Events.SEARCH_PRODUCT_NAME)) {
                        newContext = new Context(Events.SEARCH_PRODUCT_NAME_OK, productList);
                        newContext.setActivity(context.getActivity());
                    }
                    else if(context.getEvent().equals(Events.SEARCH_PRODUCT_BARCODE)){
                        newContext = new Context(Events.SEARCH_PRODUCT_BARCODE_OK, productList);
                        newContext.setActivity(context.getActivity());

                    }
                }
                else {
                    if(context.getEvent().equals(Events.SEARCH_PRODUCT_NAME)) {
                        newContext = new Context(Events.SEARCH_PRODUCT_NAME_ERROR, null);
                        newContext.setActivity(context.getActivity());
                    }
                    else if(context.getEvent().equals(Events.SEARCH_PRODUCT_BARCODE)){
                        newContext = new Context(Events.SEARCH_PRODUCT_BARCODE_ERROR, productList);
                        newContext.setActivity(context.getActivity());
                    }
                }
                Presenter.getInstance().dispatchActivity(newContext);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        return productList.get(0);
    }
    /*@Override
    public Product readByName(final Context context) {
        //We will store the products in this list.
        final List<Product> productList = new ArrayList<>();
        productList.add(null);

        //This is the name of the product we want to find in the database
        String nameToFindSimilar = (String)context.getData();


        DatabaseReference databaseReference = FirebaseUtil.getSpecifiedReference("Products");
        //This is a reference to our Firebase Database.
        Query findByNameQuery = databaseReference.orderByChild("name")
                .startAt(nameToFindSimilar)
                .endAt(nameToFindSimilar + "\uf8ff"); //We create the specific query

        findByNameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                Context newContext = null;
                if(dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) { //We recolect our data
                        productList.add(d.getValue(Product.class));
                    }

                    if(context.getEvent().equals(Events.SEARCH_PRODUCT_NAME)) {
                        newContext = new Context(Events.SEARCH_PRODUCT_NAME_OK, productList);
                        newContext.setActivity(context.getActivity());
                    }
                    else if(context.getEvent().equals(Events.SEARCH_PRODUCT_BARCODE)){
                        newContext = new Context(Events.SEARCH_PRODUCT_BARCODE_OK, productList);
                        newContext.setActivity(context.getActivity());

                    }
                }
                else {
                    if(context.getEvent().equals(Events.SEARCH_PRODUCT_NAME)) {
                    newContext = new Context(Events.SEARCH_PRODUCT_NAME_ERROR, null);
                    newContext.setActivity(context.getActivity());
                    }
                    else if(context.getEvent().equals(Events.SEARCH_PRODUCT_BARCODE)){
                        newContext = new Context(Events.SEARCH_PRODUCT_BARCODE_ERROR, productList);
                        newContext.setActivity(context.getActivity());
                    }
                }
                Presenter.getInstance().dispatchActivity(newContext);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });

        return productList.get(0);
    }*/

}

package com.example.sylergy.command;
import android.support.annotation.NonNull;

import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.controller.ActivityDispatcher;
import com.example.sylergy.integration.product.factory.DAOProductFactory;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CommandSearchProduct implements Command {
    private List<Product> listProducts = new ArrayList<>();


    @Override
    public void execute(Object data, final UpdateActivity activitySource) {
        Query productQuery = DAOProductFactory.getInstance().generateDAOProduct().readById((Long)data);
        productQuery.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listProducts.clear();
                        if(dataSnapshot.exists()) {
                            for (DataSnapshot d : dataSnapshot.getChildren()) {
                                listProducts.add(d.getValue(Product.class));
                            }
                            ActivityDispatcher.getInstance().dispatcher(new Context(Events.SEARCH_PRODUCT_OK, listProducts.get(0)), activitySource);
                        }
                        else{
                            ActivityDispatcher.getInstance().dispatcher(new Context(Events.SEARCH_PRODUCT_ERROR, null), activitySource);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }
}

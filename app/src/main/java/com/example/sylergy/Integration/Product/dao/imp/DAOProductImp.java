package com.example.sylergy.Integration.Product.dao.imp;

import android.support.annotation.NonNull;

import com.example.sylergy.Integration.Product.dao.DAOProduct;
import com.example.sylergy.Integration.firebase.FirebaseUtil;
import com.example.sylergy.Objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DAOProductImp implements DAOProduct {

    private List<Product> listProducts = new ArrayList<Product>();
    private Product product = null;

    @Override
    public Product readById(int barcode) {
        final CountDownLatch latch = new CountDownLatch(1);

        DatabaseReference database = FirebaseUtil.getSpecifiedReference("Products");
        Query q = FirebaseUtil.getQueryByChild(database, "barcode").equalTo(barcode);

        q.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listProducts.clear();
                        if(dataSnapshot.exists()) {
                            for (DataSnapshot d : dataSnapshot.getChildren()) {
                                listProducts.add(d.getValue(Product.class));
                            }
                            product = listProducts.get(0);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return product;
    }

}

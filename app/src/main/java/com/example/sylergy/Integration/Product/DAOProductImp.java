package com.example.sylergy.Integration.Product;

import android.support.annotation.NonNull;

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
    private List<Product> list= new ArrayList<Product>();
    private Product product = null;

    @Override
    public Product read(int id) {
        final CountDownLatch latch = new CountDownLatch(1);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Productos");
        Query q = database.orderByChild("barcode").equalTo(id);
        q.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list.clear();
                        if(dataSnapshot.exists()) {
                            for (DataSnapshot d : dataSnapshot.getChildren()) {
                                list.add(d.getValue(Product.class));
                            }
                            product = list.get(0);
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

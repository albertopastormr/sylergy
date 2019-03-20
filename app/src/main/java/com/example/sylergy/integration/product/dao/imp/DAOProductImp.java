package com.example.sylergy.integration.product.dao.imp;

import android.support.annotation.NonNull;

import com.example.sylergy.command.Command;
import com.example.sylergy.integration.product.dao.DAOProduct;
import com.example.sylergy.integration.firebase.FirebaseUtil;
import com.example.sylergy.objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOProductImp implements DAOProduct {

    private List<Product> listProducts = new ArrayList<Product>();
    private Product product = null;

    @Override
    public void readById(long barcode, final Command command) {

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

                        }else{
                            product = null;
                        }
                        command.executeResult(product);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                }
        );
    }

}

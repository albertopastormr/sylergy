package com.example.sylergy.product;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.sylergy.objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class IntegrationTest {

    private Product product;
    private DatabaseReference ref;
    private ValueEventListener eventListener;
    private List<Product> listProduct;

    @Before
    public void initialize() {
        listProduct = new ArrayList<>();
        product = new Product("Test_Product", 345345, Arrays.asList("1, 2, 3"), Arrays.asList("4, 5, 6"));
        ref = FirebaseDatabase.getInstance().getReference("Productos");
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listProduct.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        listProduct.add(d.getValue(Product.class));
                    }
                }
                Log.i("PRODUCT", listProduct.get(0).toString());
                assertEquals(product.getBarcode(), listProduct.get(0).getBarcode());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };
    }

    @Test
    public void correctReadTest() {
        /* We previously create the product */
        boolean resultCreate = product.create(ref);
        assertTrue(resultCreate);
        product.read(ref, eventListener);
    }
}

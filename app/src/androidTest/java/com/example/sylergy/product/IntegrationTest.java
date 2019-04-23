package com.example.sylergy.product;

import android.support.annotation.NonNull;
import android.support.v4.widget.TextViewCompat;
import android.util.Log;

import com.example.sylergy.integration.firebase.FirebaseUtil;
import com.example.sylergy.objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class IntegrationTest {

    private Product product;
    private DatabaseReference ref;
    private ValueEventListener eventListener;
    private List<Product> listProduct;

    @Before
    public void initialize() {
        listProduct = new ArrayList<>();
        product = new Product("Test_Product",
                "https://fotos01.lne.es/2018/09/23/690x278" +
                        "/el-alimento-con-el-que-adelgazaras-y-" +
                        "quemaras-grasas-si-los-comes-todos-los-dias.jpg",
                new ArrayList<HashMap<String, Object>> () {{add(new HashMap<String, Object>() {{put("text", "Ingredient1");}});}},
                "N" , new HashMap<String, Object>(){{put("1","Hola");}});

        ref = FirebaseDatabase.getInstance().getReference("Products");

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listProduct.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        listProduct.add(d.getValue(Product.class));
                    }
                    Log.i("PRODUCT", listProduct.get(0).toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };
    }

    //Automatic
    @Test
    public void connectionNotNullFirebase(){
        ref = FirebaseDatabase.getInstance().getReference("Products");
        assertNotNull(ref);
    }

    //Automatic
    @Test
    public void queryNotNullFirebase(){
        Query query = ref.orderByChild("barcode");
        assertNotNull(query);
    }

    //Automatic
    @Test
    public void connectionNotNullFirebaseUtil(){
        ref = FirebaseUtil.getSpecifiedReference("Product");
        assertNotNull(ref);
    }

    //Automatic
    @Test
    public void queryNotNullFirebaseUtil(){
        Query query = FirebaseUtil.getQueryByChild(ref, "barcode").equalTo(12);
        assertNotNull(query);
    }

    // Manual
    @Test
    public void queryWithProductFirebaseUtil(){
        Query query = FirebaseUtil.getQueryByChild(ref, "barcode").equalTo("8480000592477");
        query.addListenerForSingleValueEvent(eventListener);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Manual
    @Test
    public void queryWithNotProductFirebaseUtil(){
        Query query = FirebaseUtil.getQueryByChild(ref, "barcode").equalTo("66");
        query.addListenerForSingleValueEvent(eventListener);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

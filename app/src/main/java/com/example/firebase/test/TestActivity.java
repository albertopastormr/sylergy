package com.example.firebase.test;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.example.Objects.Product;
import com.example.example.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    List<Product> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_simple_spinner_dropdown_item);

        list = new ArrayList<>();

        //testCreateProduct();

        //testDropProduct(20182019);

        //testShowProduct(20182019);

        //testModifyProduct(20182019, new Product("Moyonesa Vegana", 20182019, Arrays.asList("Huevo", "Agua", "Maiz", "Azucar"), Arrays.asList("Vegetariano", "Celiaco", "Vegano")));

    }

    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            list.clear();
            if(dataSnapshot.exists()) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    list.add(d.getValue(Product.class));
                }
            }
            else{
                Toast.makeText(getApplicationContext(),
                        "The product doesn't exist", Toast.LENGTH_SHORT).show();
            }
            Log.i("PRODUCT", list.get(0).toString());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }

    };

    private void testCreateProduct(){
        Product p = new Product("Moyonesa", 20182019, Arrays.asList("Huevo", "Agua", "Maiz", "Azucar"), Arrays.asList("Vegetariano", "Celiaco"));
        p.create();
    }

    private void testDropProduct(Integer key){
        Product p = new Product(null, key, null, null);
        p.drop();
    }

    private void testShowProduct(Integer key){
        Product p = new Product(null, key, null, null);
        p.read(eventListener);
    }

    private void testModifyProduct(Integer key, Product product){
        Product p = new Product(null, key, null, null);
        p.modify(product);
    }



}

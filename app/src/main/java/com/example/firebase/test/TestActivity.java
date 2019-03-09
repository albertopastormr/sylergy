package com.example.firebase.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.example.Objects.Product;
import com.example.example.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Arrays;

public class TestActivity extends AppCompatActivity {

    DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_simple_spinner_dropdown_item);

        //testCreateProduct();

        //testDropProduct(20182019);

        // NO FUNCIONA
        //testShowProduct(20182019);
    }

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
        Product prt = p.read();
        p.toString();
    }



}

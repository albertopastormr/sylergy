package com.example.sylergy.objects;

import android.support.annotation.NonNull;

import com.example.sylergy.integration.firebase.FirebaseUtil;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Product implements Serializable {

    private String barcode;
    private String name;
    private String image;
    private ArrayList<HashMap<String, Object>> ingredients;
    private HashMap<String, Object> nutrimets;

    private List<Product> list = new ArrayList<Product>();

    public Product() {}

    public Product(String bcode, String imageUrl, ArrayList<HashMap<String, Object>> ingreds, String n, HashMap<String, Object> nutrs) {
        barcode = bcode;
        name = n;
        ingredients = ingreds;
        nutrimets = nutrs;
        image = imageUrl;
    }

    public Product(String bcode) {
        barcode = bcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<String> getIngredients() {
        List<String> ret = new ArrayList<>();
        for(HashMap<String, Object> elem: ingredients) {
            ret.add((String)elem.get("text"));
        }

        return ret;
    }

    public void setIngredients(ArrayList<HashMap<String, Object>> ingredients) {
        this.ingredients = ingredients;
    }

    public HashMap<String, Object> getNutrimets() {
        return nutrimets;
    }

    public void setNutrimets(HashMap<String, Object> nutriments) {
        this.nutrimets = nutriments;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

}
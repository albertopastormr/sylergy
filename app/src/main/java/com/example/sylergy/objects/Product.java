package com.example.sylergy.objects;

import android.support.annotation.NonNull;
import android.widget.Adapter;

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
    private HashMap<String, Object> nutrimets; //We know that "nutrimets" is incorrect, but the field in our db is called like that. If we change this to "nutrients", basically it explodes.
                                                // It will be fixed soon, until so, we have to keep naming this attribute the way it is.

    private List<Product> list = new ArrayList<Product>();

    public Product() {}

    public Product(String barcode, String imageUrl, ArrayList<HashMap<String, Object>> ingredients,
                   String name, HashMap<String, Object> nutrients) {
        this.barcode = barcode;
        this.name = name;
        this.ingredients = ingredients;
        this.nutrimets = nutrients;
        this.image = imageUrl;
    }

    public Product(String barcode) {
        this.barcode = barcode;
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
            if(elem != null) ret.add((String)elem.get("text"));
        }

        return ret;
    }

    public void setIngredients(ArrayList<HashMap<String, Object>> ingredients) {
        this.ingredients = ingredients;
    }

    public HashMap<String, Object> getNutrimets() {
        return nutrimets;
    }

    public void setNutrients(HashMap<String, Object> nutrients) {
        this.nutrimets = nutrients;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    @Override
    public String toString() {
        return "Name: " + this.name
                + "\n -Barcode: " + this.barcode
                + "\n -Ingredients: " + this.ingredients.toString()
                + "\n -Nutrients: " + this.nutrimets.toString()
                + "\n -URLimage: " + this.image + "\n";
    }
}
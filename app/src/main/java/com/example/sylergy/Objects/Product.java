package com.example.sylergy.Objects;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    private Integer barcode;
    private String name;
    private List<String> ingredients;
    private List<String> adaptedFor;

    private List<Product> list = null;

    public Product() {}

    public Product(String n, Integer bcode, List<String> ingreds, List<String> adFor) {
        barcode = bcode;
        name = n;
        ingredients = ingreds;
        adaptedFor = adFor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBarcode() {
        return barcode;
    }

    public void setBarcode(Integer barcode) {
        this.barcode = barcode;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getAdaptedFor() {
        return adaptedFor;
    }

    public void setAdaptedFor(List<String> adaptedFor) {
        this.adaptedFor = adaptedFor;
    }

    public boolean create(DatabaseReference ref){
        try {
            ref.child(this.barcode.toString()).setValue(this);
            Thread.sleep(1000);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean modify(DatabaseReference ref, Product newProduct){
        try{
            ref.child(this.barcode.toString()).setValue(newProduct);
            Thread.sleep(1000);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    public boolean drop(DatabaseReference ref){
        try{
            ref.child(this.barcode.toString()).removeValue();
            Thread.sleep(1000);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public boolean read(DatabaseReference ref, ValueEventListener eventListener) {
        try {
            Query q = ref.orderByChild("barcode").equalTo(this.barcode);
            q.addListenerForSingleValueEvent(eventListener);
            Thread.sleep(1000);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "-" + this.name + " - Barcode:" + this.getBarcode()+", "+getIngredients()+ ", "+getAdaptedFor().toString()+"\n\n";
    }
}
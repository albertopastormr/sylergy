package com.example.example.Objects;

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

    public void create(){
        Product p = new Product(this.name, this.barcode, this.ingredients, this.adaptedFor);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Productos");
        database.child(String.valueOf(p.getBarcode())).setValue(p);
    }

    public void modify(Product newProduct){
        Product p = new Product(this.name, this.barcode, this.ingredients, this.adaptedFor);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Productos");
        database.child(String.valueOf(p.getBarcode())).setValue(newProduct);
    }

    public void drop(){
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Productos");
        database.child(String.valueOf(this.getBarcode())).removeValue();
    }

    public void read(ValueEventListener eventListener) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Productos");
        Query q = database.orderByChild("barcode").equalTo(this.barcode);
        q.addListenerForSingleValueEvent(eventListener);
    }

    @Override
    public String toString() {
        return "-" + this.name + " - Barcode:" + this.getBarcode()+", "+getIngredients()+ ", "+getAdaptedFor().toString()+"\n\n";
    }
}

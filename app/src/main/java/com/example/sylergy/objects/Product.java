package com.example.sylergy.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Product implements Serializable {

    private String barcode;
    private String name;
    private String image;
    private ArrayList<HashMap<String, Object>> ingredients;
    private HashMap<String, Object> nutrients;

    private List<Product> list = new ArrayList<Product>();

    public Product() {}

    public Product(String barcode, String imageUrl, ArrayList<HashMap<String, Object>> ingredients,
                   String name, HashMap<String, Object> nutrients) {
        this.barcode = barcode;
        this.name = name;
        this.ingredients = ingredients;
        this.nutrients = nutrients;
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

    public HashMap<String, Object> getNutrients() {
        return nutrients;
    }

    public void setNutrients(HashMap<String, Object> nutrients) {
        this.nutrients = nutrients;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }

    @Override
    public String toString() {
        return "Name: " + this.name
                + "\n -Barcode: " + this.barcode
                + "\n -Ingredients: " + this.ingredients.toString()
                + "\n -Nutrients: " + this.nutrients.toString()
                + "\n -URLimage: " + this.image + "\n";
    }
}
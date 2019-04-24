package com.example.sylergy.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(ingredients == null || ingredients.isEmpty()) return ret;
        for(HashMap<String, Object> elem: ingredients) {
            if(elem != null && !(elem.get("text")).equals("PUEDE CONTENER TRAZA DE SOJA")
                    && !(name.contains((String) elem.get("text")))) ret.add((String)elem.get("text"));
        }

        return ret;
    }

    public void setIngredients(ArrayList<HashMap<String, Object>> ingredients) {
        this.ingredients = ingredients;
    }

    /* Alternative Version */
    public HashMap<String, Object> getNutrients() {
        HashMap<String, Object> toReturn = new HashMap<>();
        if(nutrients == null || nutrients.isEmpty()) return toReturn;

        for(String nutrient_name: nutrients.keySet()) {
            if(nutrient_name.contains("_100g") &&
                    !nutrient_name.equals("nova-group_100g") &&
                    !nutrient_name.equals("nutrition-score-uk_100g") &&
                    !nutrient_name.equals("nutrition-score-fr_100g") ) {
                toReturn.put(nutrient_name.substring(0, nutrient_name.length() - 5), //_100g is 5 characters long
                        nutrients.get(nutrient_name));
            }
        }

        return toReturn;
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
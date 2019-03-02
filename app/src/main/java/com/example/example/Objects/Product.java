package com.example.example.Objects;

import java.io.Serializable;
import java.util.List;

public class Product implements Serializable {

    private Integer barcode;
    private String name;
    private List<String> ingredients;
    private List<String> adaptedFor;


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
}

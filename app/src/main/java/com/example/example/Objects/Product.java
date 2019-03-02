package com.example.example.Objects;

import java.io.Serializable;

public class Product implements Serializable {

    private Integer barcode;
    private String name;
    private String info;


    public Product(String n, Integer bcode, String information) {
        barcode = bcode;
        name = n;
        info = information;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return this.name + " " + this.barcode + " " + this.info;
    }
}

package com.example.sylergy.utils;

import com.example.sylergy.objects.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayListUtils {
    public static ArrayList<Product> sortByName(ArrayList<Product> productList) {
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
            }
        });

        return productList;
    }
}

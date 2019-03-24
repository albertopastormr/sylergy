package com.example.sylergy.activities;

import android.util.Pair;

import com.example.sylergy.objects.Product;

public interface OnItemClickListener {
    void onItemClick(Product product);
    void onLongItemClick(Product product);
}

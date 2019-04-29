package com.example.sylergy.integration.product.dao;

import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Product;
import com.google.firebase.database.Query;

public interface DAOProduct {
    Product readById(final Context context);
    Product readByName(final Context context);
    void createProduct(final Context context);
}

package com.example.sylergy.integration.product.dao;

import com.example.sylergy.objects.Product;
import com.google.firebase.database.Query;

public interface DAOProduct {
    Product readById(Long barcode);
}

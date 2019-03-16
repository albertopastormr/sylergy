package com.example.sylergy.integration.product.dao;

import com.example.sylergy.objects.Product;

public interface DAOProduct {
    Product readById(int barcode);
}

package com.example.sylergy.Integration.Product.dao;

import com.example.sylergy.Objects.Product;

public interface DAOProduct {
    Product readById(int barcode);
}

package com.example.sylergy.integration.product.dao;

import com.google.firebase.database.Query;

public interface DAOProduct {
    Query readById(Long barcode);
}

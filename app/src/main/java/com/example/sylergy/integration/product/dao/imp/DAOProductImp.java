package com.example.sylergy.integration.product.dao.imp;

import com.example.sylergy.integration.product.dao.DAOProduct;
import com.example.sylergy.integration.firebase.FirebaseUtil;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class DAOProductImp implements DAOProduct { ;

    @Override
    public Query readById(Long barcode) {
        DatabaseReference database = FirebaseUtil.getSpecifiedReference("Products");
        return FirebaseUtil.getQueryByChild(database, "barcode").equalTo(barcode.toString());
    }

}

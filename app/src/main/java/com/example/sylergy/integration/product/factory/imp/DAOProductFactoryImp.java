package com.example.sylergy.integration.product.factory.imp;

import com.example.sylergy.integration.product.dao.DAOProduct;
import com.example.sylergy.integration.product.dao.imp.DAOProductImp;
import com.example.sylergy.integration.product.factory.DAOProductFactory;

public class DAOProductFactoryImp extends DAOProductFactory {


    @Override
    public DAOProduct generateDAOProduct() {
        return new DAOProductImp();
    }
}

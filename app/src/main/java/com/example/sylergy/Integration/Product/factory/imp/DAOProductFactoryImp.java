package com.example.sylergy.Integration.Product.factory.imp;

import com.example.sylergy.Integration.Product.dao.DAOProduct;
import com.example.sylergy.Integration.Product.dao.imp.DAOProductImp;
import com.example.sylergy.Integration.Product.factory.DAOProductFactory;

public class DAOProductFactoryImp extends DAOProductFactory {


    @Override
    public DAOProduct generateDAOProduct() {
        return new DAOProductImp();
    }
}

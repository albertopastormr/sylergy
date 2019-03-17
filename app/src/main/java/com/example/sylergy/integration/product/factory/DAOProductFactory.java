package com.example.sylergy.integration.product.factory;

import com.example.sylergy.integration.product.dao.DAOProduct;
import com.example.sylergy.integration.product.factory.imp.DAOProductFactoryImp;

public abstract class DAOProductFactory {

    private static DAOProductFactory instance;

    public synchronized static DAOProductFactory getInstance(){
        if(instance == null)
            instance = new DAOProductFactoryImp();
        return instance;
    }

    public abstract DAOProduct generateDAOProduct();
}

package com.example.sylergy.Integration.Product.factory;

import com.example.sylergy.Integration.Product.dao.DAOProduct;
import com.example.sylergy.Integration.Product.factory.imp.DAOProductFactoryImp;

public abstract class DAOProductFactory {

    private static DAOProductFactory instance;

    public synchronized static DAOProductFactory getInstance(){
        if(instance == null)
            instance = new DAOProductFactoryImp();
        return instance;
    }

    public abstract DAOProduct generateDAOProduct();
}

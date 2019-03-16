package com.example.sylergy.Integration;

import com.example.sylergy.Integration.Product.DAOProduct;
import com.example.sylergy.Integration.Product.DAOProductImp;

public class InterationFactory {
    private static InterationFactory instance;
    public synchronized static InterationFactory getInstance(){
        if(instance == null)
            instance = new InterationFactory();
        return instance;
    }

    public DAOProduct getDAOProduct(){
        return new DAOProductImp();
    }

}

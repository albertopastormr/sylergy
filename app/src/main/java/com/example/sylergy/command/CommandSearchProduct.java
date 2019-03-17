package com.example.sylergy.command;

import com.example.sylergy.integration.product.dao.DAOProduct;
import com.example.sylergy.integration.product.factory.DAOProductFactory;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;

public class CommandSearchProduct implements Command {

    @Override
    public Context execute(Object data) {
        Product product = DAOProductFactory.getInstance().generateDAOProduct().readById((int)data);

        if(product != null)
            return new Context(Events.SEARCH_PRODUCT_OK,product);
        else
            return new Context(Events.SEARCH_PRODUCT_ERROR, null);
    }
}

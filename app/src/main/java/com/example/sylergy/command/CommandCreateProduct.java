package com.example.sylergy.command;

import com.example.sylergy.integration.product.factory.DAOProductFactory;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Product;

public class CommandCreateProduct implements Command {

    @Override
    public void execute(Context context) {
        DAOProductFactory.getInstance().generateDAOProduct().createProduct(context);
    }
}

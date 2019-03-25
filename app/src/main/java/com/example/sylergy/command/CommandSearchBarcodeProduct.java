package com.example.sylergy.command;

import com.example.sylergy.integration.product.factory.DAOProductFactory;
import com.example.sylergy.objects.Context;

public class CommandSearchBarcodeProduct implements Command {

    @Override
    public void execute(Context context) {
       DAOProductFactory.
                getInstance().
                generateDAOProduct().
                readById(context);
    }

}
package com.example.sylergy.command;

import com.example.sylergy.integration.product.factory.DAOProductFactory;
import com.example.sylergy.objects.Context;

public class CommandSearchNameProduct  implements Command{
    @Override
    public void execute(Context context) throws InterruptedException {
        DAOProductFactory.
                getInstance().
                generateDAOProduct().
                readByName(context);
    }
}

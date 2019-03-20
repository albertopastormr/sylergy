package com.example.sylergy.command;

import com.example.sylergy.activities.UpdateActivity;
import com.example.sylergy.controller.Presenter;
import com.example.sylergy.integration.product.factory.DAOProductFactory;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;

public class CommandSearchProduct implements Command {
    private UpdateActivity activity;
    @Override
    public void execute(Object data,UpdateActivity activity) {
        this.activity=activity;
        DAOProductFactory.getInstance().generateDAOProduct().readById((long)data,this);
    }
    @Override
    public void executeResult(Object data){
        if(data != null && data instanceof Product)
            Presenter.getInstance().actionResult(new Context(Events.SEARCH_PRODUCT_OK,data,activity));
        else
            Presenter.getInstance().actionResult(new Context(Events.SEARCH_PRODUCT_ERROR, null,activity));
    }
}

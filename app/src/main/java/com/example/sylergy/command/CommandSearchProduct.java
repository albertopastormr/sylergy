package com.example.sylergy.command;

import com.example.sylergy.Presenter.Presenter;
import com.example.sylergy.integration.product.factory.DAOProductFactory;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;

import java.util.ArrayList;
import java.util.List;
//ActivityDispatcher.getInstance().dispatcher(new Context(Events.SEARCH_PRODUCT_OK, listProducts.get(0)), activitySource);
// ActivityDispatcher.getInstance().dispatcher(new Context(Events.SEARCH_PRODUCT_ERROR, null), activitySource);
public class CommandSearchProduct implements Command {
    private List<Product> listProducts = new ArrayList<>();


    @Override
    public void execute(Context context) {
        Product product = DAOProductFactory.getInstance()
                .generateDAOProduct()
                .readById((Long) context.getData());

        Context newContext;
        if(product == null)
            newContext = new Context(Events.SEARCH_PRODUCT_ERROR,null,context.getActivity());
        else
            newContext = new Context(Events.SEARCH_PRODUCT_OK,product,context.getActivity());

        Presenter.getInstance().dispatchActivity(newContext);
    }
}

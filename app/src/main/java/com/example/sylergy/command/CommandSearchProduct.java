package com.example.sylergy.command;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.sylergy.Presenter.ActivityDispatcher;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
//ActivityDispatcher.getInstance().dispatcher(new Context(Events.SEARCH_PRODUCT_OK, listProducts.get(0)), activitySource);
// ActivityDispatcher.getInstance().dispatcher(new Context(Events.SEARCH_PRODUCT_ERROR, null), activitySource);
public class CommandSearchProduct implements Command {

    private List<Product> listProducts = new ArrayList<>();
    private Product product;
    private Context newContext;
    private Activity activity;

    @Override
    public void execute(Context context, final Activity act) throws InterruptedException {
        //Product product = DAOProductFactory.getInstance().generateDAOProduct().readById((Long) context.getData());

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Products");
        Query q = database.orderByChild("barcode").equalTo(String.valueOf(context.getData()));
        q.addListenerForSingleValueEvent(eventListener);
        activity = act;

        //if(product.getName())
        //    newContext = new Context(Events.SEARCH_PRODUCT_ERROR,null);
        //else

    }

    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            listProducts.clear();
            if(dataSnapshot.exists()) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    listProducts.add(d.getValue(Product.class));
                }
                newContext = new Context(Events.SEARCH_PRODUCT_OK, listProducts.get(0));

                ActivityDispatcher.getInstance().dispatchActivity(newContext, activity);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}

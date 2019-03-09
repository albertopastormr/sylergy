package com.example.example.TestClasses.UnitTests;

import android.support.annotation.NonNull;

import com.example.example.Objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class JUnitTestsMethods {

    /**
     * @param list
     * @return the highest number in a list
     */
    public static Integer getMax(Integer[] list) {
        List<Integer> listedArray = Arrays.asList(list);
        int ret = listedArray.get(0);
        for(Integer x: listedArray){
            if(x > ret){
                ret = x;
            }
        }
        return ret;
    }

    /**
     *
     * @param cat1
     * @param cat2
     * @return the hypotenuse given two sides
     */
    public static Float getHypotenuse(Float cat1, Float cat2){
        return (cat1 * cat1) + (cat2 * cat2);
    }

    /**
     *
     * @param ref
     * @param p
     * @return true if the upload operation was successful, otherwise it returns false
     */
    public static boolean upload(DatabaseReference ref, Product p) {
        try {
            ref.child(p.getBarcode().toString()).setValue(p);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    /**
     *
     * @param q
     * @return Returns a product given a query
     */
    public static Product getProductFromQuery(Query q){
        try{
            final Product[] x = new Product[1];
            /* This quey and the databse reference are emulated by mockito */
            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    x[0] = dataSnapshot.getValue(Product.class);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) { }
            });
            return x[0];
        }
        catch (Exception e){
            return null;
        }
    }


}

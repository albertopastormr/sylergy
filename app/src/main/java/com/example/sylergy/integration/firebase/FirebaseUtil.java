package com.example.sylergy.integration.firebase;

import android.support.annotation.NonNull;

import com.example.sylergy.objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseUtil {

    /**
     * Gets an interconnection reference with base firebase without distinction of main tag
     * @return Standard DatabaseReference
     */
    public static DatabaseReference getBaseReference(){
        return FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Gets an interconnection reference with specified firebase with distinction of main tag
     * @param ref Specific tag with we connect to firebase
     * @return Specified DatabaseReference
     */
    public static DatabaseReference getSpecifiedReference(String ref){
        return FirebaseDatabase.getInstance().getReference(ref);
    }

    /**
     * Make a query in specific database of firebase from a child
     * @param database Reference of databse to whichyou want to make the query
     * @param nameChild Name of child to make the query
     * @return query results
     */
    public static Query getQueryByChild(DatabaseReference database ,String nameChild){
        return database.orderByChild(nameChild);
    }

    /**
     * Make a query in generic database of firebase from a child
     * @param nameChild Name of child to make the query
     * @return query results
     */
    public static Query getQueryByChild(String nameChild){
        return getBaseReference().orderByChild(nameChild);
    }

    /** - Utils methods of test with firebase - **/

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

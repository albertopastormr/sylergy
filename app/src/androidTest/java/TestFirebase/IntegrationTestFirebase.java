package TestFirebase;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.example.Objects.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class IntegrationTestFirebase {

    Product p;
    DatabaseReference ref;
    ValueEventListener eventListener;
    List<Product> list;


    @Before
    public void initialize() {
        list = new ArrayList<>(); //To store the product we will read in the read test
        p = new Product("Test_Product", 345345, Arrays.asList("1, 2, 3"), Arrays.asList("4, 5, 6")); //Just a test product.
        ref = FirebaseDatabase.getInstance().getReference("Productos"); //Reference to a Firebase Database.
        eventListener = new ValueEventListener() { //EventListener that we will use in the read test.
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot d : dataSnapshot.getChildren()) {
                        list.add(d.getValue(Product.class));
                    }
                }
                Log.i("PRODUCT", list.get(0).toString());
                assertEquals(p.getBarcode(), list.get(0).getBarcode());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };
    }

    @Test
    public void upload_test() {
        /* We try to upload the product */
        boolean result = p.create(ref);
        assertTrue(result);
    }

    @Test
    public void drop_test() {
        /* We previously create the product */
        boolean resultCreate = p.create(ref);
        assertTrue(resultCreate);

        /* We drop it */
        boolean resultDrop = p.drop(ref);
        assertTrue(resultDrop);
    }

    @Test
    public void modify_test(){
        /* We previously create the product */
        boolean resultCreate = p.create(ref);
        assertTrue(resultCreate);

        p.setName("Test_Product2");
        p.setIngredients(Arrays.asList("1_1, 2_2, 3_3"));

        /* We modify it and we check if the operation was successful */
        boolean modifyResult = p.modify(ref, p);
        assertTrue(modifyResult);
    }

    @Test
    public void read_test() {
        /* We previously create the product */
        boolean resultCreate = p.create(ref);
        assertTrue(resultCreate);
        p.read(ref, eventListener);
    }














}

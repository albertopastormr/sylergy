package com.example.example.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.example.Objects.Product;
import com.example.example.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public static final String OBJ = "OBJ"; //Used to the define the "key" we will use to send the found object to the other activity

    DatabaseReference database;
    Button b;
    EditText barcodeText;
    List<Product> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<Product>();
        b = findViewById(R.id.sbutton);
        barcodeText = findViewById(R.id.barcode_text);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String g = barcodeText.getText().toString();
                if(g.equals("")){ //Bad input, we notify it to the user
                    Toast.makeText(getApplicationContext(),
                            "You have to set a barcode", Toast.LENGTH_SHORT).show();
                }
                else {
                    database = FirebaseDatabase.getInstance().getReference("Productos");
                    Query q = database.orderByChild("barcode").equalTo(Integer.parseInt(g));
                    q.addListenerForSingleValueEvent(eventListener);
                }
            }
        });
    }

    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            list.clear();
            if(dataSnapshot.exists()) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    list.add(d.getValue(Product.class));
                }
                Intent intent = new Intent(MainActivity.this, ProductActivity.class); //Declare the intention to go to the other activity
                intent.putExtra(OBJ, list.get(0)); //we send data to the other activity
                barcodeText.setText(""); //we reset the barcode textView
                startActivity(intent); //We start our intention
            }
            else{
                Toast.makeText(getApplicationContext(),
                        "The product doesn't exist", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public AppCompatActivity getActivity(){
        return this;
    }

}

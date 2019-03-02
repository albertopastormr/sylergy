package com.example.example.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.example.Objects.Product;
import com.example.example.R;
import com.google.firebase.database.DatabaseReference;


public class MainActivity extends AppCompatActivity {
    public static final String OBJ = "OBJ"; //Used to the define the "key" we will use to send the found object to the other activity

    DatabaseReference database;
    Button b;
    EditText barcodeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    /*  ONLY FOR DEBUGGING  */
                    Product p = new Product("Sandía", Integer.parseInt(g),
                            "Tiene mucho agua y me mola cuando me la como porque se me llena la boca de h2o xd");
                    /*                  */

                    Intent intent = new Intent(MainActivity.this, ProductActivity.class); //Declare the intention to go to the other activity
                    intent.putExtra(OBJ, p); //we send data to the other activity
                    barcodeText.setText(""); //we reset the barcode textView
                    startActivity(intent); //We start our intention
                }
            }
        });



    }
}

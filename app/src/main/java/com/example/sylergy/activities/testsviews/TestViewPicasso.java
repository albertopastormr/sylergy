package com.example.sylergy.activities.testsviews;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.support.v7.app.AppCompatActivity;

import com.example.sylergy.R;
import com.squareup.picasso.Picasso;

/**
 * This class is used to check if Picasso works
 */


public class TestViewPicasso extends AppCompatActivity {
    ImageView imageView;
    Button button;
    Button incorrect;
    boolean setter;
    public boolean isCustomImage;
    public boolean isIncorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_view);

        imageView = findViewById(R.id.test_imageview);
        button = findViewById(R.id.test_buttom);
        incorrect = findViewById(R.id.button_test_2);

        setter = false;
        isCustomImage = false;
        isIncorrect = false;



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setter = !setter;
                if(setter) {
                    setCustonImage();
                }
                else {
                    imageView.setImageResource(R.drawable.logo_v3);
                }
            }
        });

        incorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIncorrectCustomImage();
            }
        });


    }

    public void setCustonImage() {
        try {
            Picasso.with(this).
                    load("https://static.openfoodfacts.org/images/products/848/000/059/2477/front_fr.4.400.jpg").into(imageView);
            isCustomImage = true; //If there is no error, this turns true.

        } catch (Exception e) {
            isCustomImage = false;
        }
    }

    public void setIncorrectCustomImage() {
        try {
            Picasso.with(this).
                    load("").into(imageView);
            isIncorrect = false;
        }
        catch(Exception e) {
            isIncorrect = true;
        }
    }


}
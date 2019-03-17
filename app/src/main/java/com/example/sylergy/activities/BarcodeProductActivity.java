package com.example.sylergy.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sylergy.controller.Presenter;
import com.example.sylergy.objects.Context;
import com.example.sylergy.objects.Events;
import com.example.sylergy.objects.Product;
import com.example.sylergy.R;

public class BarcodeProductActivity extends AppCompatActivity implements UpdateActivity{
    public static final String OBJ = "OBJ"; //Used to the define the "key" we will use to send the found object to the other activity
    public static android.content.Context context;
    Button btnSearch;
    EditText numberCodeText;
    ProgressDialog draw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        btnSearch = findViewById(R.id.btnSearch);
        numberCodeText = findViewById(R.id.barcodeText);
        draw = new ProgressDialog(this);
        draw.setMessage("Searching...");
        draw.setCancelable(false);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberCode = numberCodeText.getText().toString();
                if(numberCode.equals("")){
                    Toast.makeText(getApplicationContext(), "You have to set a barcode", Toast.LENGTH_SHORT).show();
                }
                else{
                    draw.show();
                    btnSearch.setEnabled(false);
                    Presenter.getInstance().action(new Context(Events.SEARCH_PRODUCT, Long.parseLong(numberCode)), BarcodeProductActivity.this);
                }
            }
        });
    }


    @Override
    public void updateWithCommandResult(Context context) {
        draw.hide();
        if(context.getEvent().compareToIgnoreCase(Events.SEARCH_PRODUCT_OK) == 0) {
            Intent intent = new Intent(BarcodeProductActivity.this, ProductActivity.class);
            intent.putExtra(OBJ, (Product)context.getData());
            numberCodeText.setText("");
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"The product doesn't exist", Toast.LENGTH_SHORT).show();
        }
        btnSearch.setEnabled(true);

    }
}

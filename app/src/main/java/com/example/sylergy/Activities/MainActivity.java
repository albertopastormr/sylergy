package com.example.sylergy.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sylergy.Controller.Presenter;
import com.example.sylergy.Objects.Context;
import com.example.sylergy.Objects.Events;
import com.example.sylergy.Objects.Product;
import com.example.sylergy.R;

public class MainActivity extends AppCompatActivity implements UpdateActivity{
    public static final String OBJ = "OBJ"; //Used to the define the "key" we will use to send the found object to the other activity

    Button btnSearch;
    EditText numberCodeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = findViewById(R.id.btnSearch);
        numberCodeText = findViewById(R.id.barcodeText);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberCode = numberCodeText.getText().toString();
                if(numberCode.equals("")){
                    Toast.makeText(getApplicationContext(), "You have to set a barcode", Toast.LENGTH_SHORT).show();
                }
                else{
                    Presenter.getInstance().action(new Context(Events.SEARCH_PRODUCT,numberCode));
                }
            }
        });
    }


    @Override
    public void updateWithCommandResult(Context context) {
        if(context.getEvent()==Events.SEARCH_PRODUCT_OK) {
            Intent intent = new Intent(MainActivity.this, ProductActivity.class);
            intent.putExtra(OBJ, (Product)context.getData());
            numberCodeText.setText("");
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"The product doesn't exist", Toast.LENGTH_SHORT).show();
        }
    }
}

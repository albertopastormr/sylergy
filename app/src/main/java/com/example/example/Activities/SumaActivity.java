package com.example.example.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.example.R;

public class SumaActivity extends AppCompatActivity {

    private Button enviar;
    private EditText primerNumero;
    private EditText segundoNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);

        enviar = findViewById(R.id.enviarBt);
        primerNumero = findViewById(R.id.primerNumeroEt);
        segundoNumero = findViewById(R.id.segundoNumeroEt);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumarNumeros();
            }
        });
    }

    private void sumarNumeros() {
        int a = Integer.parseInt(primerNumero.getText().toString());
        int b = Integer.parseInt(segundoNumero.getText().toString());

        int total = a + b;

        Toast.makeText(this, String.valueOf(total), Toast.LENGTH_SHORT).show();
    }
}

package com.example.actividadcontroles2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView txtConfirm;

    Button btnAceptar, btnCancelar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String nombre = bundle.getString("nombre");
        String apellidos = bundle.getString("apellidos");

        txtConfirm = findViewById(R.id.txtConfirm);
        txtConfirm.setText(String.format("Hola %s %s, ¿Aceptas las condiciones?",
                nombre != null ? nombre.toUpperCase().trim() : "",
                apellidos != null ? apellidos.toUpperCase().trim() : "")
        );

        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnAceptar.setOnClickListener(v -> {
            Intent i = new Intent();
            i.putExtra("estado", MainActivity.Estado.ACEPTADO.toString());
            setResult(RESULT_OK, i);
            finish();
        });

        btnCancelar.setOnClickListener(v -> {
            Intent i = new Intent();
            i.putExtra("estado", MainActivity.Estado.RECHAZADO.toString());
            setResult(RESULT_OK, i);
            finish();
        });



    }
}
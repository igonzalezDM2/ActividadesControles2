package com.example.actividadcontroles2_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView txtResultado;
    Button btnVolver;

    Intent intent;

    boolean correcta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtResultado = findViewById(R.id.txtResultado);
        btnVolver = findViewById(R.id.btnVolver);

        intent = getIntent();
        Bundle datos = intent.getExtras();
        int operando1 = datos.getInt("operando1");
        int operando2 = datos.getInt("operando2");
        int resultado = datos.getInt("resultado");

        correcta = (operando1 + operando2 == resultado);

        txtResultado.setText("El resultado de la operación es " + (correcta ? "CORRECTA" : "INCORRECTA"));

        btnVolver.setOnClickListener(this::volver);

    }

    private void volver(View v) {
        intent.putExtra("acertada", correcta);
        setResult(RESULT_OK, intent);
        finish();
    }
}
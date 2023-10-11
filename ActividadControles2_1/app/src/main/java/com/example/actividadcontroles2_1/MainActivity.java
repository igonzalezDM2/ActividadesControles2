package com.example.actividadcontroles2_1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static enum Estado {
        ACEPTADO, RECHAZADO
    }

    EditText nombre, apellidos;
    Button verificar, volver;

    TextView txtaceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        verificar = findViewById(R.id.verificar);
        volver = findViewById(R.id.volver);
        txtaceptar = findViewById(R.id.txtaceptar);

        volver.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });

        verificar.setOnClickListener(this::verificar);
    }

    private void verificar(View v) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("nombre", nombre.getText().toString());
        intent.putExtra("apellidos", apellidos.getText().toString());
        activityResultLauncher.launch(intent);
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (Activity.RESULT_OK == result.getResultCode()) {
                    Intent intent = result.getData();
                    Bundle bundle = intent.getExtras();
                    String est = bundle.getString("estado");
                    txtaceptar.setText("Aceptas condiciones: " + est);
                }
            });
}
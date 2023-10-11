package com.example.actividadcontroles2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    TextView txtNombre, txtApellidos, txtSexo, txtAficiones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtNombre = findViewById(R.id.resultadoNombre);
        txtApellidos = findViewById(R.id.resultadoApellidos);
        txtSexo = findViewById(R.id.resultadoSexo);
        txtAficiones = findViewById(R.id.resultadoAficiones);

        Bundle extras = getIntent().getExtras();
        String nombre = extras.getString("nombre") != null ? extras.getString("nombre") : "";
        String apellidos = extras.getString("apellidos") != null ? extras.getString("apellidos") : "";
        String sexo = extras.getString("sexo") != null ? extras.getString("sexo") : "";

        String[] aficiones = extras.getStringArray("aficiones");

        StringBuilder sbAficiones = new StringBuilder();
        Arrays.stream(aficiones).forEach(aficion -> {
            if (sbAficiones.toString().isEmpty()) {
                sbAficiones.append(aficion);
            } else {
                sbAficiones.append(String.format(", %s", aficion));
            }
        });
        txtNombre.setText(nombre);
        txtApellidos.setText(apellidos);
        txtSexo.setText(sexo);
        txtAficiones.setText(sbAficiones.toString());


    }
}
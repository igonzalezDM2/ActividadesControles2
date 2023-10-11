package com.example.actividadcontroles2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etApellidos;

    RadioGroup rbgSexo;
    RadioButton rbMasculino, rbFemenino;

    CheckBox cbMusica, cbLectura, cbDeportes, cbViajar;

    Button btnEnviar;

    TextView txtErrores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);

        rbgSexo = findViewById(R.id.rbgSexo);
        rbMasculino = findViewById(R.id.rbMasculino);
        rbFemenino = findViewById(R.id.rbFemenino);

        cbMusica = findViewById(R.id.cbMusica);
        cbLectura = findViewById(R.id.cbLectura);
        cbDeportes = findViewById(R.id.cbDeportes);
        cbViajar = findViewById(R.id.cbViajar);

        btnEnviar = findViewById(R.id.btnEnviar);
        txtErrores = findViewById(R.id.txtErrores);

        btnEnviar.setOnClickListener(this::validar);

    }

    private void validar(View view) {
        String nombre = etNombre.getText() != null ? etNombre.getText().toString() : "";
        String apellidos = etApellidos.getText() != null ? etApellidos.getText().toString() : "";

        int selectedRadio = rbgSexo.getCheckedRadioButtonId();


        String sexo = "";
        if (selectedRadio == R.id.rbMasculino) {
            sexo = "Masculino";
        } else if (selectedRadio == R.id.rbFemenino) {
            sexo = "Femenino";
        }

        ArrayList<String> aficiones = new ArrayList<>(4);
        if (cbMusica.isChecked()) {
            aficiones.add("Música");
        }
        if (cbLectura.isChecked()) {
            aficiones.add("Lectura");
        }
        if (cbDeportes.isChecked()) {
            aficiones.add("Deportes");
        }
        if (cbViajar.isChecked()) {
            aficiones.add("Viajar");
        }

        if (nombre == null || nombre.isEmpty()) {
            txtErrores.setText("El nombre no puede quedar vacío");
        } else if (apellidos == null || apellidos.isEmpty()) {
            txtErrores.setText("El apellido no puede quedar vacío");
        } else if (sexo == null || sexo.isEmpty()) {
            txtErrores.setText("Debes marcar tu sexo");
        } else {
            Intent intent = new Intent(this, MainActivity2.class);
            intent.putExtra("nombre", nombre);
            intent.putExtra("apellidos", apellidos);
            intent.putExtra("sexo", sexo);
            intent.putExtra("aficiones", aficiones.toArray(new String[aficiones.size()]));
            startActivity(intent);
        }

    }
}
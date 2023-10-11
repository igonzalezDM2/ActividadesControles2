package com.example.actividadcontroles2_2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int correctas, incorrectas;

    TextView txtCorrectas;

    EditText operando1, operando2, resultado;
    Button btnResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correctas = 0;
        incorrectas = 0;

        txtCorrectas = findViewById(R.id.txtCorrectas);

        operando1 = findViewById(R.id.operando1);
        operando2 = findViewById(R.id.operando2);
        resultado = findViewById(R.id.resultado);
        btnResultado = findViewById(R.id.btnResultado);
        generarAleatorios();

        btnResultado.setOnClickListener(v -> {
            if (!resultado.getText().toString().isEmpty()) {
                Intent intent = new Intent(this, MainActivity2.class);
                intent.putExtra("operando1", Integer.parseInt(operando1.getText().toString()));
                intent.putExtra("operando2", Integer.parseInt(operando2.getText().toString()));
                intent.putExtra("resultado", Integer.parseInt(!resultado.getText().toString().isEmpty() ? resultado.getText().toString() : "0"));
                activityResultLauncher.launch(intent);
            }
        });
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (Activity.RESULT_OK == result.getResultCode()) {
                    Intent intent = result.getData();
                    //TODO
                    generarAleatorios();
                    Bundle datos = intent.getExtras();
                    boolean correcta = datos.getBoolean("acertada");
                    if (correcta) {
                        correctas++;
                    } else {
                        incorrectas++;
                    }
                    txtCorrectas.setText(String.format("Preguntas correctas: %d; incorrectas: %d", correctas, incorrectas));

                }
            }
    );

    private void generarAleatorios() {
        int op1 = (int) (Math.random() * 101);
        int op2 = (int) (Math.random() * 101);
        operando1.setText(Integer.toString(op1));
        operando2.setText(Integer.toString(op2));
        resultado.setText("");
    }
}
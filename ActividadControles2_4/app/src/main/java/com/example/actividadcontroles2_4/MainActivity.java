package com.example.actividadcontroles2_4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgAnime;
    RadioButton rbNaruto, rbOnePiece, rbDragonBall, rbBakemonogatari, rbJujutsu;

    EditText etNota;

    Button btnComprobar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializar();

        btnComprobar.setOnClickListener(this::comprobar);
    }

    private void comprobar(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        int idRadioSeleccionado = rgAnime.getCheckedRadioButtonId();
        intent.putExtra("idAnime", idRadioSeleccionado);
        if (idRadioSeleccionado > 0) {
            RadioButton sel = findViewById(idRadioSeleccionado);
            intent.putExtra("anime", sel.getText());
        }
        String txtNota = etNota.getText().toString();
        Double nota = txtNota != null && !txtNota.isEmpty() ? Double.parseDouble(txtNota) : -1;
        intent.putExtra("nota", nota);
        activityResultLauncher.launch(intent);
    }

    private void inicializar() {
        rgAnime = findViewById(R.id.rgAnime);

        rbNaruto = findViewById(R.id.rbNaruto);
        rbOnePiece = findViewById(R.id.rbOnePiece);
        rbDragonBall = findViewById(R.id.rbDragonBall);
        rbBakemonogatari = findViewById(R.id.rbBakemonogatari);
        rbJujutsu = findViewById(R.id.rbJujutsu);

        etNota = findViewById(R.id.etNota);

        btnComprobar = findViewById(R.id.btnComprobar);
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        rgAnime.clearCheck();
                        etNota.setText("");
                    }
                }
            });


}
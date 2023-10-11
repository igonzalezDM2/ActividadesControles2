package com.example.actividadcontroles2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private static final Map<Integer, Double> NOTAS = new HashMap<>(5);

    static {
        NOTAS.put(R.id.rbOnePiece, 8.71);
        NOTAS.put(R.id.rbJujutsu, 8.63);
        NOTAS.put(R.id.rbNaruto, 7.99);
        NOTAS.put(R.id.rbBakemonogatari, 8.33);
        NOTAS.put(R.id.rbDragonBall, 7.96);
    }

    Intent intent;
    TextView tvAnime, tvNota, tvNotaPropia, tvDiferencia, tvAviso;

    Button btnVolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        inicializar();
        comprobar();

        btnVolver.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });
    }

    private void inicializar() {
        tvAnime = findViewById(R.id.tvAnime);
        tvNota = findViewById(R.id.tvNota);
        tvNotaPropia = findViewById(R.id.tvNotaPropia);
        tvDiferencia = findViewById(R.id.tvDiferencia);
        tvAviso = findViewById(R.id.tvAviso);
        btnVolver = findViewById(R.id.btnVolver);
    }
    private void comprobar() {

        intent = getIntent();
        Bundle extras = intent.getExtras();

        int idAnime = extras.getInt("idAnime");

        double notaAnime = extras.getDouble("nota");

        if (validar(idAnime, notaAnime)) {
            tvAnime.setText(extras.getString("anime"));
            tvNota.setText(String.format("%.2f", NOTAS.get(idAnime)));
            tvNotaPropia.setText(String.format("%.2f", notaAnime));

            double diferencia = Math.abs(notaAnime - NOTAS.get(idAnime));
            tvDiferencia.setText(String.format("%.2f", diferencia));
        }

    }

    private boolean validar(int idAnime, double notaAnime) {
        if (idAnime <= 0) {
            tvAviso.setText("No has seleccionado un anime");
            return false;
        } else if (notaAnime < 1 || notaAnime > 10) {
            tvAviso.setText("No has puesto una nota válida");
            return false;
        }
        return true;
    }
}
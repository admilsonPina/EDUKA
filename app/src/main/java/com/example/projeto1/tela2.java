package com.example.projeto1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tela2 extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String NOME_USUARIO = "nome_usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        // Recuperar o nome do usuário de SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String nomeUsuario = prefs.getString(NOME_USUARIO, "");

        // Exibir o nome do usuário em um TextView
        TextView textViewBemVindo = findViewById(R.id.textViewBemVindo);
        textViewBemVindo.setText("Escolha o tema:");
        TextView textViewOlaUser = findViewById(R.id.olaUser);
        textViewOlaUser.setText("Olá, " + nomeUsuario + " 😊");



    }

    public void matematica(View view) {
        Intent intent = new Intent(this, Matematica.class);
        startActivity(intent);
    }

    public void portugues(View view) {
        Intent intent = new Intent(this, Portugues.class);
        startActivity(intent);
    }

    public void ciencia(View view) {
        Intent intent = new Intent(this, Ciencias.class);
        startActivity(intent);
    }

    public void tecnologia(View view) {
        Intent intent = new Intent(this, Tecnologia.class);
        startActivity(intent);
    }

    public void voltarT2(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void telaTutorial(View view) {
        Intent intent = new Intent(this, TelaTutorial.class);
        startActivity(intent);
    }
}

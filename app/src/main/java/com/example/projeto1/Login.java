package com.example.projeto1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String NOME_USUARIO = "nome_usuario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void entrar(View view){
        EditText editTextNome = findViewById(R.id.editTextNome);
        String nome = editTextNome.getText().toString();

        if (nome.trim().isEmpty()) {
            // Se o campo de nome estiver vazio, exibir uma mensagem de erro
            Toast.makeText(this, "Por favor, insira seu nome.", Toast.LENGTH_SHORT).show();
        } else {
            // Caso contrário, salvar o nome em SharedPreferences e prosseguir para a próxima tela
            SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString(NOME_USUARIO, nome);
            editor.apply();

            Intent intent = new Intent(this, tela2.class);
            startActivity(intent);
        }
    }
}

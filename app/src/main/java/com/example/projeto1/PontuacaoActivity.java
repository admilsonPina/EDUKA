package com.example.projeto1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PontuacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuacao);

        // Receber os dados passados pela intent
        int score = getIntent().getIntExtra("SCORE", 0);
        int totalQuestions = getIntent().getIntExtra("TOTAL_QUESTIONS", 0);
        int incorrectAnswers = getIntent().getIntExtra("INCORRECT_ANSWERS", 0);

        // Atualizar TextViews com os dados recebidos
        TextView textViewScore = findViewById(R.id.textViewScore);
        TextView textViewTotalQuestions = findViewById(R.id.textViewTotalQuestions);
        TextView textViewIncorrectAnswers = findViewById(R.id.textViewIncorrectAnswers);

        textViewScore.setText("Pontuação: " + score + "/" + totalQuestions);
        textViewTotalQuestions.setText("Total de perguntas: " + totalQuestions);
        textViewIncorrectAnswers.setText("Respostas incorretas: " + incorrectAnswers);

        // Configurar o botão "Terminar"
        Button buttonFinish = findViewById(R.id.buttonFinish);
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PontuacaoActivity.this, tela2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}
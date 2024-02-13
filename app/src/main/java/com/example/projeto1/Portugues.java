package com.example.projeto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Portugues extends AppCompatActivity {

    private TextView textViewQuestion;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private int currentQuestion = 0;
    private int score = 0;
    private int totalQuestions = 0;
    private int incorrectAnswers = 0;

    private List<String[]> questionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portugues);

        textViewQuestion = findViewById(R.id.textView_question);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        readCSV(); // Ler o arquivo CSV

        selectRandomQuestions(); // Selecionar 10 perguntas aleatórias

        displayQuestion(currentQuestion); // Exibir a primeira pergunta

        // Definir ações nos botões
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button1.getText().toString());
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button2.getText().toString());
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button3.getText().toString());
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(button4.getText().toString());
            }
        });
    }

    private void readCSV() {
        InputStream inputStream = getResources().openRawResource(R.raw.questionspt); //  arquivo CSV em português
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] questionData = line.split(","); // Divide a linha em campos separados por vírgula
                questionsList.add(questionData);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void selectRandomQuestions() {
        Collections.shuffle(questionsList); // Embaralhar a lista de perguntas
        if (questionsList.size() > 10) {
            questionsList = questionsList.subList(0, 10); // Selecionar as primeiras 10 perguntas
        }
    }

    private void displayQuestion(int questionIndex) {
        String[] questionData = questionsList.get(questionIndex);
        textViewQuestion.setText(questionData[0]); // A primeira posição contém a pergunta
        List<String> optionsList = new ArrayList<>(Arrays.asList(questionData));
        optionsList.remove(0); // Remove a pergunta da lista de opções
        Collections.shuffle(optionsList);
        button1.setText(optionsList.get(0));
        button2.setText(optionsList.get(1));
        button3.setText(optionsList.get(2));
        button4.setText(optionsList.get(3));
        totalQuestions++;
    }

    private void checkAnswer(String selectedAnswer) {
        String[] questionData = questionsList.get(currentQuestion);
        String correctAnswer = questionData[1]; // A segunda posição contém a resposta correta
        if (selectedAnswer.equals(correctAnswer)) {
            score++;
        } else {
            incorrectAnswers++;
        }

        if (currentQuestion < questionsList.size() - 1) {
            currentQuestion++;
            displayQuestion(currentQuestion);
        } else {
            Toast.makeText(Portugues.this, "Completaste a tarefa!", Toast.LENGTH_SHORT).show();
            // Se todas as perguntas foram respondidas, abrir a tela de pontuação
            Intent intent = new Intent(this, PontuacaoActivity.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("TOTAL_QUESTIONS", totalQuestions);
            intent.putExtra("INCORRECT_ANSWERS", incorrectAnswers);
            startActivity(intent);
            finish(); // Termina a atividade atual para que não possa voltar para as perguntas
        }
    }

    public void voltarP(View view) {
        Intent intent = new Intent(this, tela2.class);
        startActivity(intent);
    }
}


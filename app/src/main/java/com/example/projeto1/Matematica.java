package com.example.projeto1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Matematica extends AppCompatActivity {

    private TextView textViewQuestion;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private int resultado;
    private int score = 0;
    private int totalQuestions = 0;
    private int incorrectAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matematica);

        textViewQuestion = findViewById(R.id.textView_question);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        gerarNovaPergunta();

        // Definir ações nos botões
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(Integer.parseInt(button1.getText().toString()));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(Integer.parseInt(button2.getText().toString()));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(Integer.parseInt(button3.getText().toString()));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarResposta(Integer.parseInt(button4.getText().toString()));
            }
        });


        // Definir ação no botão "Terminar"
        Button btnTerminar = findViewById(R.id.btn_terminar);
        btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarPontuacao();
            }
        });
    }

    private void gerarNovaPergunta() {
        Pergunta p = gerarPergunta();
        textViewQuestion.setText(p.pergunta);
        resultado = p.resultado;

        // Calcular opções de resposta
        int[] respostasErradas = calcularRespostasErradas(resultado);

        // Adicionar o resultado correto às opções de resposta
        int[] opcoesResposta = new int[]{resultado, respostasErradas[0], respostasErradas[1], respostasErradas[2]};

        // Embaralhar as opções de resposta
        embaralharArray(opcoesResposta);

        // Definir textos dos botões
        button1.setText(String.valueOf(opcoesResposta[0]));
        button2.setText(String.valueOf(opcoesResposta[1]));
        button3.setText(String.valueOf(opcoesResposta[2]));
        button4.setText(String.valueOf(opcoesResposta[3]));
    }

    private int[] calcularRespostasErradas(int resultado) {
        Random random = new Random();
        int respostaErrada1;
        int respostaErrada2;
        int respostaErrada3;

        do {
            // Calcular opções de resposta erradas mais próximas do resultado
            respostaErrada1 = resultado + random.nextInt(5) - 4; // +/- 4 do resultado correto
            respostaErrada2 = resultado + random.nextInt(5) - 3; // +/- 3 do resultado correto
            respostaErrada3 = resultado + random.nextInt(5) - 2; // +/- 2 do resultado correto
        } while (respostaErrada1 == resultado || respostaErrada2 == resultado || respostaErrada3 == resultado ||
                respostaErrada1 == respostaErrada2 || respostaErrada1 == respostaErrada3 || respostaErrada2 == respostaErrada3);

        return new int[]{respostaErrada1, respostaErrada2, respostaErrada3};
    }

    private void embaralharArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }


    private Pergunta gerarPergunta() {
        Random random = new Random();
        int num1 = random.nextInt(51); // números de 0 a 50
        int num2 = random.nextInt(10); // números de 0 a 9
        int operacao = random.nextInt(4); // 0 para soma, 1 para subtração, 2 para multiplicação, 3 para divisão
        int resultado;

        String pergunta;
        switch (operacao) {
            case 0:
                pergunta = num1 + " + " + num2 + " = ?";
                resultado = num1 + num2;
                break;
            case 1:
                pergunta = num1 + " - " + num2 + " = ?";
                resultado = num1 - num2;
                break;
            case 2:
                pergunta = num1 + " * " + num2 + " = ?";
                resultado = num1 * num2;
                break;
            case 3:
                // Evitar divisão por zero e números grandes
                num2 = (num2 == 0) ? 1 : num2; // Evitar divisão por zero
                if (num2 > num1) {
                    int temp = num1;
                    num1 = num2;
                    num2 = temp;
                }
                // Verificar se num1 é divisível por num2 para evitar resultados decimais
                if (num1 % num2 == 0) {
                    pergunta = num1 + " / " + num2 + " = ?";
                    resultado = num1 / num2;
                } else {
                    // Se num1 não for divisível por num2 inteiro
                    pergunta = num1 + " / " + num2 + " = (Qual o resultado inteiro)?";
                    resultado = num1 / num2;
                }
                break;

            default:
                throw new IllegalStateException("Operação inválida: " + operacao);
        }

        return new Pergunta(pergunta, resultado, num1, num2, num1 + 1, num2 + 1);
    }

    private void verificarResposta(int respostaUsuario) {
        if (respostaUsuario == resultado) {
            score++;
        } else {
            incorrectAnswers++;
        }
        totalQuestions++;
        gerarNovaPergunta();
    }

    // Classe interna para representar uma pergunta
    private static class Pergunta {
        public int num1;
        public int num2;
        public int num3;
        public int num4;
        String pergunta;
        int resultado;

        Pergunta(String pergunta, int resultado, int num1, int num2, int num3, int num4) {
            this.pergunta = pergunta;
            this.resultado = resultado;
            this.num1 = num1;
            this.num2 = num2;
            this.num3 = num3;
            this.num4 = num4;
        }
    }

    private void voltarM(){
        Intent intent = new Intent(this, tela2.class);
        startActivity(intent);
    }
    // Método para abrir a tela de pontuação
    private void mostrarPontuacao() {
        Intent intent = new Intent(this, PontuacaoActivity.class);
        intent.putExtra("SCORE", score); //
        intent.putExtra("TOTAL_QUESTIONS", totalQuestions); //
        intent.putExtra("INCORRECT_ANSWERS", incorrectAnswers); //
        startActivity(intent);
    }


}


package com.example.projeto1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class TelaTutorial extends AppCompatActivity {

    private ImageView imageViewTutorial;
    private VideoView videoViewTutorial;
    private TextView textViewInstructions;
    private Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_tutorial);

        // Inicializar os elementos visuais
        imageViewTutorial = findViewById(R.id.imageViewTutorial);
        videoViewTutorial = findViewById(R.id.videoViewTutorial);
        textViewInstructions = findViewById(R.id.textViewInstructions);
        buttonNext = findViewById(R.id.buttonNext);

        // Exibir tutorial de Português por padrão

        // Configurar botão "Próximo" para alternar entre os tutoriais
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchTutorial();
            }
        });
    }

    // Método para exibir o tutorial de Português

    // Método para exibir o tutorial de Matemática


    // Método para exibir o tutorial de Ciências


    // Método para exibir o tutorial de Tecnologia


    // Método para alternar entre os tutoriais
    private void switchTutorial() {

    }
}

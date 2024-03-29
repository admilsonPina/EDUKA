package com.example.projeto1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    // variaveis de animacao
    Animation topAnim, buttonAnim;
    ImageView image;
    TextView logo, slogam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animacao
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        buttonAnim = AnimationUtils.loadAnimation(this,R.anim.button_animation);


        logo = findViewById(R.id.textView);
        slogam = findViewById(R.id.textView2);

        image.setAnimation(topAnim);
        logo.setAnimation(buttonAnim);
        slogam.setAnimation(buttonAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();


            }
        },SPLASH_SCREEN);

    }


}

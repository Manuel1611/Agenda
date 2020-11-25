package com.example.agenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.agenda.R;

public class ActivityInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        init();
    }

    private void init() {

        Animation anim1 = AnimationUtils.loadAnimation(ActivityInicio.this, R.anim.despl_arriba);
        Animation anim2 = AnimationUtils.loadAnimation(ActivityInicio.this, R.anim.despl_abajo);

        TextView tvInicio = findViewById(R.id.tvInicio);
        Button btInicioEmpezar = findViewById(R.id.btInicioEmpezar);

        tvInicio.setAnimation(anim2);
        btInicioEmpezar.setAnimation(anim1);

        final MediaPlayer mediaPlayer = MediaPlayer.create(ActivityInicio.this, R.raw.sonidoinicio);

        btInicioEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInicio.this, MainActivity.class);
                startActivity(intent);
                mediaPlayer.start();
                Animatoo.animateFade(ActivityInicio.this);
            }
        });
    }
}
package com.app.apollo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.utilitarios.apollo.mediaPlayer;

//Não implementada
public class UserTypeActivity extends AppCompatActivity {

    protected final String activity = "userTypeActivity";

    RelativeLayout defFisico, defVisual, repeat;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_type);

        //~~~~Adaptação para defs visuais
        defFisico = (RelativeLayout) findViewById(R.id.visual);
        defFisico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Sem distinção de activitys no momento
                intent = new Intent(UserTypeActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        defVisual = (RelativeLayout) findViewById(R.id.fisico);
        defVisual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(UserTypeActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });

        //Repete o áudio inicial
        repeat = (RelativeLayout) findViewById(R.id.repeat);
        repeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //mPlayer.start();
            }
        });

    }
}

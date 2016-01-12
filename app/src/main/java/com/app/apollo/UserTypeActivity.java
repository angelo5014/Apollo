package com.app.apollo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.utilitarios.apollo.mediaPlayer;

//Não implementada
public class UserTypeActivity extends AppCompatActivity {

    public static String userTypeDef = "";
    private Context contexto = this;
    RelativeLayout defFisico, defVisual, repeat;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_type);

        mediaPlayer.tocar("0", contexto);

        defFisico = (RelativeLayout) findViewById(R.id.fisico);
        defFisico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userTypeDef = "defFisico";

                //Sem distinção de activitys no momento
                intent = new Intent(UserTypeActivity.this, UserActivity.class);
                startActivity(intent);

                mediaPlayer.parar();
                mediaPlayer.tocar("1", contexto);
            }
        });

        //~~~~Adaptação para defs visuais
        defVisual = (RelativeLayout) findViewById(R.id.visual);
        defVisual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                userTypeDef = "defVisual";

                intent = new Intent(UserTypeActivity.this, UserActivity.class);
                startActivity(intent);

                mediaPlayer.parar();
                mediaPlayer.tocar("2", contexto);
            }
        });

        repeat = (RelativeLayout) findViewById(R.id.repeat);
        repeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mediaPlayer.parar();
                mediaPlayer.tocar("0", contexto);
            }
        });
    }
}

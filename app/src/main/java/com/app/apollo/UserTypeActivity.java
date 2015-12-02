package com.app.apollo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.view.View;
import android.widget.RelativeLayout;

import com.utilitarios.apollo.mediaPlayer;

//Não implementada
public class UserTypeActivity extends AppCompatActivity {

    protected final String activity = "userTypeActivity";

    RelativeLayout defFisico, defVisual, prox;
    Button nextBtt;
    Intent intent;
    ImageButton repeatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_type);

        //~~~~Adaptação para defs visuais
        defFisico = (RelativeLayout) findViewById(R.id.cadeiranteLayout);
        defFisico.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                defFisicoActivity();
                startActivity(intent);
            }
        });

        defVisual = (RelativeLayout) findViewById(R.id.cegoLayout);
        defVisual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                defVisualActivity();
                startActivity(intent);
            }
        });


        prox = (RelativeLayout) findViewById(R.id.proxActivity);
        prox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        //Repete o áudio inicial
        repeatButton = (ImageButton) findViewById(R.id.repeatBtt);
        repeatButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //mPlayer.start();
            }
        });

        //~~~~~~~~~~~~
        nextBtt = (Button) findViewById(R.id.nextBtt);
        nextBtt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Inicia a Activity
                startActivity(intent);

            }
        });
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Configura a intent como a Activity para Defs Físicos
    public void defFisicoActivity() {
        intent = new Intent(UserTypeActivity.this, UserActivity.class);
    }

    //Configura a intent como a Activity para Defs Visuais
    public void defVisualActivity() {
        intent = new Intent(UserTypeActivity.this, UserActivity.class);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void onRadioButtonClicked(View view) {
        // Retorna true se o botao foi selecionado
        boolean checked = ((RadioButton) view).isChecked();

        // Verifica qual foi selecionado através da Id
        switch(view.getId()) {
            case R.id.cadeiranteRBtt:
                if (checked) {
                    defFisicoActivity();
                }
                    break;
            case R.id.cegoRBtt:
                if (checked) {
                    defVisualActivity();
                }
                    break;
        }
    }
}

package com.app.apollo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {
    UserTypeActivity userTypeActivity = new UserTypeActivity();

    Intent intent;

    //Declaração dos botões
    Button btUser;
    Button btDriver;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Linkando os botoes com o XML
        btDriver = (Button) findViewById(R.id.driver);
        btUser = (Button) findViewById(R.id.user);

        //Listener do btDriver
        btDriver.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, DriverActivity.class);

                //Inicia DriverActivity
                startActivity(intent);

            }
        });

        //Listener do btUser
        btUser.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                switch((userTypeActivity.userTypeDef)) {
                    case "defFisico":
                        intent = new Intent(LoginActivity.this, defFisicoActivity.class);
                        //Inicia a defFisicoActivity
                        startActivity(intent);
                        break;
                    case "defVisual":
                        intent = new Intent(LoginActivity.this, defVisualActivity.class);
                        //Inicia a defVisualActivity
                        startActivity(intent);
                        break;

                    default:
                        //Activity não implementada
                        intent = new Intent(LoginActivity.this,
                                UserTypeActivity.class);

                        //Inicia a UserTypeActivity
                        startActivity(intent);
                }
            }
        });
    }
}

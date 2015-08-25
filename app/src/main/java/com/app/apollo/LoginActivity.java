package com.app.apollo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {

    // Declara��o dos botoes
    Button btUser;
    Button btDriver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Linkando os botoes com o XML
        btUser = (Button) findViewById(R.id.user);
        btDriver = (Button) findViewById(R.id.driver);

        btDriver.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //bot�o motorista inicia a activity DriverActivity
                Intent intent = new Intent(LoginActivity.this,
                        DriverActivity.class);
                startActivity(intent);

            }
        });

        btUser.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                //Activity Usu�rio ainda n�o implementada
                Intent intent = new Intent(LoginActivity.this,
                        UserActivity.class);
                startActivity(intent);
            }
        });
    }
}

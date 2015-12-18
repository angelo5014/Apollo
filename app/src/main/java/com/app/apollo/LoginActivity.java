package com.app.apollo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity {

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
                Intent intent = new Intent(LoginActivity.this,
                        DriverActivity.class);

                //Inicia DriverActivity
                startActivity(intent);

            }
        });

        //Listener do btUser
        btUser.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                //Activity não implementada
                Intent intent = new Intent(LoginActivity.this,
                        UserTypeActivity.class);

                //Inicia a UserTypeActivity
                startActivity(intent);
            }
        });
    }
}

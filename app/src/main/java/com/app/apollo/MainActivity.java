package com.app.apollo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.utilitarios.apollo.mediaPlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    UserTypeActivity userTypeActivity = new UserTypeActivity();

    Intent intent;
    Button userBtt;
    Button driverBtt;

    Toast audioD, audioL;
    boolean audio = true;

    static File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        file = new File(MainActivity.this.getFilesDir(), "settings");

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        audioD = Toast.makeText(MainActivity.this, "Audio desligado", Toast.LENGTH_SHORT);
        audioL = Toast.makeText(MainActivity.this, "Audio ligado", Toast.LENGTH_SHORT);

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Linkando os botoes com o XML
        userBtt = (Button) findViewById(R.id.userBtt);
        driverBtt = (Button) findViewById(R.id.driverBtt);

        //Listener do btDriver
        driverBtt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, DriverActivity.class);

                //Inicia DriverActivity
                startActivity(intent);

            }
        });

        //Listener do btUser
        userBtt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch ((userTypeActivity.userTypeDef)) {
                    case "defFisico":
                        intent = new Intent(MainActivity.this, defFisicoActivity.class);
                        //Inicia a defFisicoActivity
                        startActivity(intent);
                        break;
                    case "defVisual":
                        intent = new Intent(MainActivity.this, defVisualActivity.class);
                        //Inicia a defVisualActivity
                        startActivity(intent);
                        break;

                    default:
                        //Activity não implementada
                        intent = new Intent(MainActivity.this, UserTypeActivity.class);

                        //Inicia a UserTypeActivity
                        startActivity(intent);
                }
            }
        });

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {
            case R.id.setAudio:
                if(audio) {
                    mediaPlayer.volume(0);
                    audioD.show();

                    audio = false;
                } else {
                    mediaPlayer.volume(1);
                    audioL.show();

                    audio = true;
                }
                break;

            case R.id.resetUser:

                break;

            case R.id.about:

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public static void fileWriter(String write) {
        try{
            FileWriter writer = new FileWriter(file);
            writer.append(write);
            writer.flush();
            writer.close();
        } catch(IOException e) {}
    }

    public static void fileReader(String [] txtData) {
        try {
            FileInputStream reader = new FileInputStream(file);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(reader));

            for (int i = 0; i < 1; i++) {
                txtData[i] = myReader.readLine();
            }

            myReader.close();
        } catch (IOException y) {}
    }
}
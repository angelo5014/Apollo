package com.app.apollo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;

public class newLoginActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    UserTypeActivity userTypeActivity = new UserTypeActivity();

    Intent intent;

    /*Declaração dos botões
    Button btUser = (Button) findViewById(R.id.botaozinhoUsuario);
    Button btDriver = (Button) findViewById(R.id.bttDriver);
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_login);

        /*Listener do btDriver
        btDriver.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                intent = new Intent(newLoginActivity.this, DriverActivity.class);

                //Inicia DriverActivity
                startActivity(intent);

            }
        });

        Listener do btUser
        btUser.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                switch((userTypeActivity.userTypeDef)) {
                    case "defFisico":
                        intent = new Intent(newLoginActivity.this, defFisicoActivity.class);
                        //Inicia a defFisicoActivity
                        startActivity(intent);
                        break;
                    case "defVisual":
                        intent = new Intent(newLoginActivity.this, defVisualActivity.class);
                        //Inicia a defVisualActivity
                        startActivity(intent);
                        break;

                    default:
                        intent = new Intent(newLoginActivity.this,
                                UserTypeActivity.class);

                        //Inicia a UserTypeActivity
                        startActivity(intent);
                }
            }
        });
*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Configurações", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer /*toolbar*/, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

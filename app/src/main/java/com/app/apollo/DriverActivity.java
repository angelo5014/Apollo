package com.app.apollo;

import com.utilitarios.apollo.*;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.IOException;

import com.app.apollo.R;

public class DriverActivity extends Activity {

    protected final String activity = "driverActivity";
    private static final String URL = "http://200.188.161.248:8080/WSH2/recurso/";
    private Context contexto = this;

    private ImageView recolhidoSign;
    private boolean batata = true;
    private int idParada;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    protected void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.driver);
        setupElements();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private void setupElements() {
        Button btOK;

        recolhidoSign = (ImageView) findViewById(R.id.recolhidoSign);
        btOK = (Button) findViewById(R.id.btRecolhido);

        startGPS();

        btOK.setOnClickListener(new OnClickListener() {
            @SuppressWarnings("ResourceType")
            public void onClick(View v) {
                recolhidoSign.setImageResource(R.drawable.error);
                paradaFechada();
                batata = true;


              /*  new CountDownTimer(30000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                    }
                }.start();*/
            }

        });
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void updateView(Location locat) {


        EditText edLatitude = (EditText) findViewById(R.id.lati);
        EditText edLongitude = (EditText) findViewById(R.id.longi);


        Double latitude = locat.getLatitude();
        Double longitude = locat.getLongitude();

        edLatitude.setText(latitude.toString());
        edLongitude.setText(longitude.toString());

        Log.d("LATITUDE", latitude.toString());
        Log.d("LONGITUDE", longitude.toString());

        String resposta = WebService.acesso(URL + "atualizar" + "/" + "1" + "/" + /*latitude.toString()*/600 + "/" + /*longitude.toString()*/-150);

        Log.e("FODASEERRADO", resposta);
                idParada = Double.valueOf(resposta).intValue();
                mudarSign();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private void paradaFechada() {
        if(idParada != 0) {
            WebService.acesso(URL + "fechar_parada" + "/" + /*idParada*/1);


        }
    }


    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private void mudarSign() {
        if (batata) {
            if (idParada != 0) {
                recolhidoSign.setImageResource(R.drawable.check);
                mediaPlayer.tocar(contexto);
                batata = false;
            } else {
                batata = true;
            }
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    //Metodo que inicia o GPS para coletar Longitude e Latitude
    private void startGPS() {
        LocationManager lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener lListener = new LocationListener() {
            public void onLocationChanged(Location locat) {
                updateView(locat);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lListener);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Verifica a conexão com a internet
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }
}
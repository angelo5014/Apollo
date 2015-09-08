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
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class DriverActivity extends Activity {

    private static final String URL = "http://200.188.161.248:8080/WSH2/recurso/";
    //private static final String URL = "http://200.188.161.248:8080/WSH2/recurso/consultarnumeroSOS/2";

    private Context contexto = this;

    private RelativeLayout tela;
    private boolean batata = true;
    private double idParada = 1;



    @Override
    protected void onCreate(Bundle save) {
        super.onCreate(save);
        setContentView(R.layout.driver);
        setupElements();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

    private void setupElements(){
        Button btOK;

        tela = (RelativeLayout)findViewById(R.id.relativeLayout1);
        btOK = (Button)findViewById(R.id.btRecolhido);

        startGPS();

        btOK.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                paradaFechada(idParada);
                tela.setBackgroundColor(getResources().getColor(R.color.Red));
                batata = true;
            }
        });
    }


//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

    public void updateView(Location locat){


        EditText edLatitude = (EditText)findViewById(R.id.lati);
        EditText edLongitude = (EditText)findViewById(R.id.longi);


        Double latitude = locat.getLatitude();
        Double longitude = locat.getLongitude();

        edLatitude.setText(latitude.toString());
        edLongitude.setText(longitude.toString());

        Log.d("LATITUDE", latitude.toString());
        Log.d("LONGITUDE", longitude.toString());

        String resposta = WebService.acesso(URL  + "atualizar" + "/" + "1" + "/" + latitude.toString() + "/" + longitude.toString());
        System.out.println(resposta);
        Double respostaD = Double.parseDouble(resposta);
        mudarTela(respostaD);

    }

    //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    private void paradaFechada(double idParada){
        String resposta = WebService.acesso(URL + "fecharSolParada" + "/" + idParada);
    }


    //*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
    private void mudarTela(double idParada){
        if(batata) {
            if (idParada != 0) {
                tela.setBackgroundColor(getResources().getColor(R.color.Green));
                mediaplayer.tocar(contexto);
                batata = false;
            } else {
                batata = true;
            }
        }
    }
//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

//Metodo que inicia o GPS para coletar Longitude e Latitude

    private void startGPS(){
        LocationManager lManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener lListener = new LocationListener() {
            public void onLocationChanged(Location locat) {
                updateView(locat);
            }
            public void onStatusChanged(String provider, int status, Bundle extras) {}
            public void onProviderEnabled(String provider) {}
            public void onProviderDisabled(String provider) {}
        };
        lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, lListener);
    }


//-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-

    // check network connection
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


//*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-


}

package com.app.apollo;

import android.app.Activity;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.utilitarios.apollo.WebService;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

public class UserActivity extends Activity implements BeaconConsumer {
    protected static final String TAG = "RangingActivity";
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        beaconManager = BeaconManager.getInstanceForApplication(this);

        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));

        beaconManager.bind(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }


    String []IDbeacon = new String[2];
    boolean solAberta= false;
    Beacon firstBeacon;
    Beacon definitiveBeacon;
    int aux = 0;
    int aux2 = 0;
    int contador = 0;
    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    firstBeacon = beacons.iterator().next();
                    logToDisplay("The first beacon I see is about " + firstBeacon.getDistance() + " meters away.");
                    Log.d(TAG, "The first beacon I see is about " + firstBeacon.getDistance() + " meters away.");

                   //IDbeacon[aux] = firstBeacon.toString();
                    aux++;
                    if (aux >= 1) {
                        aux = 0;
                    }
                    if (!solAberta) {
                  /*      if (IDbeacon[1] != null) {
                            if (IDbeacon[0].equalsIgnoreCase(IDbeacon[1])) {
                               aux2++;
                                if (aux2 > 6) {
                                    aux2 = 0;
                                    definitiveBeacon = firstBeacon;*/
                                    if (firstBeacon.getDistance() < 3.0) {

                                    if (contador < 6) {
                                        contador = 0;
                                        logToDisplay("ABRIUUU" + WebService.acesso("http://200.188.161.248:8080/WSH2/recurso/abrirSolParada/1"));
                                        solAberta = true;
                                    }
                               /* } else {
                                    logToDisplay("Há interferência de um outro beacon");
                                }*/
                 //           }
                        }
                    }
                }else{
                    String resposta = WebService.acesso("http://200.188.161.248:8080/WSH2/recurso/abrirSolParada/1");
                    if (resposta.equalsIgnoreCase("1")){
                        solAberta = true;
                    }else{
                        solAberta = false;

                    }/**/
                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }
    }




    private void logToDisplay(final String line) {
        runOnUiThread(new Runnable() {
            public void run() {
                EditText editText = (EditText) UserActivity.this.findViewById(R.id.rangingText);
                editText.append(line + "\n");
            }
        });
    }
}

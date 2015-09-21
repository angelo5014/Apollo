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
    Beacon firstBeacon;
    boolean solParada = false;
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

    int aux = 0;
    String id;

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    firstBeacon = beacons.iterator().next();
                    if (solParada == false) {
                        if (firstBeacon.getDistance() < 3) {
                            aux++;
                            if (aux >= 5) {
                                id = firstBeacon.getId1().toString();
                                logToDisplay(WebService.acesso("http://200.188.161.248:8080/WSH2/recurso/abrir_parada/" +
                                        id.substring(id.length() - 1, id.length())));
                                aux = 0;
                                solParada = true;
                            }
                        }
                    } else if (aux == 0) {
                        if (id != null) {
                            String resposta = WebService.acesso("http://200.188.161.248:8080/WSH2/recurso/abrir_parada/" +
                                    id.substring(id.length() - 1, id.length()));
                            if (resposta.equalsIgnoreCase("1")) {
                                solParada = true;
                            } else {
                                solParada = false;
                                WebService.acesso("http://200.188.161.248:8080/WSH2/recurso/fecharSol_parada/" +
                                        id.substring(id.length() - 1, id.length()));
                            }
                        }
                    }
                }

            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {
        }
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

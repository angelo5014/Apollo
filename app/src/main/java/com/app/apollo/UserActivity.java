package com.app.apollo;

import android.app.Activity;
import android.os.Handler;
import android.os.RemoteException;
import android.os.Bundle;
import android.widget.EditText;

import com.utilitarios.apollo.WebService;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

public class UserActivity extends Activity implements BeaconConsumer {
    Beacon firstBeacon;
    boolean solicitarParada = false, statusSolicitacao = false, onCounting = false;
    private BeaconManager beaconManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));
        beaconManager.bind(this);
        timer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    int aux = 0;
    String id;

    @Override
    public void onBeaconServiceConnect() {
        if(solicitarParada && statusSolicitacao) {
            if(!onCounting) {
                logToDisplay("Starting Timer");
                timer();
            }
        } else {
            logToDisplay("Sending request to server");
            beaconManager.setRangeNotifier(new RangeNotifier() {
                @Override
                public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                    if (beacons.size() > 0) {
                        firstBeacon = beacons.iterator().next();
                        if (!solicitarParada) {
                            if (firstBeacon.getDistance() < 3) {
                                aux++;
                                if (aux > 4) {
                                    id = firstBeacon.getId1().toString();
                                    logToDisplay(WebService.acesso("http://200.188.161.248:8080/WSH2/recurso/abrir_parada/" +
                                            id.substring(id.length() - 1, id.length())));
                                    aux = 0;
                                    solicitarParada = true;
                                    statusSolicitacao = true;
                                }
                            }
                        } else if (aux == 0) {
                            if (id != null) {
                                String resposta = WebService.acesso("http://200.188.161.248:8080/WSH2/recurso/abrir_parada/" +
                                        id.substring(id.length() - 1, id.length()));
                                if (resposta.equalsIgnoreCase("0")) {
                                    solicitarParada = true;
                                    statusSolicitacao = true;
                                } else {
                                    solicitarParada = false;
                                    WebService.acesso("http://200.188.161.248:8080/WSH2/recurso/fecharSol_parada/" +
                                            id.substring(id.length() - 1, id.length()));
                                    statusSolicitacao = true;
                                }
                            }
                        }
                    }
                }
            });
        }
        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {

        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private void logToDisplay(final String line) {
        runOnUiThread(new Runnable() {
            public void run() {
                EditText editText = (EditText) UserActivity.this.findViewById(R.id.rangingText);
                editText.append(line + "\n");
            }
        });
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private void timer() {
        onCounting = true;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                statusSolicitacao = false;
                onCounting = false;
                logToDisplay("Working");
            }
        }, 30000);//30seg
    }
}

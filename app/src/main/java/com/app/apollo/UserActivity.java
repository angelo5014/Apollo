package com.app.apollo;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;




public class UserActivity extends Activity {
    protected static final String TAG = "UserActivity";
    private BeaconManager beaconManager;
    BeaconReferenceApp beaconApp = new BeaconReferenceApp();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        verifyBluetooth();
        logToDisplay("A aplicacao iniciou");

    }

    public void onRangingClicked(View view){
        Intent intent = new Intent(this, RangingActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void onResume(){
        super.onResume();
        ((BeaconReferenceApp)this.getApplicationContext()).setUserActivity(this);
    }

    @Override
    public void onPause(){
        super.onPause();
        ((BeaconReferenceApp)this.getApplicationContext()).setUserActivity(null);
    }

    private void verifyBluetooth(){

        try{
            if(!BeaconManager.getInstanceForApplication(this).checkAvailability()){
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bluetooth not enabled");
                builder.setMessage("Please enable bluetooth in settings and restart this application.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                        System.exit(0);
                    }
                });
                builder.show();
            }
        }catch (RuntimeException e) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bluetooth LE not available");
            builder.setMessage("Sorry, this device does not support Bluetooth LE.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                    System.exit(0);
                }

            });
            builder.show();

        }
    }

    public void logToDisplay(final String line) {
        runOnUiThread(new Runnable() {
            public void run() {
                EditText editText = (EditText)UserActivity.this
                        .findViewById(R.id.monitoringText);
                editText.append(line+"\n");
            }
        });
    }

}

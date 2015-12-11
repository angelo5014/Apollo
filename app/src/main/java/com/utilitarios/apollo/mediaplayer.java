package com.utilitarios.apollo;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.app.apollo.R;

public class mediaPlayer {

    static MediaPlayer mPlayer = null;
    static String som = "";

    //Metodo que inicia o mediaplayer com um determinado som
    public static void tocar(Context contexto){

        mPlayer = MediaPlayer.create(contexto, Uri.parse(som));

        mPlayer.start();

        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
                mp = null;
            }
        });
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~
    public static void identificarActivity(String activity, String method) {
        switch (activity) {
            case "userTypeActivity":

                break;
            case "driverActivity":

                break;
        }
    }
}

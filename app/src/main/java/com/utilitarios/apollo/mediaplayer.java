package com.utilitarios.apollo;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import com.app.apollo.R;
import com.app.apollo.UserTypeActivity;

public class mediaPlayer {

    static MediaPlayer mPlayer = null;

    /*
    public static void getUserType(String def_fisico) {
        switch(def_fisico) {
            case "0":
                som = "R.raw.audio.UserTypeAudio";
                break;
            case "1":
                som = "R.raw.audio.selectFisica";
                break;
            case "2":
                som = "R.raw.audio.selectVisual";
                break;
            case "3":
                som = "R.raw.audio.proxParada";
        }
    }
    */

    //Metodo que inicia o mediaplayer com um determinado som
    public static void tocar(String user, Context contexto){
        switch(user) {
            case "0":
                mPlayer = MediaPlayer.create(contexto, R.raw.user_type_audio);
                break;
            case "1":
                mPlayer = MediaPlayer.create(contexto, R.raw.select_fisica);
                break;
            case "2":
                mPlayer = MediaPlayer.create(contexto, R.raw.select_visual);
                break;
            case "3":
                mPlayer = MediaPlayer.create(contexto, R.raw.prox_parada);
        }


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

    public static void parar() {
        mPlayer.stop();
    }
}

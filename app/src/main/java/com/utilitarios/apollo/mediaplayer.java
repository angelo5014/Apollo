package com.utilitarios.apollo;

import android.content.Context;
import android.media.MediaPlayer;

import com.app.apollo.R;

public class mediaPlayer {

    static MediaPlayer mPlayer;
    static float volume = 1;

    //Metodo que inicia o mediaPlayer com um determinado som
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

        mPlayer.setVolume(volume, volume);
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

    public static void volume(float vol) {
        volume = vol;
    }
}

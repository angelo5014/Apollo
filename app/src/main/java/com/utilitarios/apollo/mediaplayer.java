package com.utilitarios.apollo;

import android.content.Context;
import android.media.MediaPlayer;

import com.app.apollo.R;

public class mediaplayer {

    static MediaPlayer mp = null;

    //Metodo que inicia o mediaplayer com um som determinado

    public static void tocar(Context contexto){

        mp = MediaPlayer.create(contexto, R.raw.fernanda);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
                mp = null;
            }
        });
        mp.start();
    }
}

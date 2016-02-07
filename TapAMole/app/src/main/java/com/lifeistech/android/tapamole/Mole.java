package com.lifeistech.android.tapamole;

import android.widget.ImageView;

/**
 * Created by TomoyaHayakawa on 16/02/07.
 */
public class Mole {

    int state;              // モグラの状態 0:潜っている 1:出てきている 2:叩かれている
    ImageView moleImage;    // モグラのImageView

    android.os.Handler h;   // Handlerスレッド間の処理を投げる役割

    Runnable hide;          // Handlerで投げる処理の中身を書くためのクラス

    public Mole(ImageView imageView) {
        state = 0;
        moleImage = imageView;
        moleImage.setImageResource(R.drawable.mole1);

        h = new android.os.Handler();
        hide = new Runnable() {
            @Override
            public void run() {
                state = 0;
                moleImage.setImageResource(R.drawable.mole1);
            }
        };
    }

    public void start() {
        if(state == 0) {
            state = 1;
            moleImage.setImageResource(R.drawable.mole2);

            h.postDelayed(hide, 1000);
        }
    }

    public int tapMole() {
        if(state == 1) {    // モグラが潜っていたら
            state = 2;
            moleImage.setImageResource(R.drawable.mole3);

            h.removeCallbacks(hide);    // start() での postDelayed を削除
            h.postDelayed(hide, 1000);
            return 1; // score one
        }

        return 0; // score zero
    }
}

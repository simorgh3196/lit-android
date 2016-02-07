package com.lifeistech.android.clap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by TomoyaHayakawa on 16/02/07.
 */
public class Clap {

    // 音楽プレーヤー
    private SoundPool soundPool;
    // 音声ID
    private int soundId;

    public Clap(Context context) {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(context, R.raw.clap, 1);
    }

    public void play() {
        soundPool.play(soundId, 1.0f, 1.0f, 0, 0, 1.0f);
    }

    public void repearPlay(int repeat) {
        int count = 0;

        while(count < repeat) {
            play();
            count++;

            try {
                Thread.sleep(500);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

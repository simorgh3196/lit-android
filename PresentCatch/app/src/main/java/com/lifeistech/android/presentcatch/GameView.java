package com.lifeistech.android.presentcatch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;


public class GameView extends SurfaceView implements SurfaceHolder.Callback, Runnable, SensorEventListener {

    static final long FPS = 30;
    static final long FRAME_TIME = 1000 / FPS;

    int screenWidth, screenHeight;
    int score = 0;
    int life = 10;

    SurfaceHolder surfaceHolder;
    Thread thread;

    Present present;
    Bitmap presentImage;
    Player player;
    Bitmap playerImage;


    class Present {
        private static final int WIDTH = 100;
        private static final int HEIGHT = 100;

        float x, y;

        public Present() {
            Random random = new Random();
            x = random.nextInt(screenWidth - WIDTH);
            y = 0;
        }

        void update() {
            y += 15.0f;
        }

        public void reset() {
            Random random = new Random();
            x = random.nextInt(screenWidth - WIDTH);
            y = 0;
        }
    }

    class Player {

        final int WIDTH = 100;
        final int HEIGHT = 200;
        float x, y;

        public Player() {

            x = 0;
            y = screenHeight - HEIGHT;
        }

        public void move(float diffX) {

            this.x += diffX;
            this.x = Math.max(0, x);
            this.x = Math.min(screenWidth - WIDTH, x);
        }

        public boolean isEnter(Present present) {

            if (present.x + Present.WIDTH > x && present.x < x + WIDTH &&
                    present.y + Present.HEIGHT > y && present.y < y + HEIGHT) {
                return true;
            }
            return false;
        }
    }


    @Override
    public void run() {

        present = new Present();
        player = new Player();

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setFakeBoldText(true);
        textPaint.setTextSize(100);

        while (thread != null) {

            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(presentImage, present.x, present.y, null);
            canvas.drawBitmap(playerImage, player.x, player.y, null);

            canvas.drawText("SCORE :" + score, 50, 150, textPaint);
            canvas.drawText("LIFE :" + life, 50, 300, textPaint);

            if (life == 0) {
                canvas.drawText("Game Over", screenWidth / 6, screenHeight / 2, textPaint);
                surfaceHolder.unlockCanvasAndPost(canvas);
                break;
            }

            // 当たり判定
            if (player.isEnter(present)) {
                present.reset();
                score += 10;
            }
            // 画面外に出たらリセット
            else if (present.y > screenHeight) {
                present.reset();
                life--;
            }else {
                present.update();
            }

            surfaceHolder.unlockCanvasAndPost(canvas);

            try {
                thread.sleep(FRAME_TIME);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public GameView(Context context) {
        super(context);

        // SurfaceHolderの状態を取得するための処理
        getHolder().addCallback(this);

        Resources resources = context.getResources();
        presentImage = BitmapFactory.decodeResource(resources, R.drawable.like);
        playerImage = BitmapFactory.decodeResource(resources, R.drawable.bird);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        surfaceHolder = holder;
        thread = new Thread(this);
        thread.start();

//        Paint paint = new Paint();
//        paint.setColor(Color.RED);          // 赤色
//        paint.setStyle(Paint.Style.FILL);   // 塗りつぶし
//
//        Canvas canvas = holder.lockCanvas();
//        canvas.drawColor(Color.WHITE);          // 背景を白で塗りつぶす
////        canvas.drawCircle(100, 200, 50, paint); // (100, 200)に半径50の円
//        canvas.drawBitmap(presentImage, 100, 200, null);
//        holder.unlockCanvasAndPost(canvas);     // SurfaceViewにcanvasの内容を反映
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        screenWidth = width;
        screenHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        thread = null;
    }


}

package com.lifeistech.android.tictactoe;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int PLAYER_COUNT = 2;
    private static final int[] PLAYER_IMAGES = {R.drawable.ic_batsu, R.drawable.ic_maru};

    private int mTurn;                   // ターン数　プレイヤー1：1, プレイヤー2：0
    private int [] mGameBoard;           // ゲームの盤面　未選択：-1
    private ImageButton[] mBoardButtons; // 実際に見えているゲームの盤面：ボタンの配列
    private TextView mPlayerTextView;    // プレイヤーとターン表示用のTextView
    private TextView mWinnerTextView;    // 勝敗表示用のTextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPlayerTextView = (TextView) findViewById(R.id.playerText);
        mWinnerTextView = (TextView) findViewById(R.id.winnerText);

        mBoardButtons = new ImageButton[9];
        int[] buttonIDs = {
                R.id.imageButton1,
                R.id.imageButton2,
                R.id.imageButton3,
                R.id.imageButton4,
                R.id.imageButton5,
                R.id.imageButton6,
                R.id.imageButton7,
                R.id.imageButton8,
                R.id.imageButton9
        };

        for(int i = 0; i < mBoardButtons.length; i++) {
            mBoardButtons[i] = (ImageButton) findViewById(buttonIDs[i]);
            mBoardButtons[i].setTag(new Integer(i));
        }

        init();
        setPlayer();
    }

    private void init() {

        mTurn = 1;
        mGameBoard = new int[mBoardButtons.length];
        for(int i = 0; i < mBoardButtons.length; i++) {
            // 盤面全て未選択に，画像も消す
            mGameBoard[i] = -1;
            mBoardButtons[i].setImageBitmap(null);
        }

        mWinnerTextView.setVisibility(View.GONE);   //　LinearLayoutの詰めて消す
    }

    private void setPlayer() {

        if (mTurn % 2 == 0) {
            mPlayerTextView.setText("Player: ×(2)");
        }else {
            mPlayerTextView.setText("Player: ○(1)");
        }
    }

    public void tapMark(View v) {

        // 勝敗が決定していたら抜ける
        if (mWinnerTextView.getVisibility() == View.VISIBLE) return;

        // タップされたボタンのTagを取得
        int buttonNumber = (Integer) v.getTag();

        if (mGameBoard[buttonNumber] == -1) {
            // 現在のプレイヤーのコマをセットする
            mBoardButtons[buttonNumber].setImageResource(PLAYER_IMAGES[mTurn % 2]);
            mGameBoard[buttonNumber] = mTurn % 2;

            int judge = judgeGame();

            if (judge != -1) {
                if (judge == 0) {
                    // 勝敗がついた場合
                    mWinnerTextView.setText("Game End\nPlayer: ×(2)\nWin");
                    mWinnerTextView.setTextColor(Color.BLUE);
                } else {
                    mWinnerTextView.setText("Game End\nPlayer: ○(1)\nWin");
                    mWinnerTextView.setTextColor(Color.RED);
                }
                mWinnerTextView.setVisibility(View.VISIBLE);
            } else {
                // 全てのマスが埋まっているが、勝敗が決まらなかった場合
                if (mTurn >= mGameBoard.length) {
                    mWinnerTextView.setText("Game End\nDraw");
                    mWinnerTextView.setTextColor(Color.YELLOW);
                    mWinnerTextView.setVisibility(View.VISIBLE);
                }
                // 次のターンにする
                mTurn++;
                setPlayer();
            }
        }
    }

    private int judgeGame() {

        for (int i = 0; i < 3; i++) {
            // 横並びをチェック
            if (mGameBoard[i * 3] != -1 &&
                    mGameBoard[i * 3] == mGameBoard[i * 3 + 1] &&
                    mGameBoard[i * 3] == mGameBoard[i * 3 + 2]) {
                return mGameBoard[i * 3];
            }

            // 縦並びをチェック
            if (mGameBoard[i] != -1 &&
                    mGameBoard[i] == mGameBoard[i + 3] &&
                    mGameBoard[i] == mGameBoard[i + 6]) {
                return mGameBoard[i];
            }
        }

        // 斜め：左上ー右下
        if (mGameBoard[0] != -1 &&
                mGameBoard[0] == mGameBoard[4] &&
                mGameBoard[0] == mGameBoard[8]) {
            return mGameBoard[0];
        }

        // 斜め：左下ー右上
        if (mGameBoard[2] != -1 &&
                mGameBoard[2] == mGameBoard[4] &&
                mGameBoard[2] == mGameBoard[6]) {
            return mGameBoard[2];
        }

        return  -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {

            init();
            setPlayer();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

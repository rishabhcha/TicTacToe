package com.example.rishabh.connecting3;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int person = 0;
    int[] position = {2,2,2,2,2,2,2,2,2};
    boolean isGameOver = false;
    int ctr=0;
    String winner = "";

    SeekBar seekBar;
    TextView timmer;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timmer = (TextView) findViewById(R.id.timmer);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(10);
        seekBar.setProgress(0);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                timmer.setText("0:"+Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int pos = Integer.parseInt(counter.getTag().toString());
        if(position[pos]==2 && !isGameOver && ctr!=9) {
            counter.setTranslationY(-1000f);
            position[pos]=person;
            ctr++;
            if (person == 0) {
                person = 1;
                counter.setImageResource(R.drawable.cross);
                counter.animate().translationYBy(1000f).rotation(3600).setDuration(500);

            }
            else{
                person = 0;
                counter.setImageResource(R.drawable.circle);
                counter.animate().translationYBy(1000f).rotation(3600).setDuration(500);

            }
        }
        if((position[0] == 1 && position[1] == 1 && position[2] == 1) ||
                    (position[0] == 1 && position[3] == 1 && position[6] == 1) ||
                    (position[0] == 1 && position[4] == 1 && position[8] == 1) ||
                    (position[1] == 1 && position[4] == 1 && position[7] == 1) ||
                    (position[2] == 1 && position[4] == 1 && position[6] == 1) ||
                    (position[2] == 1 && position[5] == 1 && position[8] == 1) ||
                    (position[3] == 1 && position[4] == 1 && position[5] == 1) ||
                    (position[6] == 1 && position[7] == 1 && position[8] == 1)){
                isGameOver=true;
                winner= "player 2";
            }
        if((position[0] == 0 && position[1] == 0 && position[2] == 0) ||
                    (position[0] == 0 && position[3] == 0 && position[6] == 0) ||
                    (position[0] == 0 && position[4] == 0 && position[8] == 0) ||
                    (position[1] == 0 && position[4] == 0 && position[7] == 0) ||
                    (position[2] == 0 && position[4] == 0 && position[6] == 0) ||
                    (position[2] == 0 && position[5] == 0 && position[8] == 0) ||
                    (position[3] == 0 && position[4] == 0 && position[5] == 0) ||
                    (position[6] == 0 && position[7] == 0 && position[8] == 0)){
                isGameOver=true;
                winner="player 1";
            }

        if(ctr==9 && !isGameOver){
            LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
            TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
            winnerMessage.setText("Match Draw");
            winnerLayout.setVisibility(View.VISIBLE);
            seekBar.setProgress(0);

        }
        if (isGameOver){
            LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
            TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
            winnerMessage.setText("Match Won by "+winner);
            winnerLayout.setVisibility(View.VISIBLE);
        }
    }

    public void playAgain(View view) {
        person = 0;
        for(int i=0;i<position.length;i++){
            position[i]=2;
        }
        isGameOver = false;
        ctr=0;
        winner = "";
        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
        seekBar.setProgress(0);


    }

    public void resetGame(View view) {
        person = 0;
        for(int i=0;i<position.length;i++){
            position[i]=2;
        }
        isGameOver = false;
        ctr=0;
        winner = "";
        LinearLayout winnerLayout = (LinearLayout) findViewById(R.id.winnerLayout);
        winnerLayout.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
        seekBar.setProgress(0);


    }

    public void startTimmer(View view) {



        new CountDownTimer(seekBar.getProgress()*1000+100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                seekBar.setProgress((int) millisUntilFinished / 1000);

                timmer.setText("0:" + Integer.toString((int) millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {

                timmer.setText("0:0");
                mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.pop);
                mediaPlayer.start();

                seekBar.setProgress(0);
            }

        }.start();
    }
}

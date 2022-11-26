package com.example.joytoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;


    // 0 - x
    //1 - 0
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};

//    0 - x
//    1 - o
//    2 - blank

    int[][] winPos = {{0,1,2},{3,4,5},{6,7,8},
                    {0,3,6},{1,4,7},{2,5,8},
                    {0,4,8},{2,4,6}};

    public void playTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0){
                final MediaPlayer  mediaPlayer = MediaPlayer.create(this,R.raw.beep);
                Button btn = findViewById(R.id.button);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mediaPlayer.start();
                        gameReset(view);
                    }
                });
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);

                status.setText("O's Turn");
            }else{
                img.setImageResource(R.drawable.o1);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn");
//                status.setTextColor(121212);
            }
        img.animate().translationYBy(1000f).setDuration(300);
        }
        //Check if Any Player has Own
        for(int[] winPosition : winPos){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2){
                //somebody has own
                String winnerStr;
                gameActive = false;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X has Won";
                }else{
                    winnerStr = "O has Won";
                }
                //Update Status
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }

    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i < gameState.length ; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's Turn");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
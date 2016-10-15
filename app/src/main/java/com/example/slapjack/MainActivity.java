package com.example.slapjack;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    long startTime = 0;
    int userScore = 0;
    int computerScore = 0;
    Random random = new Random();
    ImageView slapDeck;
    TextView score;
    Button startButton;
    Button jackButton;
    Button resetButton;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            slapDeck.setImageResource(R.drawable.backfaceblue);

        }

    };

    public void Start(View view) {
        SelectCards();

    }

    public void SelectCards() { //Stores the cards in an arraylist so they can be selected to randomly appear/flip through


        ArrayList<Integer> myImageList = new ArrayList<>();
        Resources res = getResources();
        String packageName = getPackageName();
        String[] highs = {"ace", "jack", "king", "queen"};
        String[] suits = {"cLubs", "diamonds", "hearts", "spades"};
        String[] jokers = {"jokerblack", "jokerred"};
        for (int i = 2; i < 11; i++) {
            for (String suit : suits) {
                int id = res.getIdentifier(suit + i, "drawable", packageName);
                myImageList.add(id);
            }
        }
        for (String high : highs) {
            for (String suit : suits) {
                int id = res.getIdentifier(high + suit, "drawable", packageName);
                myImageList.add(id);
            }

        }
        for (String joker : jokers) {
            int id = res.getIdentifier(joker, "drawable", packageName);
            myImageList.add(id);
        }

        int randomNumber = random.nextInt(myImageList.size());
        slapDeck.setImageResource(myImageList.get(randomNumber));

    }

    public void Reset(View view) {
        userScore = 0;
        computerScore = 0;
        slapDeck.setImageResource(R.drawable.backfaceblue);
        score.setText("Your Score: " + userScore + " Computer Score: " + computerScore);
    }


    public void computerTurn() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slapDeck = (ImageView) findViewById(R.id.slapDeck);
        score = (TextView) findViewById(R.id.score);

        resetButton = (Button) findViewById(R.id.resetButton);
        startButton = (Button) findViewById(R.id.startButton);
        jackButton = (Button) findViewById(R.id.jackButton);

    }

}

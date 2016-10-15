package com.example.slapjack;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    ArrayList<Integer> myImageList = new ArrayList<>();
    Handler timerHandler = new Handler();
    Handler computerHandler = new Handler();
    int tempCount = 0;
    int computerSpeed = random.nextInt(2000)+1;
    int jackId;
    int j=0;
    int allJackIds [] = new int[10];



    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {  //this is the dealer, she deals one card at a time, each card is 1 second fast

            int randomNumber = random.nextInt(myImageList.size());
            slapDeck.setImageResource(myImageList.get(randomNumber));

            for (int i = 0; i<myImageList.size(); i++) {

                if (i == randomNumber) {

                    if(onJack(slapDeck)){
                        Log.d("in if stat:" , "here");
                        //computerHandler.postDelayed(runner, computerSpeed);
                        tempCount++;
                        myImageList.remove(i);
                        Log.d("check array: ", ""+myImageList.size());
                        Log.d("check array: ", ""+tempCount);
                    } else {
                        myImageList.remove(i);
                        tempCount++;
                        Log.d("check array: ", ""+myImageList.size());
                        Log.d("check variable: ", ""+tempCount);
                    }

                }
            }
            if(myImageList.size()==0){


                score.setText("Your Score: " + userScore + " Computer Score: " + computerScore);
                if(userScore>computerScore){
                    //display user own
                }else{
                    //display computer won
                }
                userScore = 0;
                computerScore = 0;
                slapDeck.setImageResource(R.drawable.backfaceblue);

            }else {
                timerHandler.postDelayed(timerRunnable, 3000);
            }
        }

    };

    public void Start(View view) {

        SelectCards();
        jackButton.setEnabled(false);

    }

    public void SelectCards() { //Stores the cards in an arraylist so they can be selected to randomly appear/flip through



        //myImageList.remove(randomNumber);
        Log.d("check array: ", ""+myImageList.size());

        //for(int i=0; i< myImageList.size(); i++) {
        timerHandler.postDelayed(timerRunnable, 3000);
        //}
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
        slapDeck.setImageResource(R.drawable.backfaceblue);
        score = (TextView) findViewById(R.id.score);

        resetButton = (Button) findViewById(R.id.resetButton);
        startButton = (Button) findViewById(R.id.startButton);
        jackButton = (Button) findViewById(R.id.jackButton);

        Resources res = getResources();
        String packageName = getPackageName();
        String[] highs = {"ace", "jack", "king", "queen"};
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
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
                if (high.equals("jack")){
                    allJackIds[j] +=jackId;
                     j++;
                }

            }

        }
        for (String joker : jokers) {
            int id = res.getIdentifier(joker, "drawable", packageName);
            myImageList.add(id);
        }
        for(int k =0; k < allJackIds.length; k++){
            Log.d("all Jids", ""+allJackIds[k]);
        }
    }




    /**
     * Handler for the "Jack" button.
     * the user or computer sees the jack.
     *
     * @param view
     * @return true
     */
    public boolean onJack(View view) {
        // text.getText();
        TextView gameStatus = (TextView) findViewById(R.id.startButton);
        //ImageView currentImage = (ImageView) findViewById(R.id.slapDeck);

        Log.d("current image ",""+slapDeck.getId());
        Log.d("jack ids ", ""+jackId);

            if(slapDeck.getId()== jackId){
                jackButton.setEnabled(true);
            }

//        if (myImageList.contains(currentImage.getId())){
//            jackButton.setEnabled(true);
//        }

        return true;
    }

}
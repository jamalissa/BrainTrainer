package com.example.Jamal.braintrainer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity {
  //defining the variables that we need
    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView multiplyTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    int score = 0;
    int numberOfQuestions = 0;
        // to start and restart the game and the timer
    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("Time");
        pointsTextView.setText("Score");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {

                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("your IQ is lower than the normal.we advice you to get some help" );

            }
        }.start();


    }
        //to generate the questions and the 4 incorrect answers
    public void generateQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(300);
        int b = rand.nextInt(300);

        multiplyTextView.setText(Integer.toString(a) + " * " + Integer.toString(b));

        answers.clear();

        int incorrectAnswer;

        for (int i=0; i<4; i++) {

                incorrectAnswer = rand.nextInt(9000);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(9000);

                }

                answers.add(incorrectAnswer);

        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));



    }
     //comments show after the users answers
    public void chooseAnswer(View view) {
        String[] myArray= new String[6];
        myArray[0] = "Loser!";
        myArray[1] = "Concentrate!";
        myArray[2] = "Elementary school level";
        myArray[3] = "almost correct!";
        myArray[4] = "if this was an exam,your degree would be 5.0";
        myArray[5] = "kindergarten Level";

        Random rand = new Random();
        int x = rand.nextInt(6);
        score--;
        resultTextView.setText(myArray[x]);
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();

    }

    public void start(View view) {
          //to make the questions interface visible
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);

        playAgain(findViewById(R.id.playAgainButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        multiplyTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

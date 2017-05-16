package hu.gearxpert.basichungariancourse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static hu.gearxpert.basichungariancourse.R.id.evaluation;

public class Main3Activity extends AppCompatActivity {

    Datastorage _Data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        _Data = (Datastorage) intent.getSerializableExtra("dataobject");

        TextView scoreView = (TextView) findViewById(R.id.score);
        scoreView.setText("Your score is " + _Data.score + " points out of 8");

        TextView evaluationView = (TextView) findViewById(evaluation);

        if (_Data.score <= 2) {
           evaluationView.setText(R.string.evaluationBad);
        } else if (_Data.score <= 4) {
            evaluationView.setText(R.string.evaluationNotBad);
        } else if (_Data.score <= 6) {
            evaluationView.setText(R.string.evaluationReallyGood);
        } else {
            evaluationView.setText(R.string.evaluationExcellentJob);
        }

    }

    /**
     * Toast message for redirection to start page, with a little delay
     */

    public void wantNewGame (View view) {
        Toast.makeText(this, R.string.redirect1, Toast.LENGTH_LONG).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(Main3Activity.this, MainActivity.class);
            startActivity(intent);
        }
    }, 3500);
    }

    /**
     * If back button is pressed, it redirects the user to start page, and game can start again
     */
    @Override
    public void onBackPressed() {
        Toast.makeText(this, R.string.redirect2, Toast.LENGTH_LONG).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 3500);

    }

    public void seeCorrectAnswers(View view)
    {
        Intent myIntent = new Intent(this, Main2Activity.class);
        myIntent.putExtra("_dataobject", _Data);
        startActivity(myIntent);
    }

}

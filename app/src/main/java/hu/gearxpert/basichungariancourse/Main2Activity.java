package hu.gearxpert.basichungariancourse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    Datastorage Data = new Datastorage();
    Datastorage dataFromThirdScreen = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Reloads the Datadtorage object from Main3Activity with the saved data
        Intent intent = getIntent();
        dataFromThirdScreen = (Datastorage) intent.getSerializableExtra("_dataobject");
            if (dataFromThirdScreen != null) {
                showCorrectAnswers();

                //disables the possible clicks after the user finished the test and checks the results
                findViewById(R.id.radio_q1a1).setEnabled(false);
                findViewById(R.id.radio_q1a2).setEnabled(false);
                findViewById(R.id.radio_q1a3).setEnabled(false);
                findViewById(R.id.radio_q2a1).setEnabled(false);
                findViewById(R.id.radio_q2a2).setEnabled(false);
                findViewById(R.id.radio_q2a3).setEnabled(false);
                findViewById(R.id.radio_q3a1).setEnabled(false);
                findViewById(R.id.radio_q3a2).setEnabled(false);
                findViewById(R.id.radio_q3a3).setEnabled(false);
                findViewById(R.id.cb1).setEnabled(false);
                findViewById(R.id.cb2).setEnabled(false);
                findViewById(R.id.cb3).setEnabled(false);
                findViewById(R.id.cb4).setEnabled(false);
                findViewById(R.id.editText1).setEnabled(false);
                findViewById(R.id.editText2).setEnabled(false);
                findViewById(R.id.editText3).setEnabled(false);

                //bottom Buttun is disabled
                findViewById(R.id.checkMyScore).setVisibility(View.GONE);
                //2 new Buttons enabled instead of the old one
                findViewById(R.id.startNewGame).setVisibility(View.VISIBLE);
                findViewById(R.id.exit).setVisibility(View.VISIBLE);

            }

            if (dataFromThirdScreen == null) {

                //at the beginning the 2 bottom Buttons are disabled, they will be used only at the checking
                findViewById(R.id.startNewGame).setVisibility(View.INVISIBLE);
                findViewById(R.id.exit).setVisibility(View.INVISIBLE);
            }

    }

    /**We make an intent so Main3Activity can get the final score */
    /** Called when the user clicks the Let's see my result! button */

      public void clickedButtonScore (View view) {
          radioButtonScore();
          checkBoxScore();
          editTextScore();
          Data.scoreCalculation();
          Intent myIntent = new Intent(this, Main3Activity.class);
          myIntent.putExtra("dataobject", Data);
          startActivity(myIntent);
      }

    public void radioButtonScore()
    {

        /**
         * Check what th euser has choosen, and saves it as int
         */

        // 1st RadioGroup
        if ( ((RadioButton) findViewById(R.id.radio_q1a1)).isChecked() ) {
            Data.q1selected = R.id.radio_q1a1;
        }
        else if ( ((RadioButton) findViewById(R.id.radio_q1a2)).isChecked() ) {
            Data.q1selected = R.id.radio_q1a2;
        }
        else if ( ((RadioButton) findViewById(R.id.radio_q1a3)).isChecked() ) {
            Data.q1selected = R.id.radio_q1a3;
        }
        else {
            Data.q1selected = 2;
        }

        //2nd RadioGroup

        if ( ((RadioButton) findViewById(R.id.radio_q2a1)).isChecked() ) {
            Data.q2selected = R.id.radio_q2a1;
        }
        else if ( ((RadioButton) findViewById(R.id.radio_q2a2)).isChecked() ) {
            Data.q2selected = R.id.radio_q2a2;
        }
        else if  ( ((RadioButton) findViewById(R.id.radio_q2a3)).isChecked() ) {
            Data.q2selected = R.id.radio_q2a3;
        }
        else {
            Data.q2selected = 2;
        }

        //3rd RadioGroup

        if ( ((RadioButton) findViewById(R.id.radio_q3a1)).isChecked() ) {
            Data.q3selected = R.id.radio_q3a1;
        }
        else if ( ((RadioButton) findViewById(R.id.radio_q3a2)).isChecked() ) {
            Data.q3selected = R.id.radio_q3a2;
        }
        else if ( ((RadioButton) findViewById(R.id.radio_q3a3)).isChecked() ) {
            Data.q3selected = R.id.radio_q3a3;
        }
        else {
            Data.q3selected = 2;
        }

        //checks if the correct answer was selected, and if it was the correct one, the variables are set to 1

        RadioButton radio_q1_correct = (RadioButton) findViewById(R.id.radio_q1a2);
        if ( radio_q1_correct.isChecked() )
        {
            Data.answer1 = 1;
        }

        RadioButton radio_q2_correct = (RadioButton) findViewById(R.id.radio_q2a1);
        if ( radio_q2_correct.isChecked() )
        {
            Data.answer2 = 1;
        }

        RadioButton radio_q3_correct = (RadioButton) findViewById(R.id.radio_q3a2);
        if ( radio_q3_correct.isChecked() )
        {
            Data.answer3 = 1;
        }

    }

    //checks which boxes were marked by the user. if the correct one was marked too, it sets its variable to 1

    public void checkBoxScore () {

        CheckBox CheckBox1 = (CheckBox) findViewById(R.id.cb1);
        boolean cb1 = CheckBox1.isChecked();
        Data.cb1selected = cb1;

        CheckBox CheckBox2 = (CheckBox) findViewById(R.id.cb2);
        boolean cb2 = CheckBox2.isChecked();
        Data.cb2selected = cb2;

        CheckBox CheckBox3 = (CheckBox) findViewById(R.id.cb3);
        boolean cb3 = CheckBox3.isChecked();
        Data.cb3selected = cb3;

        CheckBox CheckBox4 = (CheckBox) findViewById(R.id.cb4);
        boolean cb4 = CheckBox4.isChecked();
        Data.cb4selected = cb4;

        if ((cb2 == true) && (cb3 == true) && (cb1 == false) && (cb4 == false)) {
            Data.check = 1;
        }

    }

    //saves the text that was entered by the user into variables, and if the answer is correct, the variable is set to 1

    public void editTextScore () {
        EditText editTextBox1 = (EditText) findViewById(R.id.editText1);
        String editTextValue1 = editTextBox1.getText().toString();
        Data.editBox1 = editTextValue1;
        if (editTextValue1.equalsIgnoreCase(getString(R.string.in_the_house))) {
            Data.edit1 = 1;
        }

        EditText editTextBox2 = (EditText) findViewById(R.id.editText2);
        String editTextValue2 = editTextBox2.getText().toString();
        Data.editBox2 = editTextValue2;
        if (editTextValue2.equalsIgnoreCase(getString(R.string.from_the_forest))) {
            Data.edit2 = 1;
        }

        EditText editTextBox3 = (EditText) findViewById(R.id.editText3);
        String editTextValue3 = editTextBox3.getText().toString();
        Data.editBox3 = editTextValue3;
        if (editTextValue3.equalsIgnoreCase(getString(R.string.into_the_city))) {
            Data.edit3 = 1;
        }
    }

    public void showCorrectAnswers() {

        //changes the color depending on the user's correct/incorrect answers

        //RadioGroup1
        if (dataFromThirdScreen.q1selected == 2) {
            ((RadioButton) findViewById(R.id.radio_q1a2)).setTextColor(getResources().getColor(R.color.myGreen));
        }
        else if (dataFromThirdScreen.q1selected == R.id.radio_q1a2) {
            ((RadioButton) findViewById(R.id.radio_q1a2)).setChecked(true);
            ((RadioButton) findViewById(R.id.radio_q1a2)).setTextColor(getResources().getColor(R.color.myGreen));
        }
        else {
            ((RadioButton) findViewById(dataFromThirdScreen.q1selected)).setTextColor(Color.RED);
            ((RadioButton) findViewById(R.id.radio_q1a2)).setTextColor(getResources().getColor(R.color.myGreen));
            ((RadioButton) findViewById(dataFromThirdScreen.q1selected)).setChecked(true);
        }

        //RadioGroup2
        if (dataFromThirdScreen.q2selected == 2) {
            ((RadioButton) findViewById(R.id.radio_q2a1)).setTextColor(getResources().getColor(R.color.myGreen));
        }
        else if (dataFromThirdScreen.q2selected == R.id.radio_q2a1) {
            ((RadioButton) findViewById(R.id.radio_q2a1)).setChecked(true);
            ((RadioButton) findViewById(R.id.radio_q2a1)).setTextColor(getResources().getColor(R.color.myGreen));
        }
        else {
            ((RadioButton) findViewById(dataFromThirdScreen.q2selected)).setTextColor(Color.RED);
            ((RadioButton) findViewById(R.id.radio_q2a1)).setTextColor(getResources().getColor(R.color.myGreen));
            ((RadioButton) findViewById(dataFromThirdScreen.q2selected)).setChecked(true);
        }

        //RadioGroup3
        if (dataFromThirdScreen.q3selected == 2) {
            ((RadioButton) findViewById(R.id.radio_q3a2)).setTextColor(getResources().getColor(R.color.myGreen));
        }
        else if (dataFromThirdScreen.q3selected == R.id.radio_q3a2) {
            ((RadioButton) findViewById(R.id.radio_q3a2)).setChecked(true);
            ((RadioButton) findViewById(R.id.radio_q3a2)).setTextColor(getResources().getColor(R.color.myGreen));
        }
        else {
            ((RadioButton) findViewById(dataFromThirdScreen.q3selected)).setTextColor(Color.RED);
            ((RadioButton) findViewById(R.id.radio_q3a2)).setTextColor(getResources().getColor(R.color.myGreen));
            ((RadioButton) findViewById(dataFromThirdScreen.q3selected)).setChecked(true);
        }

        //CheckBoxes
        if (dataFromThirdScreen.cb1selected == true) {
            ((CheckBox) findViewById(R.id.cb1)).setChecked(true);
            ((CheckBox) findViewById(R.id.cb1)).setTextColor(Color.RED);
        }

        if (dataFromThirdScreen.cb2selected == true) {
            ((CheckBox) findViewById(R.id.cb2)).setChecked(true);
            ((CheckBox) findViewById(R.id.cb2)).setTextColor(getResources().getColor(R.color.myGreen));
        } else {
            ((CheckBox) findViewById(R.id.cb2)).setTextColor(getResources().getColor(R.color.myGreen));
        }

        if (dataFromThirdScreen.cb3selected == true) {
            ((CheckBox) findViewById(R.id.cb3)).setChecked(true);
            ((CheckBox) findViewById(R.id.cb3)).setTextColor(getResources().getColor(R.color.myGreen));
        } else {
            ((CheckBox) findViewById(R.id.cb3)).setTextColor(getResources().getColor(R.color.myGreen));
        }

        if (dataFromThirdScreen.cb4selected == true) {
            ((CheckBox) findViewById(R.id.cb4)).setChecked(true);
            ((CheckBox) findViewById(R.id.cb4)).setTextColor(Color.RED);
        }

        //EditBoxes
        if (dataFromThirdScreen.editBox1.equals("in the house")) {
            ((EditText) findViewById(R.id.editText1)).setTextColor(getResources().getColor(R.color.myGreen));
            ((EditText) findViewById(R.id.editText1)).setText("in the house");
        }
        else {
            EditText incorrect1 = (EditText) findViewById(R.id.editText1);
            String wrong1 = getColoredSpanned(dataFromThirdScreen.editBox1, "#FF3D00");
            String correct1 = getColoredSpanned("in the house","#0b7429");
            incorrect1.setText(Html.fromHtml(wrong1 + " >> " + correct1));
        }

        if (dataFromThirdScreen.editBox2.equals("from the forest")) {
            ((EditText) findViewById(R.id.editText2)).setTextColor(getResources().getColor(R.color.myGreen));
            ((EditText) findViewById(R.id.editText2)).setText("from the forest");
        }
        else {
            EditText incorrect2 = (EditText) findViewById(R.id.editText2);
            String wrong2 = getColoredSpanned(dataFromThirdScreen.editBox2, "#FF3D00");
            String correct2 = getColoredSpanned("from the forest","#0b7429");
            incorrect2.setText(Html.fromHtml(wrong2 + " >> " + correct2));
        }

        if (dataFromThirdScreen.editBox3.equals("into the city")) {
            ((EditText) findViewById(R.id.editText3)).setTextColor(getResources().getColor(R.color.myGreen));
            ((EditText) findViewById(R.id.editText3)).setText("into the city");
        }
        else {
            EditText incorrect3 = (EditText) findViewById(R.id.editText3);
            String wrong3 = getColoredSpanned(dataFromThirdScreen.editBox3, "#FF3D00");
            String correct3 = getColoredSpanned("into the city", "#0b7429");
            incorrect3.setText(Html.fromHtml(wrong3 + " >> " + correct3));
        }

    }

    //colors needed at EditText, if the answer is wrong
    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    //new game button >> appears only after final check
    public void wantNewGame (View view) {
        Toast.makeText(this, R.string.redirect1, Toast.LENGTH_LONG).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 3500);
    }

    //exit
    public void exit (View view) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

}

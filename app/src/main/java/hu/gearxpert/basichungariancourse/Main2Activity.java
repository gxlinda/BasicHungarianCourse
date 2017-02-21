package hu.gearxpert.basichungariancourse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
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

        //Visszatöltjük a Main3Activityből a Datastorage objektumunkat az összes mentett adattal
        Intent intent = getIntent();
        dataFromThirdScreen = (Datastorage) intent.getSerializableExtra("_dataobject");
            if (dataFromThirdScreen != null) {
                showCorrectAnswers();

                //letiltjük a kattogtatás lehetőségét
                ((RadioButton) findViewById(R.id.radio_q1a1)).setEnabled(false);
                ((RadioButton) findViewById(R.id.radio_q1a2)).setEnabled(false);
                ((RadioButton) findViewById(R.id.radio_q1a3)).setEnabled(false);
                ((RadioButton) findViewById(R.id.radio_q2a1)).setEnabled(false);
                ((RadioButton) findViewById(R.id.radio_q2a2)).setEnabled(false);
                ((RadioButton) findViewById(R.id.radio_q2a3)).setEnabled(false);
                ((RadioButton) findViewById(R.id.radio_q3a1)).setEnabled(false);
                ((RadioButton) findViewById(R.id.radio_q3a2)).setEnabled(false);
                ((RadioButton) findViewById(R.id.radio_q3a3)).setEnabled(false);
                ((CheckBox) findViewById(R.id.cb1)).setEnabled(false);
                ((CheckBox) findViewById(R.id.cb2)).setEnabled(false);
                ((CheckBox) findViewById(R.id.cb3)).setEnabled(false);
                ((CheckBox) findViewById(R.id.cb4)).setEnabled(false);
                ((EditText) findViewById(R.id.editText1)).setEnabled(false);
                ((EditText) findViewById(R.id.editText2)).setEnabled(false);
                ((EditText) findViewById(R.id.editText3)).setEnabled(false);

                //letiltjuk az alsó Buttont
                ((Button) findViewById(R.id.checkMyScore)).setVisibility(View.GONE);
                //megjelenítjük a két új Buttont
                ((Button) findViewById(R.id.startNewGame)).setVisibility(View.VISIBLE);
                ((Button) findViewById(R.id.exit)).setVisibility(View.VISIBLE);

            }

            if (dataFromThirdScreen == null) {

                //kezdéskor letiltjük az ellenőrzéskori két alsó gombot
                ((Button) findViewById(R.id.startNewGame)).setVisibility(View.INVISIBLE);
                ((Button) findViewById(R.id.exit)).setVisibility(View.INVISIBLE);
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
         * Megnézzük, mit jelölt be a felhasználó, és eltároljuk int-ként
         */

        // első RadioGroup
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

        //második RadioGroup

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

        //harmadik RadioGroup

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

        //megnézzük, hogy a helyeseket jelölte-e, és ha igen, akkor a változók értékét átállítjuk 1-re

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

    //Megnézzük, hogy a 4 CheckBox közül melyiket jelölte be. Ha a helyeset is, akkor a változók értékét átállítjuk 1-re

    public void checkBoxScore () {

        CheckBox CheckBox1 = (CheckBox) findViewById(R.id.cb1);
        boolean cb1 = CheckBox1.isChecked();
        Data.cb1selected = cb1;

        CheckBox CheckBox2 = (CheckBox) findViewById(R.id.cb2);
        boolean cb2 = CheckBox2.isChecked();
        Data.cb2selected = cb2;
        if (cb2) {
            Data.check2 = 1;
        }

        CheckBox CheckBox3 = (CheckBox) findViewById(R.id.cb3);
        boolean cb3 = CheckBox3.isChecked();
        Data.cb3selected = cb3;
        if (cb3) {
            Data.check3 = 1;
        }

        CheckBox CheckBox4 = (CheckBox) findViewById(R.id.cb4);
        boolean cb4 = CheckBox4.isChecked();
        Data.cb4selected = cb4;

    }

    //Elmentjük változókba a felhasználó által beírt szöveget, valamint ha a válasz helyes, a változók értékét átállítjuk 1-re

    public void editTextScore () {
        EditText editTextBox1 = (EditText) findViewById(R.id.editText1);
        String editTextValue1 = editTextBox1.getText().toString();
        Data.editBox1 = editTextValue1;
        if (editTextValue1.equals("in the house")) {
            Data.edit1 = 1;
        }

        EditText editTextBox2 = (EditText) findViewById(R.id.editText2);
        String editTextValue2 = editTextBox2.getText().toString();
        Data.editBox2 = editTextValue2;
        if (editTextValue2.equals("from the forest")) {
            Data.edit2 = 1;
        }

        EditText editTextBox3 = (EditText) findViewById(R.id.editText3);
        String editTextValue3 = editTextBox3.getText().toString();
        Data.editBox3 = editTextValue3;
        if (editTextValue3.equals("into the city")) {
            Data.edit3 = 1;
        }
    }

    public void showCorrectAnswers() {

        //átírjuk a színeket, ha a válasz helyes illetve helytelen

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
        //CheckBoxok
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
        //EditBoxok
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

    //kétféle szín kell EditTextnél, ha helytelen a válasz
    private String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }

    //csak ellenőrzéskor megjelenő gomb
    public void wantNewGame (View view) {
        Toast.makeText(this, "You will be redirected to start page!", Toast.LENGTH_LONG).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 3500);
    }
    //csak ellenőrzéskor megjelenő gomb, kilépés
    public void exit (View view) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory( Intent.CATEGORY_HOME );
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);
    }

}

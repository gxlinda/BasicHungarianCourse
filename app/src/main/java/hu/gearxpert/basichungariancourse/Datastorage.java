package hu.gearxpert.basichungariancourse;

import java.io.Serializable;

/**
 * Created by melinda.kostenszki on 2017.02.20.
 */

public class Datastorage implements Serializable {

    public int score = 0;
    public int answer1 = 0;
    public int answer2 = 0;
    public int answer3 = 0;
    public int check2 = 0;
    public int check3 = 0;
    public int edit1 = 0;
    public int edit2 = 0;
    public int edit3 = 0;
    public int q1selected = 0;
    public int q2selected = 0;
    public int q3selected = 0;
    public boolean cb1selected = false;
    public boolean cb2selected = false;
    public boolean cb3selected = false;
    public boolean cb4selected = false;
    public String editBox1 = "";
    public String editBox2 = "";
    public String editBox3 = "";

    public void scoreCalculation() {
        score = answer1 + answer2 + answer3 + check2 + check3 + edit1 + edit2 + edit3;
    }




}

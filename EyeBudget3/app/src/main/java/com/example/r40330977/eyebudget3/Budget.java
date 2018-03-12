package com.example.r40330977.eyebudget3;

import com.jjoe64.graphview.series.DataPoint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Roddy on 04/03/2018.
 */

public class Budget {

    //variables
    int _balance;
    int _bid;
    //empty constructor
    public Budget (){}

    public Budget(int _balance, int _bid){
        this._bid = _bid;
        this._balance = _balance;
    }

    //get sets
    public int get_bid() {return _bid;}
    public int get_balance() {return _balance;}

    public void set_bid(int _bid) {this._bid = _bid;}
    public void set_balance(int _balance) {this._balance = _balance;}

    public String btoString() {

        return _bid + " - " + _balance;//returns string
    }

    public String ciaString() {
        return "You have " + _balance + "pounds left";
    }//returns toast amount


}

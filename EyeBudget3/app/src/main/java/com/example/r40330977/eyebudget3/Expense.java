package com.example.r40330977.eyebudget3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Roddy on 04/03/2018.
 * Expense Class
 */

public class Expense {


    //variables
    int _id;
    String _type;
    int _amount;
    String _description;
    String _when;

    //empty constructor
    public Expense (){}

    public Expense(int _id, String _type, int _amount, String _description, String _when){
        this._id = _id;
        this._type = _type;
        this._amount=_amount;
        this._description=_description;
        this._when=_when;
    }

    public int get_id() {return _id;}
    public String get_type() {return _type;}
    public int get_amount() {return _amount;}
    public String get_description() {return _description;}
    public String get_when() {return _when;}

    public void set_id(int _id) {this._id = _id;}
    public void set_type(String _type) {this._type = _type;}
    public void set_amount(int _amount) {this._amount = _amount;}
    public void set_description(String _description) {this._description = _description;}
    public void set_when(String _when) {this._when = _when;}

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        try{
            date = format.parse(_when);
        }
        catch(ParseException e) {
            e.printStackTrace();
        }
        return _id + " - " + _type + " - " + _amount + " pounds - " + _description + " - " + date;//Expense data to string
    }
}

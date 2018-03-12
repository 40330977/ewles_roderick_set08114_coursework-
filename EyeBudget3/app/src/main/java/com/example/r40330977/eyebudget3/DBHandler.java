package com.example.r40330977.eyebudget3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jjoe64.graphview.series.DataPoint;

import java.util.LinkedList;
import java.util.List;

import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Roddy on 04/03/2018.
 */

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;

    private static final String DATABASE_NAME = "eyebudget";

    //table names
    private static final String TABLE_EXPENSES = "expensesDetails";
    private static final String TABLE_BUDGET = "budgetDetails";

    //expenses table columns
    private static final String KEY_ID = "id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_LOGTIME = "logtime";
    private static final String[] COLUMNS = {KEY_ID, KEY_TYPE, KEY_AMOUNT, KEY_DESCRIPTION, KEY_LOGTIME};

    //budget table columns
    private static final String KEY_BID = "bid";
    private static final String KEY_BALANCE = "balance";
    private static final String[] BCOLUMNS = {KEY_BID, KEY_BALANCE};


    //expenses create statement
    private static final String CREATE_EXPENSES_TABLE = "CREATE TABLE " + TABLE_EXPENSES + " ("
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_TYPE + " TEXT, "
            + KEY_AMOUNT + " INTEGER, "
            + KEY_DESCRIPTION + " TEXT, "
            + KEY_LOGTIME + " TEXT " + ")";

    //budget create statement
    private static final String CREATE_BUDGET_TABLE = "CREATE TABLE " + TABLE_BUDGET + " ("
            + KEY_BID + " INTEGER PRIMARY KEY,"
            + KEY_BALANCE + " INTEGER " + ")";

    public DBHandler(Context contex) {super(contex, DATABASE_NAME,null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create required tables
        db.execSQL(CREATE_BUDGET_TABLE);
        db.execSQL(CREATE_EXPENSES_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGET);

        onCreate(db);
    }

    //add an expense
    public void addExpense(Expense expense){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, expense.get_id());
        values.put(KEY_TYPE, expense.get_type());
        values.put(KEY_AMOUNT, expense.get_amount());
        values.put(KEY_DESCRIPTION, expense.get_description());
        values.put(KEY_LOGTIME, expense.get_when());

        //insert the row
        db.insert(TABLE_EXPENSES, null, values);
        db.close();

    }

    public Expense getExpense(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_EXPENSES, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Expense expense = new Expense();
        expense.set_id(Integer.parseInt(cursor.getString(0)));
        expense.set_type(cursor.getString(1));
        expense.set_amount(Integer.parseInt(cursor.getString(2)));
        expense.set_description(cursor.getString(3));
        expense.set_when(cursor.getString(4));

        return expense;
    }

    //list expenses
    public List<Expense> allExpenses() {

        List<Expense> expenses = new LinkedList<Expense>();
        String query = "SELECT  * FROM " + TABLE_EXPENSES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Expense expense = null;

        if (cursor.moveToFirst()) {
            do {
                expense = new Expense();
                expense.set_id(Integer.parseInt(cursor.getString(0)));
                expense.set_type(cursor.getString(1));
                expense.set_amount(Integer.parseInt(cursor.getString(2)));
                expense.set_description(cursor.getString(3));
                expense.set_when(cursor.getString(4));
                //add
                expenses.add(expense);
            } while (cursor.moveToNext());
        }

        return expenses;
    }

    public int updateExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, expense.get_id());
        values.put(KEY_TYPE, expense.get_type());
        values.put(KEY_AMOUNT, expense.get_amount());
        values.put(KEY_DESCRIPTION, expense.get_description());
        values.put(KEY_LOGTIME, expense.get_when());

        int i = db.update(TABLE_EXPENSES, // table
                values, // column/value
                "id = ?", // selections
                new String[]{String.valueOf(expense.get_id())});

        db.close();

        return i;
    }

    public void deleteOne(Expense expense) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXPENSES, "id = ?",
                new String[] { String.valueOf(expense.get_id()) });
        db.close();

    }

    //add a budget
    public void addBudget(Budget budget){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BID, budget.get_bid());
        values.put(KEY_BALANCE, budget.get_balance());


        //insert the row
        db.insert(TABLE_BUDGET, null, values);
        db.close();}

    public List<Budget> allBudget() {

        List<Budget> budgets = new LinkedList<Budget>();
        String query = "SELECT  * FROM " + TABLE_BUDGET;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Budget budget = null;

        if (cursor.moveToFirst()) {
            do {
                budget = new Budget();
                budget.set_bid(Integer.parseInt(cursor.getString(0)));
                budget.set_balance(Integer.parseInt(cursor.getString(1)));

                //add
                budgets.add(budget);
            } while (cursor.moveToNext());
        }

        return budgets;
    }

    public Budget getBudget(int bid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_BUDGET, // a. table
                BCOLUMNS, // b. column names
                " bid = ?", // c. selections
                new String[]{String.valueOf(bid)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Budget budget = new Budget();
        budget.set_bid(Integer.parseInt(cursor.getString(0)));
        budget.set_balance(Integer.parseInt(cursor.getString(1)));


        return budget;
    }

    public Budget getLastBudget() {
        String selectQuery= "SELECT * FROM " + TABLE_BUDGET +" ORDER BY " + KEY_BID + " DESC LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Budget budget = null;

        if(cursor.moveToFirst())
            budget = new Budget();
        budget.set_bid(Integer.parseInt(cursor.getString( cursor.getColumnIndex(KEY_BID))));
        budget.set_balance(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_BALANCE))));

        cursor.close();
        return budget;
    }

    public Expense getLastExpense() {
        String selectQuery= "SELECT * FROM " + TABLE_EXPENSES +" ORDER BY " + KEY_ID + " DESC LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Expense expense = null;

        if(cursor.moveToFirst())
            expense = new Expense();

        expense.set_id(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID))));
        expense.set_amount(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_AMOUNT))));
        expense.set_description(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
        expense.set_when(cursor.getString(cursor.getColumnIndex(KEY_LOGTIME)));

        expense.set_type(cursor.getString(cursor.getColumnIndex(KEY_TYPE)));

        cursor.close();
        return expense;
    }

    public int updateBudget(Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BID, budget.get_bid());
        values.put(KEY_BALANCE, budget.get_balance());


        int i = db.update(TABLE_BUDGET, // table
                values, // column/value
                "id = ?", // selections
                new String[]{String.valueOf(budget.get_bid())});

        db.close();

        return i;
    }

    public void deleteOneb(Budget budget) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BUDGET, "bid = ?",
                new String[]{String.valueOf(budget.get_bid())});
        db.close();

    }

    public String[] getAnalysisData(){
        String query = "SELECT  * FROM " + TABLE_BUDGET;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String[] balances = new String[cursor.getCount()];
        int i = 0;

        while(cursor.moveToNext()){
            String d = cursor.getString(cursor.getColumnIndex(KEY_BALANCE));
            balances[i] = d;
            i++;
        }

        return  balances;
    }
    public DataPoint[] generateData(String[] balances) {
        int count = balances.length;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = i;

            double y = Integer.parseInt(balances[i]);
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;}

    public String[] getEAnalysisData(){
        String query = "SELECT  * FROM " + TABLE_EXPENSES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String[] expenses = new String[cursor.getCount()];
        int i = 0;

        while(cursor.moveToNext()){
            String d = cursor.getString(cursor.getColumnIndex(KEY_AMOUNT));
            expenses[i] = d;
            i++;
        }

        return  expenses;
    }
    public DataPoint[] generateEData(String[] expenses) {
        int count = expenses.length;
        DataPoint[] values = new DataPoint[count];
        for (int i=0; i<count; i++) {
            double x = i;

            double y = Integer.parseInt(expenses[i]);
            DataPoint v = new DataPoint(x, y);
            values[i] = v;
        }
        return values;}
}

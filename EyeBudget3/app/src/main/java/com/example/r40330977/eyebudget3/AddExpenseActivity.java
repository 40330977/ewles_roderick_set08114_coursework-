package com.example.r40330977.eyebudget3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;


public class AddExpenseActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        final DBHandler db = new DBHandler(this);
        final Budget budget =  db.getLastBudget();
        final Expense expense = db.getLastExpense();

        Button btn = (Button) findViewById(R.id.buttonSMB);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = (EditText) findViewById(R.id.editTextSMA);
                final EditText des = (EditText) findViewById(R.id.editTextSMD);
                final EditText type = (EditText) findViewById(R.id.editTextSMT);
                int neManager = expense.get_id() +1;
                final Expense newExpense = new Expense();
                newExpense.set_amount(Integer.parseInt(input.getText().toString()));
                newExpense.set_id(neManager);
                newExpense.set_description((des.getText().toString()));
                newExpense.set_when(new Date().toString());
                newExpense.set_type(type.getText().toString());




                db.addExpense(newExpense);

                int bidManager = budget.get_bid() +1;
                int inToBal = budget.get_balance() - Integer.parseInt(input.getText().toString());
                Budget newBudget = new Budget();
                newBudget.set_bid(bidManager);
                newBudget.set_balance(inToBal);
                
                db.addBudget(newBudget);

                Intent SMB = new Intent(AddExpenseActivity.this, MainActivity.class);
                startActivity(SMB);//change intent back to main activity




            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_expense, menu);
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

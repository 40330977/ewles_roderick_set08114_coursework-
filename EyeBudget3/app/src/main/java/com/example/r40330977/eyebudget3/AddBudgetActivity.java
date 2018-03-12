package com.example.r40330977.eyebudget3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddBudgetActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);
        final DBHandler db = new DBHandler(this);
        final Budget budget =  db.getLastBudget();


        Button btn = (Button) findViewById(R.id.buttonAB);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText input = (EditText) findViewById(R.id.editTextAB);
                //input.getText().toString();
                int inToBal = budget.get_balance() + Integer.parseInt(input.getText().toString());
                int bidManager = budget.get_bid() +1;
                Budget newBudget = new Budget();
                newBudget.set_bid(bidManager);
                newBudget.set_balance(inToBal);

                db.addBudget(newBudget);

                Intent ABAB = new Intent(AddBudgetActivity.this, MainActivity.class);
                startActivity(ABAB);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_budget, menu);
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

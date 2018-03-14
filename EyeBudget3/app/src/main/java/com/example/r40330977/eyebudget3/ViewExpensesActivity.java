package com.example.r40330977.eyebudget3;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;


public class ViewExpensesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        DBHandler db = new DBHandler(this);

        List<Expense> expenses = db.allExpenses();

        if (expenses != null) {
            String[] itemsNames = new String[expenses.size()];

            for (int i = 0; i < expenses.size(); i++) {
                itemsNames[i] = expenses.get(i).toString();//get data
            }

            // display in list
            ListView list = (ListView) findViewById(R.id.list);
            list.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, itemsNames));

        }

        Button btn = (Button) findViewById(R.id.buttonveb);//back button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent VEAB = new Intent(ViewExpensesActivity.this, MainActivity.class);
                startActivity(VEAB);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_expenses, menu);
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

package com.example.r40330977.eyebudget3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.List;
import com.example.r40330977.eyebudget3.DBHandler;
import com.example.r40330977.eyebudget3.Budget;
import com.example.r40330977.eyebudget3.Expense;
import java.text.DateFormat;
import java.util.Date;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHandler db = new DBHandler(this);
        String timeStamp = new SimpleDateFormat(" yyyyMMdd_HHmmss "). format (new Date ());

        //demo expense entries
        Expense expense1 = new Expense(1, "Food", 5, "gregs", timeStamp);
        Expense expense2 = new Expense(2, "Bill", 30, "heating", timeStamp);
        Expense expense3 = new Expense(3, "luxury", 20,"netflix", timeStamp);

        //innitial budget
        Budget budget1 = new Budget(0,1);

        //add demo expenses
        db.addExpense(expense1);
        db.addExpense(expense2);
        db.addExpense(expense3);

        //add innitial budget
        db.addBudget(budget1);
        //list expenses
        List<Expense> expenses = db.allExpenses();

        //call get last budget method used to generate how much toast
        final Budget budget =  db.getLastBudget();

        //vew expenses
        Button btn = (Button) findViewById(R.id.buttonvs);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent VEA = new Intent(MainActivity.this,ViewExpensesActivity.class);
                startActivity(VEA);
            }
        });

        //can I afford it toast
        Button btn1 = (Button) findViewById(R.id.buttonciai);
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
            Context context = getApplicationContext();
            CharSequence text = budget.ciaString();
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            }
        });

        //set budget
        Button btn2 = (Button) findViewById(R.id.buttonswb);
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent ABA = new Intent(MainActivity.this,AddBudgetActivity.class);
                startActivity(ABA);
            }
        });

        //spend money
        Button btn3 = (Button) findViewById(R.id.buttonsm);
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent SM = new Intent(MainActivity.this,AddExpenseActivity.class);
                startActivity(SM);
            }
        });

        //budget analysis
        Button btn4 = (Button) findViewById(R.id.buttona);
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent An = new Intent(MainActivity.this,AnalysisActivity.class);
                startActivity(An);
            }
        });

        //expense analysis
        Button btn5 = (Button) findViewById(R.id.buttonea);
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent EAn = new Intent(MainActivity.this,ExpenseAnalysisActivity.class);
                startActivity(EAn);
            }
        });



    }
}




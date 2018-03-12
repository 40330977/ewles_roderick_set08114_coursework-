package com.example.r40330977.eyebudget3;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.Series;
import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.BarGraphSeries;

public class ExpenseAnalysisActivity extends ActionBarActivity {

    private static final Random RANDOM = new Random();
    private BarGraphSeries<DataPoint> series1;
    private int lastX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_analysis);

        DBHandler db = new DBHandler(this);

        String[] expenses = db.getEAnalysisData();//get analysis data



        GraphView graph1 = (GraphView) findViewById(R.id.graph1);//get graph view instance

        series1 = new BarGraphSeries<>(db.generateEData(expenses));//create new series converting data to data points

        graph1.addSeries(series1);
        series1.setSpacing(50);


        series1.setDrawValuesOnTop(true);



        Button btn = (Button) findViewById(R.id.buttonABE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent EAnB = new Intent(ExpenseAnalysisActivity.this, MainActivity.class);
                startActivity(EAnB);
            }


        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expense_analysis, menu);
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

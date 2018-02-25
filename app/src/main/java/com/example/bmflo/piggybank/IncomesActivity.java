package com.example.bmflo.piggybank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bmflo on 2/15/2018.
 */

public class IncomesActivity extends AppCompatActivity {

    private ArrayList<Income> incomeList;
    private ListView incomesView;

    IncomeDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_incomes);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        incomesView = (ListView)findViewById(R.id.income_list);
        incomeList = new ArrayList<Income>();

        dbHelper = new IncomeDBHelper(this);

        incomeList = dbHelper.getAllIncomes();
        if(incomeList.size()!=0){
            TextView emptyBanksText = (TextView)findViewById(R.id.emptyIncomeView);
            emptyBanksText.setVisibility(View.INVISIBLE);
            //setColors();
        }

        IncomeAdapter incomeAdapter = new IncomeAdapter(this, incomeList);
        incomesView.setAdapter(incomeAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_income_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_income:
                createNewIncome();
                //TextView emptyBanksText = (TextView)findViewById(R.id.emptyBanksView);
                //emptyBanksText.setVisibility(View.INVISIBLE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createNewIncome(){
        Intent intent = new Intent(IncomesActivity.this, CreateIncomeActivity.class);
        startActivity(intent);
    }
}

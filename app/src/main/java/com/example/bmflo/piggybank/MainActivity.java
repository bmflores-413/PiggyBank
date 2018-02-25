package com.example.bmflo.piggybank;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.design.widget.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private ArrayList<PiggyBank> piggyBankList;
    private ListView banksView;


    PiggyBankDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        banksView = (ListView)findViewById(R.id.piggy_bank_list);
        piggyBankList = new ArrayList<PiggyBank>();

        dbHelper = new PiggyBankDBHelper(this);

        piggyBankList = dbHelper.getAllBanks();
        if(piggyBankList.size()!=0){
            TextView emptyBanksText = (TextView)findViewById(R.id.emptyBanksView);
            emptyBanksText.setVisibility(View.INVISIBLE);
            //setColors();
        }

        BankAdapter bankAdapter = new BankAdapter(this, piggyBankList);
        banksView.setAdapter(bankAdapter);

        banksView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //PiggyBank currentBank = (PiggyBank) (banksView.getItemAtPosition(i));
                PiggyBank currentBank = (PiggyBank)  (piggyBankList.get(i));
                String currentBankName = currentBank.getBankName();

                Cursor data = dbHelper.getItemID(currentBankName);
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Intent editScreenIntent = new Intent(MainActivity.this, EditBankActivity.class);
                    editScreenIntent.putExtra("id", itemID);
                    editScreenIntent.putExtra("name", currentBankName);
                    startActivity(editScreenIntent);
                }

            }
        });
    }

    public void editPageFor(int i){
        PiggyBank currentBank = (PiggyBank)  (piggyBankList.get(i));
        String currentBankName = currentBank.getBankName();

        Cursor data = dbHelper.getItemID(currentBankName);
        int itemID = -1;
        while(data.moveToNext()){
            itemID = data.getInt(0);
        }
        if(itemID > -1) {
            Intent editScreenIntent = new Intent(MainActivity.this, EditBankActivity.class);
            editScreenIntent.putExtra("id", itemID);
            editScreenIntent.putExtra("name", currentBankName);
            startActivity(editScreenIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_bank:
                createNewBank();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void createNewBank(){
        Intent intent = new Intent(MainActivity.this, CreateBankActivity.class);
        startActivity(intent);
        TextView emptyBanksText = (TextView)findViewById(R.id.emptyBanksView);
        emptyBanksText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_manage_income:
                Intent intent = new Intent(MainActivity.this, IncomesActivity.class);
                startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setColors(){
        ProgressBar progPercentBar = (ProgressBar)findViewById(R.id.bank_progress_bar);
        for(int i=0; i<piggyBankList.size(); i++){
            PiggyBank currentBank = piggyBankList.get(i);
            switch(currentBank.getColor()){
                case "green":
                    progPercentBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar));
                    break;
                case "blue":
                    progPercentBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar_blue));
                    break;
                case "pink":
                    progPercentBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar_pink));
                    break;
                case "red":
                    progPercentBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar_red));
                    break;
                case "yellow":
                    progPercentBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar_yellow));
                    break;
                case "aqua":
                    progPercentBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_bar_aqua));
                    break;
            }
        }
    }

}

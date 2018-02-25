package com.example.bmflo.piggybank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by bmflo on 1/30/2018.
 */

public class CreateBankActivity extends Activity {

    private PiggyBank piggyBank;

    PiggyBankDBHelper dbHelper;

    EditText nameEntered;
    EditText goalEntered;
    EditText currentProgEntered;
    EditText deadlineNumEntered;
    Spinner deadlineIntervalEntered;
    Spinner colorEntered;

    private String bankName;
    private int goal;
    private int currentProg;
    private int deadline1;
    private String deadline2;
    private int color;


    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_bank_view);

        dbHelper = new PiggyBankDBHelper(this);

        nameEntered = (EditText) findViewById(R.id.bank_name);
        goalEntered = (EditText) findViewById(R.id.goal);
        currentProgEntered = (EditText) findViewById(R.id.current_progress);
        deadlineNumEntered = (EditText) findViewById(R.id.deadline1);
        deadlineIntervalEntered = (Spinner) findViewById(R.id.deadline2);
        colorEntered = (Spinner) findViewById(R.id.piggy_bank_color);


        saveButton = (Button)findViewById(R.id.save_bank);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createBank();
            }
        });
    }

    public void createBank(){

        String sNameEntered = nameEntered.getText().toString();
        int nGoalEntered = Integer.parseInt(goalEntered.getText().toString());
        int nCurrentProgEntered = Integer.parseInt(currentProgEntered.getText().toString());
        int nDeadlineNumEntered = Integer.parseInt(deadlineNumEntered.getText().toString());
        String sDeadlineIntervalEntered = deadlineIntervalEntered.getSelectedItem().toString();
        String sColoredEntered = colorEntered.getSelectedItem().toString();

        piggyBank = new PiggyBank(sNameEntered, nGoalEntered, nCurrentProgEntered, nDeadlineNumEntered, sDeadlineIntervalEntered, sColoredEntered);
        dbHelper.addData(sNameEntered, nGoalEntered, nCurrentProgEntered, nDeadlineNumEntered, sDeadlineIntervalEntered, sColoredEntered);

        Intent intent = new Intent(CreateBankActivity.this, MainActivity.class);
        startActivity(intent);
    }

}

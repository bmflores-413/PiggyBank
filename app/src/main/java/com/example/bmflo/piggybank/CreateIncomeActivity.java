package com.example.bmflo.piggybank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * Created by bmflo on 2/15/2018.
 */

public class CreateIncomeActivity extends Activity {

    private IncomeDBHelper dbHelper;

    private EditText sourceEntered;
    private EditText amountEntered;
    private Spinner frequencyEntered;
    private Switch autoEntered;
    private EditText limitEntered;

    private Button saveButton;

    String source;
    int amount;
    String frequency;
    Boolean auto;
    String sAuto;
    int limit;
    String banks;

    Boolean taxed;
    String sTaxed;
    int tax;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_income_view);

        dbHelper = new IncomeDBHelper(this);

        sourceEntered = (EditText)findViewById(R.id.income_source);
        amountEntered = (EditText)findViewById(R.id.income_amount);
        frequencyEntered = (Spinner)findViewById(R.id.income_frequency);
        autoEntered = (Switch)findViewById(R.id.auto_switch);
        limitEntered = (EditText)findViewById(R.id.limit);

        saveButton = (Button)findViewById(R.id.save_income);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createIncome();
            }
        });
    }

    public void createIncome(){
        source = sourceEntered.getText().toString();
        amount = Integer.parseInt(amountEntered.getText().toString());
        frequency = frequencyEntered.getSelectedItem().toString();
        auto = autoEntered.isChecked();
        limit = Integer.parseInt(limitEntered.getText().toString());
        banks = "None";

        taxed = false;
        tax = 0;

        if(auto){
            sAuto = "true";
        }else{
            sAuto = "false";
        }

        if(taxed){
            sTaxed = "true";
        }else{
            sTaxed = "false";
        }


        dbHelper.addData(source, sTaxed, tax, sAuto, amount, banks, frequency, limit);

        Intent intent = new Intent(CreateIncomeActivity.this, IncomesActivity.class);
        startActivity(intent);

    }
}

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

public class EditBankActivity extends Activity {
    private Button deleteButton;
    PiggyBankDBHelper dbHelper;

    private String selectedBank;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_bank_view);
        Intent receivedIntent = getIntent();

        dbHelper = new PiggyBankDBHelper(this);

        selectedID = receivedIntent.getIntExtra("id", -1);

        //get the name we passed as an extra
        selectedBank = receivedIntent.getStringExtra("name");

        /*
        nameEntered = (EditText) findViewById(R.id.bank_name);
        goalEntered = (EditText) findViewById(R.id.goal);
        currentProgEntered = (EditText) findViewById(R.id.current_progress);
        deadlineNumEntered = (EditText) findViewById(R.id.deadline1);
        deadlineIntervalEntered = (Spinner) findViewById(R.id.deadline2);
        colorEntered = (Spinner) findViewById(R.id.piggy_bank_color);
        */


        deleteButton = (Button)findViewById(R.id.delete_btn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBank();
                Intent nIntent = new Intent(EditBankActivity.this, MainActivity.class);
                nIntent.putExtra("deleted_id", selectedID);
                nIntent.putExtra("deleted_name", selectedBank);
                startActivity(nIntent);
            }
        });
    }

    public void deleteBank(){
        dbHelper.deleteBank(selectedID,selectedBank);
    }
}

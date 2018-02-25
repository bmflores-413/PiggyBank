package com.example.bmflo.piggybank;

import android.content.Context;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.v4.widget.CircularProgressDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;


/**
 * Created by bmflo on 1/30/2018.
 */

public class BankAdapter extends BaseAdapter {

    private ArrayList<PiggyBank> banks;
    private LayoutInflater bankInf;

    public BankAdapter(Context c, ArrayList<PiggyBank> myBanks){
        banks = myBanks;
        bankInf=LayoutInflater.from(c);
    }

    @Override
    public int getCount(){
        return banks.size();
    }

    @Override
    public Object getItem(int arg0){
        return null;
    }

    @Override
    public long getItemId(int arg0){
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //map to song layout (as in individual song)
        LinearLayout bankLayout = (LinearLayout)bankInf.inflate(R.layout.bank_list_item_view, parent,false);

        TextView progPercentText = (TextView)bankLayout.findViewById(R.id.goalPercent);
        TextView bankNameText = (TextView)bankLayout.findViewById(R.id.bank_name_in_list);


        ProgressBar progPercentBar = (ProgressBar)bankLayout.findViewById(R.id.bank_progress_bar);


        PiggyBank currentBank = banks.get(position);

        double div = ((double)currentBank.getCurrentProg()/currentBank.getGoal());
        double progPercentD = div*100;
        int progPercent = (int) progPercentD;
        progPercentText.setText(Integer.toString(progPercent) + "%");
        bankNameText.setText(currentBank.getBankName());
        progPercentBar.setProgress(progPercent);
        progPercentBar.setRotation(-90f);

        switch(currentBank.getColor()){
            case "green":
                //progPercentBar.setProgressDrawable(res.getDrawable(R.drawable.progress_bar));
        }
        bankLayout.setTag(position);

        return bankLayout;
    }
}

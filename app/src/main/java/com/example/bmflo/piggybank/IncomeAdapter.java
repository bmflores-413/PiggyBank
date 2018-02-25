package com.example.bmflo.piggybank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bmflo on 2/14/2018.
 */

public class IncomeAdapter extends BaseAdapter {

    private ArrayList<Income> incomes;
    private LayoutInflater incomeInf;

    public IncomeAdapter(Context c, ArrayList<Income> myIncomes){
        incomes = myIncomes;
        incomeInf=LayoutInflater.from(c);
    }

    @Override
    public int getCount(){
        return incomes.size();
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

        //map to income layout (as in individual income list item)
        LinearLayout incomeLayout = (LinearLayout)incomeInf.inflate(R.layout.income_list_item_view, parent,false);


        TextView incomeSource = (TextView)incomeLayout.findViewById(R.id.income_source);
        TextView incomeAmount = (TextView)incomeLayout.findViewById(R.id.rate_amount);
        TextView incomeFrequency = (TextView)incomeLayout.findViewById(R.id.rate_frequency);
        TextView percentSaved = (TextView)incomeLayout.findViewById(R.id.percent_saved);
        TextView items = (TextView)incomeLayout.findViewById(R.id.items);
        Switch autoOnOff = (Switch)incomeLayout.findViewById(R.id.auto_on_off_switch);

        Income currentIncome = incomes.get(position);

        incomeSource.setText(currentIncome.getSource());
        incomeAmount.setText(currentIncome.getAmount());
        incomeFrequency.setText(currentIncome.getFrequency());
        percentSaved.setText(currentIncome.getSavedPercent());

        //parse items to get correct format

        //

        if(currentIncome.getAutomatic()==true){
            autoOnOff.setChecked(true);
        }else{
            autoOnOff.setChecked(false);
        }

        incomeLayout.setTag(position);

        return incomeLayout;
    }
}

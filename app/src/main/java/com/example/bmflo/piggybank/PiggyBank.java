package com.example.bmflo.piggybank;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by bmflo on 1/30/2018.
 */

public class PiggyBank {
    private String bankName;
    private int goal;
    private int currentProg;
    private int deadline1;
    private String deadline2;
    private String color;

    public PiggyBank(String name, int g, int p, int d1, String d2, String c){
        bankName = name;
        goal = g;
        currentProg = p;
        deadline1 = d1;
        deadline2 = d2;
        color = c;
    }

    public String getBankName(){
        return bankName;
    }

    public int getGoal(){
        return goal;
    }

    public int getCurrentProg(){
        return currentProg;
    }

    public int getDeadline1() {
        return deadline1;
    }

    public String getDeadline2() {
        return deadline2;
    }

    public String getColor() {
        return color;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public void setCurrentProg(int currentProg) {
        this.currentProg = currentProg;
    }

    public void setDeadline1(int deadline1) {
        this.deadline1 = deadline1;
    }

    public void setDeadline(String deadline2) {
        this.deadline2 = deadline2;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

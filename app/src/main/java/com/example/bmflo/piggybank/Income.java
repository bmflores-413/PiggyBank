package com.example.bmflo.piggybank;

/**
 * Created by bmflo on 2/9/2018.
 */

public class Income {

    private String source;
    private String frequency;
    private int amount;        //per (frequency)
    private Boolean automatic;
    private int savedPercent;
    private String funding;
    private int limit;

    public Income(String s, String f, int a, Boolean au, int sp, String fu, int l){
        source = s;
        frequency = f;
        amount = a;
        automatic = au;
        savedPercent = sp;
        funding = fu;
        limit = l;
    }

    public String getSource() {
        return source;
    }

    public String getFrequency() {
        return frequency;
    }

    public int getAmount() {
        return amount;
    }

    public Boolean getAutomatic() {
        return automatic;
    }

    public int getSavedPercent() { return savedPercent; }

    public String getFunding() {
        return funding;
    }

    public int getLimit() {
        return limit;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setAutomatic(Boolean automatic) {
        this.automatic = automatic;
    }

    public void setSavedPercent(int savedPercent) { this.savedPercent=savedPercent; }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

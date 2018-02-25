package com.example.bmflo.piggybank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by bmflo on 2/14/2018.
 */

public class IncomeDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "IncomeDBHelper";
    private static final String TABLE_NAME = "income_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "source";
    private static final String COL3 = "is_taxed";
    private static final String COL4 = "percent_taxed";
    private static final String COL5 = "auto_update";
    private static final String COL6 = "income_amount";
    private static final String COL7 = "banks_involved";
    private static final String COL8 = "frequency";
    private static final String COL9 = "limit";

    public IncomeDBHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT ," + COL3 + " TEXT ," + COL4 + " INT ," + COL5 + " TEXT ," + COL6 + " INT ," + COL7 + " TEXT ," + COL8 + " TEXT ," + COL9 + " INT);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j){
        String comm = "DROP IF TABLE EXISTS ";
        db.execSQL(comm + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String source, String taxed, int tax, String auto, int income, String banks, String frequency, int limit){
        Log.d(TAG, "addData: Adding " + source + " to " + TABLE_NAME);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, source);
        contentValues.put(COL3, taxed);
        contentValues.put(COL4, tax);
        contentValues.put(COL5, auto);
        contentValues.put(COL6, income);
        contentValues.put(COL7, banks);
        contentValues.put(COL8, frequency);
        contentValues.put(COL9, limit);

        Log.d(TAG, "addData: Added " + source + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result < 0){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME + " WHERE " + COL2 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void deleteIncome(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public ArrayList<Income> getAllIncomes() {
        ArrayList<Income> array_list = new ArrayList<Income>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            String a1 = res.getString(res.getColumnIndex(COL2));
            String a4 = res.getString(res.getColumnIndex(COL5));
            Boolean auto;
            if(a4 == "true"){
                auto = true;
            }else{
                auto = false;
            }
            int a5 = Integer.parseInt(res.getString(res.getColumnIndex(COL6)));
            String a7 = res.getString(res.getColumnIndex(COL8));
            int a8 = Integer.parseInt(res.getString(res.getColumnIndex(COL9)));

            Income currentIncome = new Income(a1, a7, a5, auto, 0, "None", a8);
            array_list.add(currentIncome);
            res.moveToNext();
        }
        return array_list;
    }
}

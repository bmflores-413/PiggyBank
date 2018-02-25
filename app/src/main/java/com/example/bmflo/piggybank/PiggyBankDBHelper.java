package com.example.bmflo.piggybank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bmflo on 1/30/2018.
 */

public class PiggyBankDBHelper extends SQLiteOpenHelper {
    private static final String TAG = "PiggyBankDBHelper";
    private static final String TABLE_NAME = "piggy_bank_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "goal";
    private static final String COL4 = "progress";
    private static final String COL5 = "date1";
    private static final String COL6 = "date2";
    private static final String COL7 = "color";

    public PiggyBankDBHelper(Context context){
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        /*

        sqlite> CREATE TABLE COMPANY(
                ID INT PRIMARY KEY     NOT NULL,
                NAME           TEXT    NOT NULL,
                AGE            INT     NOT NULL,
                ADDRESS        CHAR(50),
                SALARY         REAL
                );



        private static final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255) ," + MyPASSWORD + " VARCHAR(255));";
         */

        String createTable = "CREATE TABLE " + TABLE_NAME + " (" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL2 + " TEXT ," + COL3 + " INT ," + COL4 + " INT ," + COL5 + " INT ," + COL6 + " TEXT ," + COL7 + " TEXT);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int j){
        String comm = "DROP IF TABLE EXISTS ";
        db.execSQL(comm + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, int goal, int prog, int num, String interval, String color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, name);
        contentValues.put(COL3, goal);
        contentValues.put(COL4, prog);
        contentValues.put(COL5, num);
        contentValues.put(COL6, interval);
        contentValues.put(COL7, color);

        Log.d(TAG, "addData: Adding " + name + " to " + TABLE_NAME);

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

    public void deleteBank(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

    public ArrayList<PiggyBank> getAllBanks() {
        ArrayList<PiggyBank> array_list = new ArrayList<PiggyBank>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            PiggyBank currentBank = new PiggyBank(res.getString(res.getColumnIndex(COL2)), Integer.parseInt(res.getString(res.getColumnIndex(COL3))),Integer.parseInt(res.getString(res.getColumnIndex(COL4))),Integer.parseInt(res.getString(res.getColumnIndex(COL5))),res.getString(res.getColumnIndex(COL6)),res.getString(res.getColumnIndex(COL7)));
            array_list.add(currentBank);
            res.moveToNext();
        }
        return array_list;
    }
}

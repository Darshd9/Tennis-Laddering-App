package com.example.darsh.tennisladderingpractice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by darsh on 3/1/2018.
 */

public class Database extends SQLiteOpenHelper {

    public static class Titles implements BaseColumns{
        private static final String TABLE_Name = "Player_Table3";
        private static final String COL1 = "Player_Name";
        private static final String COL2 = "Rank";
        private static final String COL3 = "Wins";
        private static final String COL4 = "Loses";
        private static final String COL5 = "Improvement";
        private static final String COL6 = "Start_Rank";
        private static final String COL7 = "MAX_Rank";
        private static final String COL8 = "MIN_Rank";

    }

    Player player;



    public Database(Context context){
        super(context, Titles.TABLE_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + Titles.TABLE_Name + " (" + Titles._ID + " INTEGER PRIMARY KEY," +
                Titles.COL1 + " TEXT," + Titles.COL2 + " INTEGER," + Titles.COL3 + " INTEGER," + Titles.COL4 + " INTEGER,"
                + Titles.COL5 + " INTEGER," + Titles.COL6 + " INTEGER," + Titles.COL7 + " INTEGER, " + Titles.COL8 + " INTEGER)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Titles.TABLE_Name);
        onCreate(db);

    }

    public boolean addStudent(String name, int rank){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Titles.COL1, name);
        contentValues.put(Titles.COL2, rank);
        contentValues.put(Titles.COL3, 0);
        contentValues.put(Titles.COL4, 0);
        contentValues.put(Titles.COL5, 0);
        contentValues.put(Titles.COL6, rank);
        contentValues.put(Titles.COL7, rank);
        contentValues.put(Titles.COL8, rank);

        long result = db.insert(Titles.TABLE_Name, null, contentValues);


        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public void clearData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Titles.TABLE_Name,null,null);
    }

    public boolean updateData(Player player){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Titles.COL1, player.getName());
        contentValues.put(Titles.COL2, player.getRank());
        contentValues.put(Titles.COL3, player.getWin());
        contentValues.put(Titles.COL4, player.getLose());
        contentValues.put(Titles.COL5, player.getImprovement());
        contentValues.put(Titles.COL6, player.getStartRank());
        contentValues.put(Titles.COL7, player.getMaxRank());
        contentValues.put(Titles.COL8, player.getMinRank());

        String id = String.valueOf(player.getStartRank());

        long result = db.update(Titles.TABLE_Name, contentValues, "Start_Rank = ?", new String[] { id });
        if (result == -1){
            return false;
        }else {
            return true;
        }
    }

    //returns data

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Titles.TABLE_Name;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getPlayerInfo(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Titles.TABLE_Name +
                " WHERE " + Titles.COL1 + " = '" + name + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }


    public Cursor getPlayerID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + Titles.COL6 + " FROM " + Titles.TABLE_Name + " WHERE " + Titles.COL1 + " = '" + name + "'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public String getPlayerName(int rank){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + Titles.COL1 + " FROM " + Titles.TABLE_Name + " WHERE " + Titles.COL2 + " = '" + rank + "'";
        Cursor data = db.rawQuery(query, null);
        String name = "";
        while (data.moveToNext()){
            name = data.getString(0);
        }

        return name;

    }

    public int getPlayerCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + Titles.TABLE_Name;
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        return count;

    }


}

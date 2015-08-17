package com.example.taz.canadafood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
// From Book Beginning Android 4 Application Development
public class DBAdapter {
    static final String DB_FULL_PATH="/data/data/com.example.taz.canadafood/databases/food.db";
    static final String KEY_YEAR="Year";
    static final String KEY_YEAR2="Year";
    static final String KEY_GEO="GEO";
    static final String KEY_GEO2="GEO";
    static final String KEY_FOOD="FOOD";
    static final String KEY_FOOD2="FOOD";
    static final String KEY_COMM="COMMODITY";
    static final String KEY_COMM2="COMMODITY";
    static final String KEY_VEC="Vector";
    static final String KEY_VEC2="Vector";
    static final String KEY_COR="Coordinate";
    static final String KEY_COR2="Coordinate";
    static final String KEY_VAL="Valuess";
    static final String KEY_VAL2="Valuess";
    static final String DATABASE_NAME="food.db";
    static final String DATABASE_TABLE="english";
    static final String DATABASE_TABLE2="french";
    static final int DATABASE_VERSION=1;
    static final String DATABASE_CREATE="CREATE TABLE english(\n" +
            "   _id INTEGER PRIMARY KEY AUTOINCREMENT"+
            "  ,Year   INTEGER(4) NOT NULL \n" +
            "  ,GEO        VARCHAR(6) NOT NULL\n" +
            "  ,FOOD       VARCHAR(34) NOT NULL\n" +
            "  ,COMMODITY  VARCHAR(91) NOT NULL\n" +
            "  ,Vector     VARCHAR(9) NOT NULL\n" +
            "  ,Coordinate VARCHAR(7) NOT NULL\n" +
            "  ,Valuess      VARCHAR(6) NOT NULL\n" +
            ");";
    static final String DATABASE_CREATE2="CREATE TABLE french(\n" +
            "   _id INTEGER PRIMARY KEY AUTOINCREMENT"+
            "  ,Year   INTEGER(4) NOT NULL \n" +
            "  ,GEO        VARCHAR(6) NOT NULL\n" +
            "  ,FOOD       VARCHAR(34) NOT NULL\n" +
            "  ,COMMODITY  VARCHAR(91) NOT NULL\n" +
            "  ,Vector     VARCHAR(9) NOT NULL\n" +
            "  ,Coordinate VARCHAR(7) NOT NULL\n" +
            "  ,Valuess    DECIMAL(6,2) NOT NULL\n" +
            ");";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;


    public DBAdapter(Context context) {
        this.context = context;
        DBHelper=new DatabaseHelper(context);
    }


    public static class DatabaseHelper extends SQLiteOpenHelper{
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);
            db.execSQL(DATABASE_CREATE2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS english");
            db.execSQL("DROP TABLE IF EXISTS french");
            onCreate(db);
        }
    }
    //open the database
    public DBAdapter open() throws SQLException{
        db=DBHelper.getWritableDatabase();
        return this;
    }
    //close the database
    public void close(){
        DBHelper.close();
    }

    //check table
    public boolean doesTableExist() {
        Cursor cursor = db.rawQuery("select DISTINCT year from "+DATABASE_TABLE, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }
    public boolean doesTableExist2() {
        Cursor cursor = db.rawQuery("select DISTINCT year from "+DATABASE_TABLE2, null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }
    public void drop(){db.execSQL("DELETE FROM english");}
    public void drop2(){db.execSQL("DELETE FROM french");}
    //insert the values for english table
    public void insertValues(String year,String geo,String food,String commodity,String vec,String coor,String valuess){
        ContentValues initial=new ContentValues();
        initial.put(KEY_YEAR,year);
        initial.put(KEY_GEO,geo);
        initial.put(KEY_FOOD,food);
        initial.put(KEY_COMM,commodity);
        initial.put(KEY_VEC,vec);
        initial.put(KEY_COR, coor);
        initial.put(KEY_VAL, valuess);
        db.insert(DATABASE_TABLE, null, initial);
    }
    //insert values for french table
    public void insertValues2(String year, String geo, String food, String commodity, String vec, String coor, String val) {
        ContentValues initial=new ContentValues();
        initial.put(KEY_YEAR2,year);
        initial.put(KEY_GEO2,geo);
        initial.put(KEY_FOOD2,food);
        initial.put(KEY_COMM2,commodity);
        initial.put(KEY_VEC2,vec);
        initial.put(KEY_COR2, coor);
        initial.put(KEY_VAL2, val);
        db.insert(DATABASE_TABLE2,null,initial);
    }
 public Cursor getRecord(){
     return null;
 }

}
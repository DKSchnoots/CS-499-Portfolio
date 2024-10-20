package com.example.cs499_artifactoneenhancements;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalsDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "animals.db";
    private static final int DATABASE_VERSION = 1; // Good practice to specify the database version
    private static final String TABLE_NAME = "animals";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "NAME";
    private static final String COL_SEX = "SEX";
    private static final String COL_BREED = "BREED";
    private static final String COL_AGE = "AGE";

    public AnimalsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_SEX + " TEXT, " +
                COL_BREED + " TEXT, " +
                COL_AGE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String sex, String breed, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_SEX, sex);
        contentValues.put(COL_BREED, breed);
        contentValues.put(COL_AGE, age);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public boolean updateData(String id, String name, String sex, String breed, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_SEX, sex);
        contentValues.put(COL_BREED, breed);
        contentValues.put(COL_AGE, age);
        int result = db.update(TABLE_NAME, contentValues, COL_ID + " = ?", new String[]{id});
        return result > 0;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_ID + " = ?", new String[]{id});
    }
}

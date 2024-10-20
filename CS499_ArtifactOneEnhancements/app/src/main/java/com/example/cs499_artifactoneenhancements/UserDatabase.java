package com.example.cs499_artifactoneenhancements;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserData.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_USER = "username";
    private static final String COLUMN_PASS = "password";

    public UserDatabase(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_USER + " TEXT PRIMARY KEY, "
                + COLUMN_PASS + " TEXT)";
        db.execSQL(createTableQuery);

        // default user and password
        String defaultUsername = "user";
        String defaultPassword = "password";
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, defaultUsername);
        values.put(COLUMN_PASS, defaultPassword);
        db.insert(TABLE_NAME, null, values);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER, user.getUsername());
        values.put(COLUMN_PASS, user.getPassword());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_USER};
        String selection = COLUMN_USER + " = ? AND " + COLUMN_PASS + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
}
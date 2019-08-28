package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATEBASE_VERSION = 1;
    private static final String DATEBASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table cotacts";

    public DatabaseHelper(Context context){
        super(context, DATEBASE_NAME, null, DATEBASE_VERSION);

    }

    public void insertContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());

        db.insert(TABLE_NAME,null, values);
    }

    public String searchPass(String uname){
        db = this.getReadableDatabase();
        String query = "select uname, pass form" + TABLE_NAME;
        Cursor cursor =  db.rawQuery(query, null);
        String auxUname, pass = "not found";

        if(cursor.moveToFirst()){
            do{
                auxUname = cursor.getString(0);

                if(auxUname.equals(uname)){
                    pass = cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }

        return pass;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXIST" + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}

package com.example.testapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.testapp.ModelsandAdapters.Datum;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Datadb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "details";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String FIRST_NAME_COL = "First_name";

    private static final String LAST_NAME_COL = "last_name";
    private static final String IMG_URL_COL = "Imgurl";
    private static final String EMAIL_COL = "email";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME_COL + " TEXT,"
                + LAST_NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + IMG_URL_COL + " TEXT)";
        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }public void deleteData() {


        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }
    public void setAllData(String F_name, String L_name, String Email, String Img_url) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FIRST_NAME_COL, F_name);
        values.put(LAST_NAME_COL, L_name);
        values.put(EMAIL_COL, Email);
        values.put(IMG_URL_COL, Img_url);


        db.insert(TABLE_NAME, null, values);

        db.close();
    }


    public ArrayList<Datum> getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<Datum> ModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                ModalArrayList.add(new Datum(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(4),
                        cursor.getString(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();
        return ModalArrayList;
    }
}

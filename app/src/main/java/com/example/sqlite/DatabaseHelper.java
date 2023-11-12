package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "user_database";
    private static final int DATABASE_VERSION = 6;
    private static final String TABLE_USER = "users";
    private static final String KEY_ID = "id";

    private static final String KEY_RUT = "rut";
    private static final String KEY_FIRSTNAME = "name";
    private static final String KEY_DESC = "descripcion";
    //private static final String KEY_FECHA = "fecha";
    //private static final String KEY_HORA = "hora";

    private static final String KEY_LABORATORIO = "nombreLaboratorio";


    private static final String CREATE_TABLE_STUDENTS = "CREATE TABLE "
            + TABLE_USER + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_RUT + " TEXT, "
            + KEY_FIRSTNAME + " TEXT, " + KEY_DESC + " TEXT, "
            + KEY_LABORATORIO + " TEXT );";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL(CREATE_TABLE_STUDENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        onCreate(db);
    }



    public long addUserDetail(String rut, String name, String descripcion, String nombreLaboratorio) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_RUT, rut);
        values.put(KEY_FIRSTNAME, name);
        values.put(KEY_DESC, descripcion);
        values.put(KEY_LABORATORIO, nombreLaboratorio);
        //values.put(KEY_FECHA, getCurrentDate());
        //values.put(KEY_HORA, getCurrentTime());

       // insert row in students table
        long insert = db.insert(TABLE_USER, null, values);

        return insert;
    }

    //private String getCurrentDate() {
      //  SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        //Date now = new Date();
        //return sdfDate.format(now);
    //}

    //private String getCurrentTime() {
      //  SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        //Date now = new Date();
        //return sdfTime.format(now);
    //}

    public ArrayList<UserModel> getAllUsers() {
        ArrayList<UserModel> userModelArrayList = new ArrayList<UserModel>();
        String selectQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                UserModel userModel = new UserModel();
                userModel.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                userModel.setRut(c.getString(c.getColumnIndex(KEY_RUT)));
                userModel.setName(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                userModel.setDescripcion(c.getString(c.getColumnIndex(KEY_DESC)));
                userModel.setLab(c.getString(c.getColumnIndex(KEY_LABORATORIO)));

               // adding to Students list
                userModelArrayList.add(userModel);
            } while (c.moveToNext());
         }
        return userModelArrayList;
    }

    public int updateUser(int id, String rut, String name, String descripcion, String nombrelaboratorio) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_RUT, rut);
        values.put(KEY_FIRSTNAME, name);
        values.put(KEY_DESC, descripcion);
        values.put(KEY_LABORATORIO, nombrelaboratorio);

       // update row in students table base on students.is value
        return db.update(TABLE_USER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteUSer(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

}


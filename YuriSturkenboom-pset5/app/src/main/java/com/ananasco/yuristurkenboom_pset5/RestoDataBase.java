package com.ananasco.yuristurkenboom_pset5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**

 */

public class RestoDataBase extends SQLiteOpenHelper {

    private static RestoDataBase instance;

    private RestoDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE orders (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(255), price FLOAT);");

//        // add 3 sample items
          sqLiteDatabase.execSQL("INSERT INTO orders (name, price) VALUES ('Tuna sandwich yeah boi', 9001)");
//        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('buy more cheese', 0);");
//        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('feed goldfish', 0);");
    }

    public void addItem(String name) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        //cv.put("price", price);
        getWritableDatabase().insert("orders",null, cv);
    }

    public void clear() {
        getWritableDatabase().execSQL("DELETE FROM orders");
    }

    public static RestoDataBase getInstance(Context context) {
        if (instance == null) {
            instance = new RestoDataBase(context, "restoDatabase", null, 1);
        }
        return instance;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("We had an upgrade! :O");
        clear();
    }

    public Cursor selectAll(){
        return getWritableDatabase().rawQuery("SELECT * FROM orders",null);
    }
    /*
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE todos;");
        onCreate(sqLiteDatabase);
    }


    public Cursor selectAll(){
        Cursor cursor = getWritableDatabase().rawQuery("SELECT * FROM todos",null);
        return cursor;
    }

    public void insert(String title, Boolean completed) {
        ContentValues cv = new ContentValues();
        cv.put("title", title);
        cv.put("completed", completed);
        getWritableDatabase().insert("todos",null,cv);
    }

    public void update (long id, Boolean updateCompleted) {
        ContentValues cv = new ContentValues();
        cv.put("completed", updateCompleted);
        getWritableDatabase().update("todos", cv, "_id = " + id, null);
    }

    public void delete (long id) {
        getWritableDatabase().delete("todos", "_id = " + id, null);
    }*/
}

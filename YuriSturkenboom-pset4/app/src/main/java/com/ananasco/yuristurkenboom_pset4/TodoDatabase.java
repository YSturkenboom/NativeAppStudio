package com.ananasco.yuristurkenboom_pset4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yuri on 28-11-17.
 */

public class TodoDatabase extends SQLiteOpenHelper {

    private static TodoDatabase instance;

    private TodoDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE todos (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title VARCHAR(255), completed BOOLEAN);");

        // add 3 sample items
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('install new todo app', 1);");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('buy more cheese', 0);");
        sqLiteDatabase.execSQL("INSERT INTO todos (title, completed) VALUES ('feed goldfish', 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE todos;");
        onCreate(sqLiteDatabase);
    }

    public static TodoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new TodoDatabase(context, "todoDatabase", null, 1);
        }
        return instance;
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
    }
}

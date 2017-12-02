package com.ananasco.yuristurkenboom_pset5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by yuri on 1-12-17.
 */

public class RestoAdapter extends ResourceCursorAdapter {

    public RestoAdapter(Context context, Cursor cursor, int rowLayoutId){
        super(context, rowLayoutId, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        System.out.println("yoloswag");
        int nameI = cursor.getColumnIndex("name");
        String name = cursor.getString(nameI);
        ((TextView) view.findViewById(R.id.itemText)).setText(name);
    }
}


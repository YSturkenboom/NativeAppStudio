package com.ananasco.yuristurkenboom_pset4;

import android.content.Context;
import android.database.Cursor;
import android.text.Layout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/**
 * Created by yuri on 28-11-17.
 */

public class TodoAdapter extends ResourceCursorAdapter {

    public TodoAdapter(Context context, Cursor cursor, int rowLayoutId){
        super(context, rowLayoutId, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        int titleI = cursor.getColumnIndex("title");
        int completedI = cursor.getColumnIndex("completed");
        String title = cursor.getString(titleI);
        int completed = cursor.getInt(completedI);
        ((TextView) view.findViewById(R.id.itemText)).setText(title);
        ((CheckBox) view.findViewById(R.id.checkbox)).setChecked(completed != 0);
    }
}

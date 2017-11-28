package com.ananasco.yuristurkenboom_pset4;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private TodoAdapter adapter;
    private TodoDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Todo");
        db = TodoDatabase.getInstance(getApplicationContext());
        Cursor result = db.selectAll();
        adapter = new TodoAdapter(this, result, R.layout.row_todo);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnTodoClickListener());
        listView.setOnItemLongClickListener(new OnTodoLongClickListener());

    }

    public void addItem(View view) {
        TodoDatabase db = TodoDatabase.getInstance(getApplicationContext());
        db.insert(((EditText) findViewById(R.id.edittext)).getText().toString(), false);
        updateData();
    }

    private void updateData() {
        adapter.swapCursor(db.selectAll());
    }

    private class OnTodoClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CheckBox cb = view.findViewById(R.id.checkbox);
            Boolean updateCompleted = !cb.isChecked();
            db.update(l, updateCompleted);
            updateData();
        }
    }

    private class OnTodoLongClickListener implements AdapterView.OnItemLongClickListener {

        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            db.delete(l);
            updateData();
            return true;
        }
    }
}

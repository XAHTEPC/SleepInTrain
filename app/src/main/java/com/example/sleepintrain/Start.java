package com.example.sleepintrain;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sleepintrain.data_2.Contract_2;
import com.example.sleepintrain.data_2.DBHelper_2;

public class Start extends AppCompatActivity {
    DBHelper_2 dbHelper = new DBHelper_2(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }
    public void start(View V)
    {
        Intent r = new Intent(this,Login.class);
        startActivity(r);

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract_2.DataEntry.COLUMN_STATE, 0);
        contentValues.put(Contract_2.DataEntry.COLUMN_DATE,"");
        database.insert(Contract_2.DataEntry.TABLE_NAME, null, contentValues);
    }
}

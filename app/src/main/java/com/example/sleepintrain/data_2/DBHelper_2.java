package com.example.sleepintrain.data_2;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DBHelper_2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DATA.db";
    private static final int DATABASE_VERSION = 1;
    public DBHelper_2 (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Contract_2.DataEntry.TABLE_NAME + " ("
                + Contract_2.DataEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract_2.DataEntry.COLUMN_STATE + " INTEGER NOT NULL, "
                + Contract_2.DataEntry.COLUMN_DATE + " TEXT NOT NULL " + ")" );
        Log.w("SQLite", "Создана БД");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS  EquipmentEntry.TABLE_NAME");
        onCreate(db);
    }
}
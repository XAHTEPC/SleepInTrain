package com.example.sleepintrain.data_2;
import android.provider.BaseColumns;
public class Contract_2 {
    private  Contract_2()
    {
    };
    public static final class DataEntry implements BaseColumns {
        public final static String TABLE_NAME = "DATA";

        public final static String _ID = "_id";
        public final static String COLUMN_DATE = "DATE";
        public  final  static  String COLUMN_STATE = "STATE";
    }
}

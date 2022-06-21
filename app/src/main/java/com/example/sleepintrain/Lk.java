package com.example.sleepintrain;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sleepintrain.data_2.Contract_2;
import com.example.sleepintrain.data_2.DBHelper_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Lk extends AppCompatActivity {
    TextView female, name, date, login, pass, date_bill;
    private final static String FILE = "enter.txt";
    DBHelper_2 dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lk);
        female = findViewById(R.id.female);
        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        login = findViewById(R.id.log);
        pass = findViewById(R.id.pass);
        ImageView imageView = findViewById(R.id.pic_qr);
        if(check())
            imageView.setImageResource(R.drawable.qr);
        String INFO="";
        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            INFO=text;

        }
        catch(IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        char[] info = INFO.toCharArray();
        String st="";
        int m=0;
        for(int i=0;i<INFO.length();i++)
        {
            //Log.e("st",st);
            if(info[i]!='\n')
                st+=info[i];

            else
            {
                if(m==0)
                    female.setText(st);
                if(m==1)
                    name.setText(st);
                if(m==2)
                    date.setText(st);
                if(m==3)
                    login.setText(st);
                if(m==4)
                    pass.setText(st);
                st="";
                m++;
            }

        }
    }
    boolean check()
    {
        dbHelper = new DBHelper_2(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] info = {
                Contract_2.DataEntry.COLUMN_STATE,
                Contract_2.DataEntry.COLUMN_DATE
        };
        Cursor cursor = db.query(
                Contract_2.DataEntry.TABLE_NAME,   // таблица
                info,            // столбцы
                null,                  // столбцы для условия WHERE
                null,                  // значения для условия WHERE
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);
        cursor.moveToLast();
        int dateColumnIndex = cursor.getColumnIndex(Contract_2.DataEntry.COLUMN_DATE);
        int stateColumnIndex = cursor.getColumnIndex(Contract_2.DataEntry.COLUMN_STATE);
        int  stateColumn = cursor.getInt(stateColumnIndex);
        String currentdate = cursor.getString(dateColumnIndex);
        if(!currentdate.isEmpty()) {
            char[] mon = currentdate.toCharArray();
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            char[] date = dateText.toCharArray();
            String d = "";
            String m = "";
            String y = "";
            String d_ = "";
            String m_ = "";
            String y_ = "";
            d += String.valueOf(date[0]) + String.valueOf(date[1]);
            m += String.valueOf(date[3]) + String.valueOf(date[4]);
            y += String.valueOf(date[8]) + String.valueOf(date[9]);
            d_ += String.valueOf(mon[0]) + String.valueOf(mon[1]);
            m_ += String.valueOf(mon[3]) + String.valueOf(mon[4]);
            y_ += String.valueOf(mon[8]) + String.valueOf(mon[9]);
            int D = Integer.parseInt(d);
            int D_ = Integer.parseInt(d_);
            int M = Integer.parseInt(m);
            int M_ = Integer.parseInt(m_);
            int Y = Integer.parseInt(y);
            int Y_ = Integer.parseInt(y_);
            boolean t = stateColumn == 1 && (D - D_ < 2 &&D - D_>=0&& M == M_ && Y == Y_ ||
                    D == 1 &&
                            (D_ == 30 && (M == 4 && M_ == 3 || M == 6 && M_ == 5 || M == 9 && M_ == 8 || M == 11 && M_ == 10) && Y == Y_
                                    || D_ == 31 && (M == 1 && M_ == 12 && Y - Y_ == 1 || (M == 3 && M_ == 2 || M == 5 && M == 4 || M == 7 && M == 6 || M == 8 && M_ == 7 || M == 10 && M_ == 9 || M == 12 && M_ == 11) && Y == Y_) ||
                                    ((D_ == 28 || D_ == 29) && M_ == 2 && Y == Y_)))
                    || stateColumn == 2 && (D >= D_ && M == M_ && Y == Y_ || D < D_ && M - M_ == 1 && Y == Y_ || D < D_ && M == 1 && M_ == 12 && Y - Y_ == 1);
            //Log.e("INFO",String.valueOf(t));
            // Log.e("DATE",mon+"/"+date+"/"+stateColumn);
            //  Log.e("XXX", d+"-"+d_+" "+m+"-"+m_+" "+y+"-"+y_);
            return t;
        }
        else return false;
    }
}

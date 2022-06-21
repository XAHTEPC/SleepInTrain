package com.example.sleepintrain;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sleepintrain.data_2.Contract_2;
import com.example.sleepintrain.data_2.DBHelper_2;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class Buy extends AppCompatActivity {
    EditText num, date, cvv;
    DBHelper_2 dbHelper = new DBHelper_2(this);
    private String currentText = "";
    /*private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int i1, int i2) {
            if (!s.toString().equals("")) {
                // Remove all non-digit.
                String newTextClean = s.toString().replaceAll("[^\\d.]|\\.", "");
                String currentTextClean = currentText.replaceAll("[^\\d.]|\\.", "");

                int newTextLength = newTextClean.length();

                // Cursor Position Index.
                int selectionIndex = newTextLength;
                for (int i = 4; i <= newTextLength && i < 16; i += 4) {
                    selectionIndex++;
                }
                // Fix for pressing delete next to a forward slash
                if (newTextClean.equals(currentTextClean))  {
                    selectionIndex--;
                }

                if (newTextClean.length() < 16) {
                    newTextClean = newTextClean + this.DDMMYYYY.substring(newTextClean.length());
                } else {
                    // This part makes sure that when we finish entering numbers
                    // the date is correct, fixing it otherwise
                    int day  = Integer.parseInt(newTextClean.substring(0,2));
                    int month  = Integer.parseInt(newTextClean.substring(2,4));
                    int year = Integer.parseInt(newTextClean.substring(4,8));

                    month = month < 1 ? 1 : month > 12 ? 12 : month;
                    this.calendar.set(Calendar.MONTH, month-1);

                    year = (year < 1900)? 1900:(year > 2100)? 2100 : year;
                    this.calendar.set(Calendar.YEAR, year);

                    // ^ first set year for the line below to work correctly
                    // with leap years - otherwise, date e.g. 29/02/2012
                    // would be automatically corrected to 28/02/2012

                    day = (day > this.calendar.getActualMaximum(Calendar.DATE))? this.calendar.getActualMaximum(Calendar.DATE):day;

                    newTextClean = String.format("%02d%02d%02d",day, month, year);
                }
                // "%s/%s/%s"
                String format = "%s" + SEPARATOR + "%s" + SEPARATOR +"%s";
                newTextClean = String.format(format, newTextClean.substring(0, 2),
                        newTextClean.substring(2, 4),
                        newTextClean.substring(4, 8));

                selectionIndex = selectionIndex < 0 ? 0 : selectionIndex;
                this.currentText = newTextClean;

                this.editText.setText(this.currentText);
                this.editText.setSelection(selectionIndex < this.currentText.length() ? selectionIndex : this.currentText.length());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        num = findViewById(R.id.number);
        date = findViewById(R.id.date);
        cvv = findViewById(R.id.cvv);
       // num.addTextChangedListener(textWatcher);
        //date.addTextChangedListener(textWatcher);
    }
    boolean check_1 (String S){
        char [] s = S.toCharArray();
        boolean fl = true;
        for (int i = 0; i<S.length(); i++) {
            if (s [i] > '9' || s[i]<'0' || S.length()!=16) fl = false;
        }
        return  fl;
    }
    boolean check_2 (String S){
        char [] s = S.toCharArray();
        boolean fl = true;
        for (int i = 0; i<S.length(); i++) {
            if (s [i] >'9'|| s[i]<'0'|| S.length()!=4) fl = false;
        }
        return  fl;
    }
    boolean check_3 (String S){
        char [] s = S.toCharArray();
        boolean fl = true;
        for (int i = 0; i<S.length(); i++) {
            if (s [i] > '9' || s[i]<'0'|| S.length()!=3) fl = false;
        }
        return  fl;
    }
    public void buy_1 (View V){

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        String S1 = num.getText().toString();
        String S2 = date.getText().toString();
        String S3 = cvv.getText().toString();
        if (check_1(S1) && !S1.isEmpty() && check_2(S2) && !S2.isEmpty() && check_3(S3) && !S3.isEmpty())
        {
            Toast.makeText(this, "Оплата прошла успешно", Toast.LENGTH_SHORT).show();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Contract_2.DataEntry.COLUMN_STATE, 1);
            contentValues.put(Contract_2.DataEntry.COLUMN_DATE, dateText);
            database.insert(Contract_2.DataEntry.TABLE_NAME, null, contentValues);
        }
        else
            Toast.makeText(this, "Оплата не прошла", Toast.LENGTH_SHORT).show();
    }
    public void buy_2 (View V){

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        String S1 = num.getText().toString();
        String S2 = date.getText().toString();
        String S3 = cvv.getText().toString();
        if (check_1(S1) && !S1.isEmpty() && check_2(S2) && !S2.isEmpty() && check_3(S3) && !S3.isEmpty())
        {
            Toast.makeText(this, "Оплата прошла успешно", Toast.LENGTH_SHORT).show();
            ContentValues contentValues = new ContentValues();
            contentValues.put(Contract_2.DataEntry.COLUMN_STATE, 2);
            contentValues.put(Contract_2.DataEntry.COLUMN_DATE, dateText);
            database.insert(Contract_2.DataEntry.TABLE_NAME, null, contentValues);
        }
        else Toast.makeText(this, "Оплата не прошла", Toast.LENGTH_SHORT).show();
    }
}

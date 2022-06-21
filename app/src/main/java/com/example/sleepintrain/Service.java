package com.example.sleepintrain;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.sleepintrain.data.DBHelper;
public class Service extends AppCompatActivity {
    TextView time;
    Spinner type;
    String type_;
    DBHelper dbHelper;
    String selection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        time = findViewById(R.id.id);
        type = findViewById(R.id.spinner);
        setupSpinner();
        ImageView imageView_Map = findViewById(R.id.pic_map);
        imageView_Map.setImageResource(R.drawable.map);
        ImageView imageView_lk = findViewById(R.id.pic_lk);
        imageView_lk.setImageResource(R.drawable.lk);
    }
    private void setupSpinner() {
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_station, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        type.setAdapter(genderSpinnerAdapter);
        type.setSelection(2);
        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection))
                {
                    type_ = "";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                type_ = "Электрозаводская";
            }
        }
        );
    }
    boolean check  (String s)
    {
        return s.equals(selection);
    }
    public void save(View v)
    {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] info = {"station","time_1","time_2","time_3","time_4"};
        String product = "";
        Cursor cursor = db.query("TRAIN_TIME",info,null,null,null,null,null);
        try {
            int stationColumnIndex = cursor.getColumnIndex("station");
            int time_1_ColumnIndex = cursor.getColumnIndex("time_1");
            int time_2_ColumnIndex = cursor.getColumnIndex("time_2");
            int time_3_ColumnIndex = cursor.getColumnIndex("time_3");
            int time_4_ColumnIndex = cursor.getColumnIndex("time_4");
            while (cursor.moveToNext()) {
                String st = cursor.getString(stationColumnIndex);
                String time1 = cursor.getString(time_1_ColumnIndex);
                String time2 = cursor.getString(time_2_ColumnIndex);
                String time3 = cursor.getString(time_3_ColumnIndex);
                String time4 = cursor.getString(time_4_ColumnIndex);
                if(check(st))
                {
                    product += time1+" " + time2 + "\n " + time3 + " " + time4;
                }
            }
            time.setText(product);
        } finally {
            cursor.close();
        }
    }
    public void home(View v)
    {
        Intent r = new Intent(this, Lk.class);
        startActivity(r);
    }
    public void buy (View view){
        Intent B = new Intent(this, Buy.class);
        startActivity(B);
    }
}

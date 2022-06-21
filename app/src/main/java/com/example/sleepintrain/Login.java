package com.example.sleepintrain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Login extends AppCompatActivity {

    EditText female;
    EditText name;
    EditText date;
    EditText login;
    EditText pass;
    private final static String FILE = "enter.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        female = findViewById(R.id.female);
        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        login = findViewById(R.id.log);
        pass = findViewById(R.id.pass);

    }
    public void save(View v) {

        String female_ = female.getText().toString();
        String name_ = name.getText().toString();
        String date_ = date.getText().toString();
        String login_ = login.getText().toString();
        String pass_ = pass.getText().toString();
        FileOutputStream fos = null;
        Toast b = Toast.makeText(this,"Вы успешно зарегистрировались", Toast.LENGTH_SHORT);
        Toast c = Toast.makeText(this,"Ошибка ввода данных", Toast.LENGTH_SHORT);
        if (!female_.isEmpty() && !name_.isEmpty() && !date_.isEmpty() && !login_.isEmpty() && !pass_.isEmpty()) {
            String INFO = female_ + "\n" +
                    name_ + "\n" +
                    date_ + "\n" +
                    login_ + "\n" +
                    pass_ + "\n";
            try {
                fos = openFileOutput(FILE, MODE_PRIVATE);
                fos.write(INFO.getBytes());
                Toast.makeText(this, "Данные сохранены", Toast.LENGTH_SHORT).show();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                try {
                    if (fos != null)
                        fos.close();
                } catch (IOException ex) {
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            Intent start = new Intent(this, Service.class);
            startActivity(start);
            b.show();
        }
        else
            c.show();

    }
}

package com.example.sleepintrain;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    private final static String FILE = "enter.txt";
    EditText pass;
    EditText log;
    String password="";
    String login="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log = findViewById(R.id.login);
        pass = findViewById(R.id.pass);
        FileInputStream fin = null;
        String info = "";
        try {
            fin = openFileInput(FILE);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            info = text;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if (info.isEmpty()) {
            Toast first = Toast.makeText
                    (this,
                            "Привет, зарегистрируйся, чтобы пользоваться приложением",
                            Toast.LENGTH_LONG);
            Intent r = new Intent(this, Start.class);
            startActivity(r);
            first.show();
        }
        else
        {
            char[] information = info.toCharArray();
            String info_="";
            int m=0;
            for(int i=0;i<info.length();i++)
            {
                if(information[i]!='\n')
                    info_+=information[i];
                else
                {
                    if(m==3)
                    {
                        login = info_;
                    }
                    if(m==4)
                    {
                        password=info_;
                    }
                    info_="";
                    m++;
                }
            }
        }
    }

    public void log(View v)
    {

        String log_ = log.getText().toString();
        String pass_ = pass.getText().toString();
        if(password.equals(pass_)&&log_.equals(login)) {
            Intent intent = new Intent(this, Service.class);
            startActivity(intent);
        }
        else
        {
            Toast er = Toast.makeText(this,"Неверный логин/пароль",Toast.LENGTH_SHORT);
            er.show();
        }
    }

}

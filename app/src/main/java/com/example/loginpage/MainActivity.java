package com.example.loginpage;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button b1 = findViewById(R.id.button);
        Button b2 = findViewById(R.id.button2);
        EditText e1 = findViewById(R.id.editTextText3);
        EditText e2 = findViewById(R.id.editTextText4);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Second.class);
                startActivity(i);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                if (s1.equals("")||s2.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Fill all", Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteDatabase data = openOrCreateDatabase("RKGIT",MODE_PRIVATE,null);
                    data.execSQL("CREATE TABLE IF NOT EXISTS NET(NAME VARCHAR,PASSWORD VARCHAR,EMAIL VARCHAR)");
                    String query = "SELECT * FROM NET WHERE (NAME = '"+s1+"' AND EMAIL = '"+s2+"')";
                    Cursor cursor = data.rawQuery(query, null);
                    if (cursor.getCount() > 0)
                    {
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(MainActivity.this, Third.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Denied", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
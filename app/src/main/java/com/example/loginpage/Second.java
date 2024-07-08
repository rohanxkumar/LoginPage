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

public class Second extends AppCompatActivity {
    Button b1, b2;
    EditText e1,e2,e3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        Button b1 = findViewById(R.id.button3);
        Button b2 = findViewById(R.id.button4);
        EditText e1 = findViewById(R.id.editTextText);
        EditText e2 = findViewById(R.id.editTextText2);
        EditText e3 = findViewById(R.id.editTextText5);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString().trim();
                if (s1.equals("")||s2.equals("")||s3.equals(""))
                {
                    Toast.makeText(Second.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteDatabase data = openOrCreateDatabase("RKGIT",MODE_PRIVATE,null);
                    data.execSQL("CREATE TABLE IF NOT EXISTS NET(NAME VARCHAR,PASSWORD VARCHAR,EMAIL VARCHAR)");
                    String query = "SELECT * FROM NET WHERE (NAME = '"+s1+"' AND EMAIL = '"+s3+"')";
                    Cursor cursor = data.rawQuery(query, null);
                    if (cursor.getCount() > 0)
                    {
                        Toast.makeText(Second.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        data.execSQL("INSERT INTO NET VALUES('"+s1+"','"+s2+"','"+s3+"')");
                        Toast.makeText(Second.this, "Database Updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Second.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }

                }


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Second.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
package com.example.suraksha;
import com.example.suraksha.DatabaseHelper;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText email_ip,password_ip;
    TextView register=(TextView)findViewById(R.id.register_text);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login=(Button)findViewById(R.id.login_button);


        email_ip=(EditText) findViewById(R.id.email_input);
        password_ip=(EditText)findViewById(R.id.password_input);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=email_ip.getText().toString();
                String password=password_ip.getText().toString();

                startActivity(new Intent(MainActivity.this, Signin.class));

                Context context = null;
                DatabaseHelper dbHelper = new DatabaseHelper(context);
                SQLiteDatabase db = dbHelper.getReadableDatabase();



                if(DatabaseHelper.checkLogin(db,email,password))
                {

                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                }
                finish();

            }

        });


    }
}
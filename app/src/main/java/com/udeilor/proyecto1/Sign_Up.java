package com.udeilor.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Sign_Up extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Button login =(Button) findViewById(R.id.login);
        Button signup=(Button) findViewById(R.id.signup);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent l=new Intent(Sign_Up.this, MainActivity.class);
                startActivity(l);
            }

        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View sign){
                Intent s=new Intent(Sign_Up.this, Sign_Up.class);
                startActivity(s);
            }

        });
    }
}

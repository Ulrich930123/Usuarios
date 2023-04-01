package com.udeilor.proyecto1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Log_in extends AppCompatActivity {
    Button guardar;
    EditText txtUsername;
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button login =(Button) findViewById(R.id.login);
        Button signup=(Button) findViewById(R.id.signup);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent l=new Intent(Log_in.this, Log_in.class);
                startActivity(l);
            }

        });
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View sign){
                Intent s=new Intent(Log_in.this, Sign_Up.class);
                startActivity(s);
            }

        });

        guardar=findViewById(R.id.send);

        txtUsername=findViewById(R.id.user);
        txtPassword=findViewById(R.id.pass123);

        guardar.setOnClickListener(new View.OnClickListener() {
            String username="";
            String pass="";
            @Override
            public void onClick(View view) {
                username = txtUsername.getText().toString();
                pass = txtPassword.getText().toString();

                if (username.equals("")) {
                    txtUsername.setError("ingrese un nombre de usuario");
                    txtUsername.requestFocus();
                } else if (username.length() < 4) {
                    txtUsername.setError("debe escribir mas de 4 caracteres");
                    txtUsername.requestFocus();
                }
                try {
                    AdminSQlite admin = new AdminSQlite(getApplicationContext(), "biblioteca", null, 1);
                    SQLiteDatabase db = admin.getWritableDatabase();
                    Cursor fila = db.rawQuery("select *  from usuario where  username=" + "\"" + username + "\"", null);
                    if (fila.moveToFirst()) {

                        if (pass.equals(fila.getString(3))) {
                            db.close();
                            txtUsername.setText(Boolean.toString(pass.equals(fila.getString(3)))+"hola");
                            Intent a = new Intent(Log_in.this, Usuario.class);
                            startActivity(a);
                        }
                    else {
                        Toast.makeText(Log_in.this, "La combinacion usuario contraseña no existe en el sistema", Toast.LENGTH_SHORT);
                        db.close();
                    }}


                } catch (Exception e) {
                    Toast.makeText(Log_in.this, "La combinacion usuario contraseña no existe en el sistema", Toast.LENGTH_SHORT);
                }

            } });

    }
}
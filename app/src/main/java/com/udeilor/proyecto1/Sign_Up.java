package com.udeilor.proyecto1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sign_Up extends AppCompatActivity {

    Button guardar;
    EditText txtUsername;
    EditText txtEmail;
    EditText txtPassword;
    EditText txtPassConfirm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        Button login =(Button) findViewById(R.id.login);
        Button signup=(Button) findViewById(R.id.signup);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent l=new Intent(Sign_Up.this, Log_in.class);
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

        guardar=findViewById(R.id.guardar);
        txtUsername=findViewById(R.id.TextPersonName);
        txtEmail=findViewById(R.id.EmailAddress);
        txtPassword=findViewById(R.id.TextPassword);
        txtPassConfirm=findViewById(R.id.txtPassConfirm);

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view){
                int cond1=0;
                int cond2=0;
                int cond3=0;
                int cond4=0;
                int cond5=0;
                AdminSQlite admin= new AdminSQlite(getApplicationContext(),"biblioteca", null, 1);
                SQLiteDatabase db= admin.getWritableDatabase();
            String username=txtUsername.getText().toString();
            if (username.equals("")){
                txtUsername.setError("ingrese un nombre de usuario");
                txtUsername.requestFocus();
            } else if (username.length()<4) {
                txtUsername.setError("debe escribir mas de 4 caracteres");
                txtUsername.requestFocus();
            }else{
                cond1=1;
            }

            // Patrón para validar el email
            Pattern pattern = Pattern
                    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

            String email=txtEmail.getText().toString();
            Matcher mather = pattern.matcher(email);

            if (!mather.find() == true) {
                txtEmail.setError("Debe ingresar el email en el formato email.dominio.com");
                txtEmail.requestFocus();

        }else{
                cond2=1;
            }
            String password=txtPassword.getText().toString();
            String passConf=txtPassConfirm.getText().toString();
            if (password.equals("")){
                txtPassword.setError("Debe escribir un password de 6 o mas letras");
                txtPassword.requestFocus();
            }else if(password.length()<6){
                txtPassword.setError("Debe escribir un password de 6 o mas letras");
                txtPassword.requestFocus();
                }else{
                cond3=1;
            }
                if (passConf.equals("")){
                    txtPassConfirm.setError("Debe escribir un password de 6 o mas letras");
                    txtPassConfirm.requestFocus();
                }else if(passConf.length()<6){
                    txtPassConfirm.setError("Debe escribir un password de 6 o mas letras");
                    txtPassConfirm.requestFocus();
                }else{
                    cond4=1;
                }
                if (password.equals(passConf)) {
                    cond5=1;
                }else{
                    txtPassword.setText("");
                    txtPassConfirm.setText("");
                    txtPassword.setError("La contraseña y su confirmacion deben ser iguales");
                    txtPassword.requestFocus();
                }
                if (cond1==1 && cond2==1 && cond3==1 && cond4==1 && cond5==1){
                    ContentValues datos= new ContentValues();
                    datos.put("username",username);
                    datos.put("correo",email);
                    datos.put("password",password);
                    db.insert("usuario", null, datos );
                    db.close();
                    txtPassConfirm.setText("");
                    txtPassword.setText("");
                    txtEmail.setText("");
                    txtUsername.setText("");
                    cond1=0;
                    cond2=0;
                    cond3=0;
                    cond4=0;
                    cond5=0;
                }


        }});
    }
}

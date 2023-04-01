package com.udeilor.proyecto1;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Usuario extends AppCompatActivity {
    EditText id, nombre, correo;
    Button eliminar, actualizar, buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usuarios);
        nombre=findViewById(R.id.TextPersonName);
        correo=findViewById(R.id.EmailAddress);
        id=findViewById(R.id.idusuario);
        eliminar=findViewById(R.id.eliminar);
        actualizar=findViewById(R.id.actualizar);
        buscar=findViewById(R.id.buscar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=nombre.getText().toString();
                AdminSQlite admin = new AdminSQlite(getApplicationContext(), "biblioteca", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                try{
                Cursor fila = db.rawQuery("select *  from usuario where  username=" + "\"" + username + "\"", null);
                if (fila.moveToFirst()) {
                  correo.setText(fila.getString(2));
                  id.setText(fila.getString(0));
                  nombre.setText(fila.getString(1));
                }}catch (Exception e){

                    Toast.makeText(Usuario.this, "valor de username no valido.", Toast.LENGTH_SHORT);
                }
                db.close();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQlite admin = new AdminSQlite(getApplicationContext(), "biblioteca", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();

                String username=nombre.getText().toString();
               try {
                   int c = db.delete("usuario", "username=" + "\"" + username + "\"", null);
                   if (c > 0) {
                       Toast.makeText(Usuario.this, "Usuario eliminado con exito.", Toast.LENGTH_SHORT);
                   } else {
                       Toast.makeText(Usuario.this, "Usuario no existe.", Toast.LENGTH_SHORT);
                   }
                   id.setText("");
                   correo.setText("");
                   nombre.setText("");

                   db.close();
               }
               catch(Exception e){
                   Toast.makeText(Usuario.this, "Error consultando delete.", Toast.LENGTH_SHORT);
                }

            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQlite admin = new AdminSQlite(getApplicationContext(), "biblioteca", null, 1);
                SQLiteDatabase db = admin.getWritableDatabase();
                String username=nombre.getText().toString();
                String email=correo.getText().toString();

                ContentValues updateUsu=new ContentValues();
                updateUsu.put("username",username);
                updateUsu.put("correo",email);
                db.update("usuario",updateUsu,"username=" + "\"" + username + "\"", null);
                db.close();
            }
        });

    }
}

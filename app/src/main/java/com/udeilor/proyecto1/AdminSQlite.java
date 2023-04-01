package com.udeilor.proyecto1;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQlite extends SQLiteOpenHelper {
    public AdminSQlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table usuario(_id integer primary key AUTOINCREMENT, username text, correo text, password text)");


    }

    @Override
    public  void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("drop table if exists usuario");
        db.execSQL("create table usuario(_id integer primary key AUTOINCREMENT, username text, correo text, password text)");

    }
}

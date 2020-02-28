package com.example.ex5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class database extends SQLiteOpenHelper {
    public database(Context context) {
        super(context, "employee", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table employee(name varchar(20),gender varchar(1),code varchar(10),department varchar(20),salary int(6))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists employee");
        onCreate(db);
    }

    public void insert(int code,String name, String department,int salary,String gender){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("code",code);
        cv.put("name",name);
        cv.put("department",department);
        cv.put("salary",salary);
        cv.put("gender",gender);
        db.insert("employee",null,cv);

    }
    public void update(int code,String name,String department,int salary,String gender){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("code",code);
        cv.put("name",name);
        cv.put("department",department);
        cv.put("salary",salary);
        cv.put("gender",gender);
        db.update("employee",cv,"code=?",new String[]{Integer.toString(code)});
    }
    public void delete(int code){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete("employee","code=?",new String[]{Integer.toString(code)});
    }
    public Cursor display(int code){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.rawQuery("select * from employee where code="+Integer.toString(code),null);
    }
}
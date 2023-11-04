package com.example.sqlliitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="student.db";
    public static final String TABLE_NAME="student_table";
    public static final String COL1="ID";
    public static final String COL2="name";
    public static final String COL3="lastname";
    public static final String COL4="marks";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,  null,  1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+
                " NAME TEXT,LASTNAME TEXT,MARKS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(String name,String lastname,String marks){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,name);
        contentValues.put(COL3,lastname);
        contentValues.put(COL4,marks);
        Long success = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(success==-1)
        {
            return false;
        }
        else{
            return true;
        }

    }
    public Cursor getAllData()
    {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cur = myDb.rawQuery("select * from "+ TABLE_NAME,null);
        return cur;
    }
    public boolean updateData(String id,String name,String lastname,String marks)
    {
        SQLiteDatabase updateData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,id);
        contentValues.put(COL2,name);
        contentValues.put(COL3,lastname);
        contentValues.put(COL4,marks);
        updateData.update(TABLE_NAME,contentValues,"ID=?",new String[]{id});
        return true;
    }
    public Integer deleteData(String id)
    {
        SQLiteDatabase deleteData = this.getWritableDatabase();
        return deleteData.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

}

package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper1 extends SQLiteOpenHelper {

    // database connection class for appointments list
    private static final String Table_name1 = "appointments";
    private static final String COL10 = "id";
    private static final String COL11 = "detail";
    private static final String COL12 = "date";
    private static final String COL13 = "time";

    public DBHelper1(Context context){
        super(context, Table_name1, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//create table
        String create1 = "create table " + Table_name1 + " (id integer primary key autoincrement , " + COL11 + " TEXT , "+COL12+" TEXT , "+COL13+" TEXT)";
        db.execSQL(create1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addAppointment(String detail, String date, String time){
//add appointments
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();

        cv.put(COL11, detail);
        cv.put(COL12, date);
        cv.put(COL13, time);

        long result = db.insert(Table_name1,null,cv);

        if(result == -1)
            return false;
        else
            return true;

    }


    public Cursor getAppointment(){
//get list of all appointments
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "select * from "+ Table_name1 ;

        Cursor data = db.rawQuery(sql,null);

        return data;

    }

    public void deleteAppointment(int id){
        //delete the appointment
        SQLiteDatabase db=this.getWritableDatabase();


        String sql="DELETE FROM "+Table_name1+" WHERE id="+id+";";
        db.execSQL(sql);

        Log.i("result",sql);
    }

    public boolean updateAppointment(String item,String date,String time,int id){

        //update the appointment details
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put(COL11,item);
        data.put(COL12,date);
        data.put(COL13,time);
        long result=db.update(Table_name1, data, "id=" + id, null);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
}

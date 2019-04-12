package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

// database connection class for shopping list

    private static final String dbName = "ToDoList";
    private static final String Table_name = "shopping";
    private static final String COL0 = "id";
    private static final String COL1 = "Item_name";
    private static final String COL2 = "quantity";


    public DBHelper(Context context){
        super(context, Table_name, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//create table
        String create = "create table " + Table_name + " (id integer primary key autoincrement , " + COL1 + " TEXT , "+COL2+" TEXT)";
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public boolean addItem(String name, String quantity){
//add item to table
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();

        cv.put(COL1, name);
        cv.put(COL2, quantity);

        long result = db.insert(Table_name,null,cv);

        if(result == -1)
            return false;
        else
            return true;




    }


    public Cursor getItems(){
//get the list of all items to table
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "select * from "+ Table_name ;

         Cursor data = db.rawQuery(sql,null);

         return data;

    }

    public void deleteItem(int id){

        //delete corresonding item from table and list
        SQLiteDatabase db=this.getWritableDatabase();


        String sql="DELETE FROM "+Table_name+" WHERE id="+id+";";
        db.execSQL(sql);

        Log.i("result",sql);
    }

    public boolean updateItem(String item,String quantity,int id){

        //update the details of a particular list item
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put(COL1,item);
        data.put(COL2,quantity);
        long result=db.update(Table_name, data, "id=" + id, null);
        //String strSQL = "UPDATE "+Table_name+" SET name "+name+" WHERE id = "+id;
        //db.rawQuery(strSQL,null);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
}

package it.rmu.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by worawith on 8/22/2016.
 */
public class MyDBManager {

    private SQLiteDatabase db;
    private MyDBHelper helper;

    // constructor method
    public MyDBManager(Context context){
        // สั่งให้คลาส MyDBHelper ทำงาน
        helper=new MyDBHelper(context);
    }

    // method เปิด db
    public void openDB(){
        // อ่าน เขียน db ได้
        db=helper.getWritableDatabase();
    }

    // method ปิด db
    public void closeDB(){
        helper.close();
    }

    // method เพิ่มข้อมูลเข้า db
    public void addDB(String std_id,String std_name,String std_email,String std_tel){
        ContentValues values=new ContentValues();
        values.put(helper.col_stdID,std_id);
        values.put(helper.col_stdName,std_name);
        values.put(helper.col_stdEmail,std_email);
        values.put(helper.col_stdTel,std_tel);

        // เพิ่มข้อมูล
        db.insert(helper.TBName,null,values);
    }

    // method แก้ไข db
    public void updateDB(String std_id,String std_name,String std_email,String std_tel){
        ContentValues values=new ContentValues();
        values.put(helper.col_stdName,std_name);
        values.put(helper.col_stdEmail,std_email);
        values.put(helper.col_stdTel,std_tel);

        //แก้ไขข้อมูล
        db.update(helper.TBName,values,helper.col_stdID+"="+std_id,null);

    }

    //ลบข้อมูล db
    public void deleteDB(String std_id){
        //ลบข้อมูล
        db.delete(helper.TBName,helper.col_stdID+"="+std_id,null);
    }

    // method ดึงขัอมูลทั้งหมด
    public Cursor queryAll(){
        String[] column={helper.col_stdID,helper.col_stdName,helper.col_stdEmail,helper.col_stdTel};

        //ดึงข้อมูล
       return db.query(helper.TBName,column,null,null,null,null,null);
    }

    // method ดึงข้อมูลตามเงื่อนไข
    public Cursor querySelection(String std_id){
        String[] column={helper.col_stdID,helper.col_stdName,helper.col_stdEmail,helper.col_stdTel};
        // ดึงข้อมูล ตามเงื่อนไข
        return  db.query(helper.TBName,column,helper.col_stdID+"="+std_id,null,null,null,null);
    }




}

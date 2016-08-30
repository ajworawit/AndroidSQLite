package it.rmu.mydatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by worawith on 8/22/2016.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    // ประกาศตัวแปร เพื่อนำไปสร้าง db
    public static String DBname="StudentDB";
    public static String TBName="tbstudent";
    public static String col_stdID="_std_id";
    public static String col_stdName="std_name";
    public static String col_stdEmail="std_email";
    public static String col_stdTel="std_tel";

    private static int db_version=2;

    // ประกาศตัวแปร สร้าง table คำสั่ง sql สร้าง table
    private static String createTable="CREATE TABLE "+TBName+" ("+
            col_stdID+" integer PRIMARY KEY,"+
            col_stdName+" varchar(50) not null,"+
            col_stdEmail+" varchar(50) null,"+
            col_stdTel+" varchar(10) null)";


    // constructor
    // method นี้ สร้าง db
    public MyDBHelper(Context context) {
        // สร้าง db
        super(context, DBname, null, db_version);
    }

    // method นี้ สร้าง table
    @Override
    public void onCreate(SQLiteDatabase db) {
        // สร้าง table
        db.execSQL(createTable);
    }

    // method นี้ ทำการ drop และสร้าง table ใหม่
    // method นี้ จะทำงานเมื่อ db version เปลี่ยน
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // สั่งให้ drop table
        db.execSQL("DROP TABLE IF EXISTS "+TBName);
        // สั่งให้สร้าง table ใหม่
        onCreate(db);
    }

}

package it.rmu.mydatabase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MyDBHelper helper;
    MyDBManager dbm;

    Cursor cursor;

    ListView lv;

    String[] menu={"update","delete"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.lv);

        dbm=new MyDBManager(getApplicationContext());
        // เปิด db
        dbm.openDB();

        // เก็บข้อมูลที่ได้จากการ query ข้อมูลมาเก็บไว้ที่ corsor
        cursor=dbm.queryAll();

        MyAdapter adapter=new MyAdapter(getApplicationContext(),cursor);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                cursor.moveToPosition(i);
                String id=cursor.getString(0);

                Toast.makeText(getApplicationContext(),""+id,Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(getApplicationContext(),DetileActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);

            }
        });


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                cursor.moveToPosition(i);
                final String id=cursor.getString(0);

                AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Menu");
                builder.setItems(menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selected=menu[i];
                        switch (i){
                            case 0:
                                Toast.makeText(MainActivity.this,"update", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                                intent.putExtra("id",id);
                                startActivity(intent);
                                break;
                            case 1:
                                Toast.makeText(MainActivity.this,"delete", Toast.LENGTH_SHORT).show();
                                // ลบข้อมูล
                                dbm.deleteDB(id);

                                // จากนั้น สั่งให้ adapter ดึงข้อมูลมาแสดงใหม่
                                cursor=dbm.queryAll();
                                MyAdapter adapter=new MyAdapter(getApplicationContext(),cursor);
                                lv.setAdapter(adapter);
                                break;
                        }

                    }
                });
                builder.create();
                builder.show();
                return true;
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        cursor=dbm.queryAll();
        MyAdapter adapter=new MyAdapter(getApplicationContext(),cursor);
        lv.setAdapter(adapter);

    }
}

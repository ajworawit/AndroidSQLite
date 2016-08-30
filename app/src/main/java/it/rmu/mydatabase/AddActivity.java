package it.rmu.mydatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    MyDBHelper helper;
    MyDBManager dbm;

    EditText id,name,email,tel;
    Button ok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_layout);

        // เปิด db
        dbm=new MyDBManager(getApplicationContext());
        dbm.openDB();

        id=(EditText)findViewById(R.id.edt_id);
        name=(EditText)findViewById(R.id.edt_name);
        email=(EditText)findViewById(R.id.edt_email);
        tel=(EditText)findViewById(R.id.edt_tel);
        ok=(Button)findViewById(R.id.btn_ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String v_id=id.getText().toString();
                String v_name=name.getText().toString();
                String v_email=email.getText().toString();
                String v_tel=tel.getText().toString();

                // เพิ่มข้อมูล
                dbm.addDB(v_id,v_name,v_email,v_tel);

                // เปลี่ยนหน้า
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);


            }
        });





    }

    @Override
    protected void onResume() {
        super.onResume();
        id.setText("");
        id.requestFocus();
        name.setText("");
        email.setText("");
        tel.setText("");
    }
}

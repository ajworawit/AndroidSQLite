package it.rmu.mydatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    MyDBHelper helper;
    MyDBManager dbm;
    Cursor cursor;
    EditText id,name,email,tel;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // เปิด db
        dbm=new MyDBManager(getApplicationContext());
        dbm.openDB();

        name=(EditText)findViewById(R.id.edt_name);
        email=(EditText)findViewById(R.id.edt_email);
        tel=(EditText)findViewById(R.id.edt_tel);
        ok=(Button)findViewById(R.id.btn_ok);

        Bundle bundle=getIntent().getExtras();
        final String v_id=bundle.getString("id");
        cursor=dbm.querySelection(v_id);
        cursor.moveToFirst();

        name.setText(""+cursor.getString(1));
        email.setText(""+cursor.getString(2));
        tel.setText(""+cursor.getString(3));


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String v_name=name.getText().toString();
                String v_email=email.getText().toString();
                String v_tel=tel.getText().toString();

                dbm.updateDB(v_id,v_name,v_email,v_tel);

                finish();

            }
        });


    }
}

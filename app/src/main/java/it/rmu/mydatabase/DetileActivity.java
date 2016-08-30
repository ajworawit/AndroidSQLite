package it.rmu.mydatabase;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetileActivity extends AppCompatActivity {

    MyDBHelper helper;
    MyDBManager dbm;
    Cursor cursor;

    TextView tv_id,tv_name,tv_email,tv_tel;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detile);

        tv_id=(TextView)findViewById(R.id.tv_id);
        tv_name=(TextView)findViewById(R.id.tv_name);
        tv_email=(TextView)findViewById(R.id.tv_email);
        tv_tel=(TextView)findViewById(R.id.tv_tel);
        ok=(Button)findViewById(R.id.btn_ok);

        dbm=new MyDBManager(getApplicationContext());
        dbm.openDB();

        Bundle bundle=getIntent().getExtras();
        String id=bundle.getString("id");

        cursor=dbm.querySelection(id);

        cursor.moveToFirst();

        String std_id=cursor.getString(0);
        String std_name=cursor.getString(1);
        String std_email=cursor.getString(2);
        String std_tel=cursor.getString(3);

        tv_id.setText(std_id);
        tv_name.setText(std_name);
        tv_email.setText(std_email);
        tv_tel.setText(std_tel);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}

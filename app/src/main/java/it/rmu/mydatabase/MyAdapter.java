package it.rmu.mydatabase;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by worawith on 8/19/2016.
 */
public class MyAdapter extends BaseAdapter {

    Context ct;
    Cursor cursor;

    // constructor method
    public MyAdapter(Context ct,Cursor cursor){
        this.ct=ct;
        this.cursor=cursor;
    }

    @Override
    public int getCount() {
        // จำนวนข้อมูลทั้งหมด
        return cursor.getCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // เช็คว่ามี view ใน adapter หรือยัง
        if (view==null) {
            // นำ layout item ใส่ใน view
            view=inflater.inflate(R.layout.layout_list_item,viewGroup,false);
        }

        // อ้างไปหา id ใน layout
        ImageView logo=(ImageView)view.findViewById(R.id.img_logo);
        TextView title=(TextView)view.findViewById(R.id.tv_title);
        TextView item=(TextView)view.findViewById(R.id.tv_item);

        // ชี้ cursor มาที่ตำแหน่งของ row
        cursor.moveToPosition(i);
        // ชี้ cursor ไปยัง culumn ที่ต้องการ
        String name=cursor.getString(1);

        // กำหนดข้อมูล
        title.setText(name);
        item.setText(""+i);

        return view;
    }
}

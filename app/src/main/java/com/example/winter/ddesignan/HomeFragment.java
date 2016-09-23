package com.example.winter.ddesignan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.winter.sql.DbUtils;
import com.example.winter.sql.HoardDbOpenHelper;
import com.example.winter.uiUtils.uiUtilsTool;

import java.util.HashSet;
import java.util.Map;

/**
 * Created by WINTER on 2016/9/19.
 */
public class HomeFragment extends Fragment {
    private TextView textView;
    private Button b1, b2, b3;
    private ListView lv;

    private Cursor cursor;

    public static HomeFragment getIntent(String txt) {
        Bundle b = new Bundle();
        b.putString("txt", txt);
        HomeFragment hf = new HomeFragment();
        hf.setArguments(b);
        return hf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.item, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lv = (ListView) view.findViewById(R.id.lv);
        b1 = (Button) view.findViewById(R.id.b1);
        b2 = (Button) view.findViewById(R.id.b2);
        b3 = (Button) view.findViewById(R.id.b3);
        init();
    }

    public void init() {
        b1.setOnClickListener(new MyClick());
        b2.setOnClickListener(new MyClick());
        b3.setOnClickListener(new MyClick());
        cursor = HoardDbOpenHelper.getIntent().getWritableDatabase().query(HoardDbOpenHelper.TABLE_NAME, null, null, null, null,
                null, null);
        MyAdapter adapter = new MyAdapter(cursor);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    class MyClick implements View.OnClickListener {
        Intent intent = new Intent(uiUtilsTool.getBasAppContext(), DataAct.class);

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.b1:
                    intent.putExtra("flag", "1");
                    startActivity(intent);
                    break;
                case R.id.b2:
                    intent.putExtra("flag", "2");
                    startActivity(intent);
                    break;
                case R.id.b3:
                    intent.putExtra("flag", "3");
                    startActivity(intent);
                    break;
            }
        }
    }

    class MyAdapter extends BaseAdapter {
        Cursor cursor;

        public MyAdapter(Cursor cursor) {
            this.cursor = cursor;
        }

        @Override
        public int getCount() {
            return cursor.getCount();
        }

        @Override
        public Object getItem(int position) {
            return cursor.getPosition();
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = View.inflate(uiUtilsTool.getBasAppContext(), R.layout.listitem, null);
            TextView tv = (TextView) convertView.findViewById(R.id.item_txt);

            cursor.moveToPosition(position);
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            tv.setText(content + time);



            return convertView;

        }
    }
}

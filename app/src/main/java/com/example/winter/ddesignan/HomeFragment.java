package com.example.winter.ddesignan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by WINTER on 2016/9/19.
 */
public class HomeFragment extends Fragment {

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
        TextView textView = (TextView) view.findViewById(R.id.item_txt);
        String txt = getArguments().getString("txt");
        textView.setText(txt);
    }
}

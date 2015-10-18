package com.example.wu_.testapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class Myadapter extends BaseAdapter {
    private class holder {
        TextView course;
        ImageView photo;
        TextView mytext;
        ImageButton button;
    }

    private ArrayList<HashMap<String, Object>> myarray;
    private String[] keystring;
    private int[] valueint;
    private LayoutInflater myinflater;
    private Context mycontext;
    private holder myholder;

    public Myadapter(Context c, ArrayList<HashMap<String, Object>> oriList, int resource, String[] from, int[] to) {
        myarray = oriList;
        mycontext = c;
        //加载我们传入的布局
        myinflater = LayoutInflater.from(c);
        keystring = new String[from.length];//复制数组！！！！！！不知道复制的作用
        valueint = new int[to.length];
        System.arraycopy(from, 0, keystring, 0, from.length);
        System.arraycopy(to, 0, valueint, 0, to.length);
    }
    @Override
    public int getCount() {
        return myarray.size();
    }
    @Override
    public Object getItem(int position) {
        return myarray.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void removeItem(int position) {
        //删除一个列表用的
        myarray.remove(position);
        this.notifyDataSetChanged();//动态更新listview，不用重新刷新listview
    }
    @Override
    //getview()方法是在每个子项被滚动到屏幕内的时候被调用，通过getitem（）方法得到当前项的实例，我在上面已经加载了布局。
    //然后调用findviewbyid获取textview button等的实例，调用set方法设置显示的内容，然后返回布局。
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView != null) {
            myholder = (holder) convertView.getTag();
        } else {
            convertView = myinflater.inflate(R.layout.list_item, null);
            myholder = new holder();//使用myholder是为了提高效率
            myholder.course = (TextView) convertView.findViewById(valueint[0]);
            myholder.photo = (ImageView) convertView.findViewById(valueint[1]);
            myholder.mytext = (TextView) convertView.findViewById(valueint[2]);
            myholder.button = (ImageButton) convertView.findViewById(valueint[3]);
            convertView.setTag(myholder);
        }

        myholder.button.setTag(position);
        myholder.button.setOnClickListener(onClickListener);

        HashMap<String, Object> myarray2 = myarray.get(position);
        if (myarray2 != null) {
            String number = (String) myarray2.get(keystring[0]);//hashmap的get方法获取当前key的value值
            int mid = (Integer) myarray2.get(keystring[1]);
            //因为我们在textview中直接申明为string所以得到string，而在此是imageview的id地址，所以是int
            String course = (String) myarray2.get(keystring[2]);
            int bid = (Integer) myarray2.get(keystring[3]);
            myholder.course.setText(number);
            myholder.photo.setImageDrawable(myholder.photo.getResources().getDrawable(mid));
            myholder.mytext.setText(course);
            myholder.button.setImageDrawable(myholder.button.getResources().getDrawable(bid));
        }
        return convertView;
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int vid = v.getId();
            int position = (int)v.getTag();
            if (vid == myholder.button.getId())
                removeItem(position);
        }
    };

//    class mybuttonListener implements View.OnClickListener {
//        private int position;
//
//        mybuttonListener(int pos) {
//            position = pos;
//        }
//
//        @Override
//        public void onClick(View v) {
//
//
//        }
//    }
}
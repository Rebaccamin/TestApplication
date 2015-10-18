package com.example.wu_.testapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class Second extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_1);
        // 关联Layout中的ListView
        ListView mylist = (ListView) findViewById(R.id.list);
        // 生成动态数组，加入数据
        ArrayList<HashMap<String, Object>> remoteItem = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("number", "Course" + i);
            map.put("photo", R.drawable.book);
            map.put("coursename", "数据库");
            map.put("button", R.drawable.delete);
            remoteItem.add(map);
        }
        // 生成适配器的Item和动态数组对应的元素
        Myadapter listItemAdapter = new Myadapter(
                this,
                remoteItem,//数据源
                R.layout.list_item,//ListItem的XML实现

                //动态数组与ImageItem对应的子项
                new String[]{"number", "photo", "coursename","button"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] {R.id.course,R.id.myimage,R.id.mytext,R.id.MoD}
        );

     mylist.setAdapter(listItemAdapter);
    }

}



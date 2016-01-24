package com.nguyenthanhson.newsapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nguyenthanhson.newsapp.R;
import com.nguyenthanhson.newsapp.adapter.MyListAdapter;
import com.nguyenthanhson.newsapp.model.Item;
import com.nguyenthanhson.newsapp.model.Variables;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listViewAllNewspaper;
    MyListAdapter myListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
    }

    private void setUpView() {
        listViewAllNewspaper=(ListView)findViewById(R.id.list_newspaper);
        setAdapterForListView();
        registerEvent();
    }

    private void registerEvent() {
        listViewAllNewspaper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this,CategoryActivity.class);
                intent.putExtra(Variables.PAPER,position);
                startActivity(intent);
            }
        });

    }

    private void setAdapterForListView() {
        ArrayList<Item> listItem=new ArrayList<Item>();
        Item vietnamnet=new Item(R.drawable.vietnamnet,"VietNamNet");
        Item vnexpress=new Item(R.drawable.vnexpress,"VnExpress");
        listItem.add(vietnamnet);
        listItem.add(vnexpress);
        myListAdapter=new MyListAdapter(MainActivity.this,R.layout.list_item,listItem);
        listViewAllNewspaper.setAdapter(myListAdapter);
    }
}

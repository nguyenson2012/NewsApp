package com.nguyenthanhson.newsapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenthanhson.newsapp.R;
import com.nguyenthanhson.newsapp.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by SON on 1/18/2016.
 */
public class MyListAdapter extends ArrayAdapter<Item> {
    private Activity context;
    private List<Item> listItem=new ArrayList<Item>();
    public MyListAdapter(Activity context, int resource, ArrayList<Item> objects) {
        super(context, resource, objects);
        this.context=context;
        listItem=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=context.getLayoutInflater();
            convertView=inflater.inflate(R.layout.list_item,parent,false);
        }
        TextView tv_title=(TextView)convertView.findViewById(R.id.tv_title);
        tv_title.setText(listItem.get(position).getTitle());
        ImageView imgNews=(ImageView)convertView.findViewById(R.id.img_article);
        imgNews.setImageResource(listItem.get(position).getImage());


        return convertView;
    }
}

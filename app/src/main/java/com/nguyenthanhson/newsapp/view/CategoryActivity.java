package com.nguyenthanhson.newsapp.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nguyenthanhson.newsapp.R;
import com.nguyenthanhson.newsapp.adapter.MyListAdapter;
import com.nguyenthanhson.newsapp.model.ArticleInfo;
import com.nguyenthanhson.newsapp.model.Item;
import com.nguyenthanhson.newsapp.model.Variables;
import com.nguyenthanhson.newsapp.utility.RSSParser;
import com.nguyenthanhson.newsapp.utility.XMLRSSParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SON on 1/18/2016.
 */
public class CategoryActivity extends Activity {
    ListView listViewCategory;
    TextView tvNameNewspaper;
    MyListAdapter myListAdapter;
    ArrayList<ArticleInfo> listArticle= new ArrayList<ArticleInfo>();
    Intent intent;
    int paper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_category_activity);
        setUpView();
        getDataFromIntent();
        setAdapterForListCategory();
        registerEventForListView();
    }

    private void getDataFromIntent() {
        Bundle bundle=getIntent().getExtras();
        paper=bundle.getInt(Variables.PAPER);
        tvNameNewspaper.setText(Variables.NEWSPAPER[paper]);

    }

    private void registerEventForListView() {
        listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent=new Intent(CategoryActivity.this,NewsOfCategoryActivity.class);
                intent.putExtra(Variables.PAPER,paper);
                intent.putExtra(Variables.CATEGORY, position);
                startActivity(intent);
            }
        });
    }

    private void setAdapterForListCategory() {
        ArrayList<Item> listCategory=new ArrayList<Item>();
        int img_id=R.drawable.vietnamnet;
        switch (paper){
            case 0:
                img_id=R.drawable.vietnamnet;
                break;
            case 1:
                img_id=R.drawable.vnexpress;
                break;
        }
        for(String category: Variables.CATEGORIES[paper]){
            Item item=new Item(img_id,category);
            listCategory.add(item);
        }
        myListAdapter=new MyListAdapter(CategoryActivity.this,R.layout.list_item,listCategory);
        listViewCategory.setAdapter(myListAdapter);
    }

    private void setUpView() {
        listViewCategory=(ListView)findViewById(R.id.list_category);
        tvNameNewspaper=(TextView)findViewById(R.id.nameOfNewsPaper);
    }


}

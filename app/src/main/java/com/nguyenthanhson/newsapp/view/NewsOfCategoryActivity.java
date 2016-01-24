package com.nguyenthanhson.newsapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.InstrumentationTestRunner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.nguyenthanhson.newsapp.R;
import com.nguyenthanhson.newsapp.adapter.MyListAdapter;
import com.nguyenthanhson.newsapp.model.ArticleInfo;
import com.nguyenthanhson.newsapp.model.Item;
import com.nguyenthanhson.newsapp.model.Variables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SON on 1/18/2016.
 */
public class NewsOfCategoryActivity extends Activity {
    ListView listViewArticle;
    List<ArticleInfo> listArticle=new ArrayList<ArticleInfo>();
    ArrayList<Item> listItem=new ArrayList<Item>();
    MyListAdapter myListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_of_category_activity);
        setUpView();
        setAdapterForListView();
        registerForEvent();
    }

    private void registerForEvent() {
        listViewArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String linkArticle=listArticle.get(position).getLink();
                Intent intent=new Intent(NewsOfCategoryActivity.this,ArticleDetailsActivity.class);
                intent.putExtra(Variables.ARTICLE,linkArticle);
                startActivity(intent);
            }
        });
    }

    private void setAdapterForListView() {
        Bundle bundle=getIntent().getExtras();
        int paper=bundle.getInt(Variables.PAPER);
        int img_id=R.drawable.vietnamnet;
        int keyOfPaper=bundle.getInt(Variables.CATEGORY);
        listArticle=Variables.newsHashMap.get(paper * 1000 + keyOfPaper);
        switch (paper){
            case 0:
                img_id=R.drawable.vietnamnet;
                break;
            case 1:
                img_id=R.drawable.vnexpress;
                break;
        }
        for(ArticleInfo articleInfo:listArticle){
            Item item=new Item(img_id,articleInfo.getTitle());
            listItem.add(item);
        }

        myListAdapter=new MyListAdapter(NewsOfCategoryActivity.this,R.layout.list_item,listItem);
        listViewArticle.setAdapter(myListAdapter);

    }

    private void setUpView() {
        listViewArticle=(ListView)findViewById(R.id.list_news_of_category);
    }
}

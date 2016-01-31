package com.nguyenthanhson.newsapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.test.InstrumentationTestRunner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nguyenthanhson.newsapp.R;
import com.nguyenthanhson.newsapp.adapter.ArticleAdapter;
import com.nguyenthanhson.newsapp.adapter.MyListAdapter;
import com.nguyenthanhson.newsapp.model.ArticleInfo;
import com.nguyenthanhson.newsapp.model.Item;
import com.nguyenthanhson.newsapp.model.Variables;
import com.nguyenthanhson.newsapp.utility.NetworkConnection;
import com.nguyenthanhson.newsapp.utility.XMLRSSParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SON on 1/18/2016.
 */
public class NewsOfCategoryActivity extends Activity {
    ListView listViewArticle;
    int paper;
    int position;
    ArrayList<ArticleInfo> listArticle=new ArrayList<ArticleInfo>();
    ArticleAdapter myListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_of_category_activity);
        getDataFromIntent();
        setUpView();
        setAdapterForListView();
        registerForEvent();
    }

    private void getDataFromIntent() {
        Bundle bundle=getIntent().getExtras();
        paper=bundle.getInt(Variables.PAPER);
        position=bundle.getInt(Variables.CATEGORY);
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
        if (NetworkConnection.isNetworkAvailable(getApplicationContext())) {
            String local = "";
            new RetrieveFeedTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, position);
        } else {
            Toast.makeText(getApplicationContext(), "Network not available!",
                    Toast.LENGTH_LONG).show();
        }


    }

    private void setUpView() {
        listViewArticle=(ListView)findViewById(R.id.list_news_of_category);
    }
    class RetrieveFeedTask extends AsyncTask<Integer, Void, Void> {

        private Exception exception;

        protected Void doInBackground(Integer... array) {
            try {
                Log.e("link", Variables.LINKS[paper][array[0]]);
                XMLRSSParser parser=new XMLRSSParser(Variables.LINKS[paper][array[0]]);
                parser.fetchXML();
                while (parser.parsingComplete);
                listArticle = parser.getListArticle();
                Log.e("ListArticle", listArticle.size() + "");
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            myListAdapter=new ArticleAdapter(NewsOfCategoryActivity.this,R.layout.list_item,listArticle);
            listViewArticle.setAdapter(myListAdapter);

        }
    }
}

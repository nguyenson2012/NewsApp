package com.nguyenthanhson.newsapp.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenthanhson.newsapp.R;
import com.nguyenthanhson.newsapp.model.ArticleInfo;
import com.nguyenthanhson.newsapp.model.Item;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SON on 1/26/2016.
 */
public class ArticleAdapter extends ArrayAdapter<ArticleInfo> {
    private Activity context;
    private List<ArticleInfo> listItem=new ArrayList<ArticleInfo>();
    public ArticleAdapter(Activity context, int resource, ArrayList<ArticleInfo> objects) {
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
        ImageView imgArticle=(ImageView)convertView.findViewById(R.id.img_article);
        new DownloadImageTask(imgArticle).execute(listItem.get(position).getLinkImg());



        return convertView;
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}

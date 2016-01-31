package com.nguyenthanhson.newsapp.utility;

import android.util.Log;

import com.nguyenthanhson.newsapp.model.ArticleInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SON on 1/19/2016.
 */
public class XMLRSSParser {
    public static String ITEM="item";
    public static String TITLE="title";
    public static String LINK="link";
    public static String DATE="pubDate";
    public static String IMG="image";
    public static String DESCRIPTION="description";

    private static final String TAG = XMLRSSParser.class.getSimpleName();
    private ArticleInfo item;
    private ArrayList<ArticleInfo> listArticle=new ArrayList<ArticleInfo>();
    private boolean itemStarted;
    public boolean parsingComplete=true;
    private String link;
    private XmlPullParserFactory xmlFactoryObject;
    StringBuilder stringBuilder=new StringBuilder();

    public XMLRSSParser(String url){
        this.link=url;
    }
    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;

        try {
            event = myParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();

                switch (event){
                    case XmlPullParser.START_TAG:
                        if(name.equals(ITEM)){
                            itemStarted=true;
                            item=new ArticleInfo();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(itemStarted&&name.equals(TITLE)){
                             item.setTitle(text);
                        }

                        else if(itemStarted&&name.equals(LINK)){
                            item.setLink(text);
                        }
                        else if(itemStarted&&name.equals(DATE)){
                            item.setDate(text);
                        }else if(itemStarted&&name.equals(DESCRIPTION)){
                            item.setDescription(text);
                        }else if(name.equals(ITEM)){
                            item.perfectDescription();
                            listArticle.add(item);
                            itemStarted=false;

                        }
                        break;
                }
                event = myParser.next();
            }
            parsingComplete = false;
        }


        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void fetchXML() {
        try {
            InputStream stream = NetworkConnection.downloadUrlInputStream(link);
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();

            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
                    , false);
            myparser.setInput(stream, null);
            parseXMLAndStoreIt(myparser);
            stream.close();
        } catch (MalformedURLException e) {
            Log.i(TAG, "" + e.getMessage());
        } catch (IOException e) {
            Log.i(TAG, "" + e.getMessage());
        } catch (XmlPullParserException e) {
            Log.i(TAG, "" + e.getMessage());
        }

    }
    public ArrayList<ArticleInfo> getListArticle(){
        return listArticle;
    }
}

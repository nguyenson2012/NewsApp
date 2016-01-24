package com.nguyenthanhson.newsapp.utility;

import com.nguyenthanhson.newsapp.model.ArticleInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
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

    private ArticleInfo item;
    private List<ArticleInfo> listArticle=new ArrayList<ArticleInfo>();
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
                        if(name.equals(ITEM)){
                            listArticle.add(item);
                            itemStarted=false;

                        }
                        else if(itemStarted&&name.equals(TITLE)){
                             item.setTitle(text);
                        }

                        else if(itemStarted&&name.equals(LINK)){
                            item.setLink(text);
                        }

                        else if(itemStarted&&name.equals(DATE)){
                            item.setDate(text);
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
    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream stream = conn.getInputStream();
                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    parseXMLAndStoreIt(myparser);
                    stream.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
    public List<ArticleInfo> getListArticle(){
        return listArticle;
    }
}

package com.nguyenthanhson.newsapp.utility;

import com.nguyenthanhson.newsapp.model.ArticleInfo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SON on 1/18/2016.
 */
public class RSSHandler extends DefaultHandler {
    public static String ITEM="item";
    public static String TITLE="title";
    public static String LINK="link";
    public static String DATE="pubDate";

    private ArticleInfo item;
    private List<ArticleInfo> listArticle=new ArrayList<ArticleInfo>();
    private boolean itemStarted;
    StringBuilder stringBuilder=new StringBuilder();

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(itemStarted&&stringBuilder!=null){
            stringBuilder.append(ch,start,length);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(localName.equalsIgnoreCase(ITEM)){
            item=new ArticleInfo();
            itemStarted=true;
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if ((localName.equalsIgnoreCase(ITEM))){
            listArticle.add(item);
            itemStarted=false;
        }else if(localName.equalsIgnoreCase(TITLE)){
            item.setTitle(stringBuilder.toString());
        }else if(localName.equalsIgnoreCase(LINK)){
            item.setLink(stringBuilder.toString());
        }else if(localName.equalsIgnoreCase(DATE)){
            item.setDate(stringBuilder.toString());
        }
        stringBuilder=new StringBuilder();
    }
    public List<ArticleInfo> getListArticle(){
        return listArticle;
    }
}

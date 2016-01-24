package com.nguyenthanhson.newsapp.model;

/**
 * Created by SON on 1/18/2016.
 */
public class ArticleInfo {
    private String title;
    private String link;
    private String date;

    public ArticleInfo(String title, String link,String date) {
        this.title = title;
        this.link = link;
        this.date=date;
    }
    public ArticleInfo(){

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

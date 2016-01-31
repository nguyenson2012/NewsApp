package com.nguyenthanhson.newsapp.model;

/**
 * Created by SON on 1/18/2016.
 */
public class ArticleInfo {
    private String linkImg;
    private String title;
    private String link;
    private String date;
    private String description;

    public ArticleInfo(String linkImg, String title, String link, String date,String description) {
        this.linkImg = linkImg;
        this.title = title;
        this.link = link;
        this.date = date;
        this.description=description;
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

    public String getLinkImg() {
        return linkImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }
    public void perfectDescription() {
        String description = this.description;
        int index1 = description.indexOf("src=\"");
        int index2 = description.indexOf("</br>");
        this.setLinkImg(description.substring(index1+5, index2-7));
        this.setDescription(description.substring(index2+5));
    }
}

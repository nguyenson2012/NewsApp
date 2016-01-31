package com.nguyenthanhson.newsapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by SON on 1/18/2016.
 */
public class Variables {
    public static final String[] NEWSPAPER={"Vietnamnet","VnExpress"};

    public static final String[] VIETNAMNET_CATEGORIES={"Trang Chủ","Thể Thao"};
    public static final String[] VNEXPRESS_CATEGORIES={"Trang Chủ","Thời Sự"};
    public static final  String[] VIETNAMNET_LINKS={
            "http://vietnamnet.vn/rss/home.rss",
            "http://vietnamnet.vn/rss/the-thao.rss"
    };
    public static final String[] VNEXPRESS_LINKS={
            "http://vnexpress.net/rss/tin-moi-nhat.rss",
            "http://vnexpress.net/rss/thoi-su.rss"
    };

    public static final String[][] CATEGORIES={VIETNAMNET_CATEGORIES,VNEXPRESS_CATEGORIES};
    public static final String[][] LINKS={VIETNAMNET_LINKS,VNEXPRESS_LINKS};

    public static final String CATEGORY="category";
    public static final String PAPER="paper";
    public static final String ARTICLE="article";

    public static final HashMap<Integer,ArrayList<ArticleInfo>> newsHashMap=new HashMap<Integer,ArrayList<ArticleInfo>>();

}

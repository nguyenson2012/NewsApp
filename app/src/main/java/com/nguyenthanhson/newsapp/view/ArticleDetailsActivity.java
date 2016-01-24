package com.nguyenthanhson.newsapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.nguyenthanhson.newsapp.R;
import com.nguyenthanhson.newsapp.model.Variables;

/**
 * Created by SON on 1/18/2016.
 */
public class ArticleDetailsActivity extends Activity {
    String linkArticle;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity);
        getLinkFromIntent();
        loadLinkArticle();
    }

    private void loadLinkArticle() {
        webView=(WebView)findViewById(R.id.web_view);
        settingForWebView();
        webView.loadUrl(linkArticle);

    }

    private void settingForWebView() {
        webView.getSettings().setSupportZoom(true);
        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
    }

    private void getLinkFromIntent() {
        Bundle bundle=getIntent().getExtras();
        linkArticle=bundle.getString(Variables.ARTICLE);
    }
}

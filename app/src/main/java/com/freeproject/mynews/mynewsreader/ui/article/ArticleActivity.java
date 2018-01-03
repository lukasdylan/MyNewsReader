package com.freeproject.mynews.mynewsreader.ui.article;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.freeproject.mynews.mynewsreader.R;
import com.freeproject.mynews.mynewsreader.data.network.Article;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindDrawable(R.drawable.vector_back)
    Drawable backBtn;

    private Article article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0,0);
        setContentView(R.layout.activity_article);
        ButterKnife.bind(this);

        article = getIntent().getParcelableExtra("Article");

        toolbar.setTitle(article.getTitle());
        toolbar.setNavigationIcon(backBtn);
        toolbar.setNavigationOnClickListener(view1 -> onBackPressed());

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if(newProgress == progressBar.getMax()){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.setOnLongClickListener(v -> true);
        webView.setLongClickable(false);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressBar.getVisibility()==View.VISIBLE) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        webView.loadUrl(article.getUrl());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView.destroy();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0,0);
    }
}

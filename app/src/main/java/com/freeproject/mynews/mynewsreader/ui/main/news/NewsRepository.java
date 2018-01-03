package com.freeproject.mynews.mynewsreader.ui.main.news;

import android.content.Context;
import android.content.Intent;

import com.freeproject.mynews.mynewsreader.data.constant.GlobalConstant;
import com.freeproject.mynews.mynewsreader.data.network.APIService;
import com.freeproject.mynews.mynewsreader.data.network.NewsResponse;
import com.freeproject.mynews.mynewsreader.ui.article.ArticleActivity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Lukas Dylan Adisurya on 12/29/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

class NewsRepository {
    private final APIService apiService;

    NewsRepository(APIService apiService){
        this.apiService = apiService;
    }

    Observable<NewsResponse> loadNewsByCategory(String category){
        Map<String, String> params = new HashMap<>();
        params.put("apiKey", GlobalConstant.API_KEY);
        params.put("language", "en");
        params.put("category", category);
        return apiService.topHeadlineByCategory(params);
    }

    Single<Intent> toArticleActivity(Context context){
        return Single.just(new Intent(context, ArticleActivity.class));
    }
}

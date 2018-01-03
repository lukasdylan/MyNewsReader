package com.freeproject.mynews.mynewsreader.ui.main.news;

import android.content.Context;
import android.content.Intent;

import com.freeproject.mynews.mynewsreader.data.network.NewsResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Lukas Dylan Adisurya on 12/29/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

public class NewsUseCase {

    private final NewsRepository newsRepository;

    @Inject
    NewsUseCase(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    Observable<NewsResponse> loadNewsByCategory(String category){
        return newsRepository.loadNewsByCategory(category);
    }

    Single<Intent> toArticleActibity(Context context){
        return newsRepository.toArticleActivity(context);
    }
}

package com.freeproject.mynews.mynewsreader.ui.main.news;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.freeproject.mynews.mynewsreader.CoreApplication;

/**
 * Created by Lukas Dylan Adisurya on 12/29/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

public class NewsViewModelFactory implements ViewModelProvider.Factory {

    private final NewsUseCase newsUseCase;
    private final CoreApplication coreApplication;

    NewsViewModelFactory(CoreApplication coreApplication, NewsUseCase newsUseCase){
        this.coreApplication = coreApplication;
        this.newsUseCase = newsUseCase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewsViewModel.class)) {
            return (T) new NewsViewModel(coreApplication, newsUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

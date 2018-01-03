package com.freeproject.mynews.mynewsreader.ui.main.news;

import com.freeproject.mynews.mynewsreader.CoreApplication;
import com.freeproject.mynews.mynewsreader.data.network.APIService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lukas Dylan Adisurya on 12/29/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

@Module
public class NewsModule {

    @Provides
    NewsRepository provideRepository(APIService apiService){
        return new NewsRepository(apiService);
    }

    @Provides
    NewsViewModelFactory provideViewModelFactory(CoreApplication coreApplication, NewsUseCase newsUseCase){
        return new NewsViewModelFactory(coreApplication, newsUseCase);
    }
}

package com.freeproject.mynews.mynewsreader.di;

import com.freeproject.mynews.mynewsreader.ui.main.MainActivity;
import com.freeproject.mynews.mynewsreader.ui.main.MainModule;
import com.freeproject.mynews.mynewsreader.ui.main.news.NewsFragment;
import com.freeproject.mynews.mynewsreader.ui.main.news.NewsModule;
import com.freeproject.mynews.mynewsreader.ui.splash.SplashActivity;
import com.freeproject.mynews.mynewsreader.ui.splash.SplashModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Lukas Dylan Adisurya on 12/25/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

@Module
abstract class BuilderModule {
    @ContributesAndroidInjector(modules = SplashModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = NewsModule.class)
    abstract NewsFragment bindBusinessFragment();
}

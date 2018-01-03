package com.freeproject.mynews.mynewsreader.ui.splash;

import com.freeproject.mynews.mynewsreader.CoreApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lukas Dylan Adisurya on 12/26/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

@Module
public class SplashModule {

    @Provides
    SplashRepository provideRepository(){
        return new SplashRepository();
    }

    @Provides
    SplashViewModelFactory provideViewModelFactory(CoreApplication coreApplication, SplashUseCase splashUseCase){
        return new SplashViewModelFactory(coreApplication, splashUseCase);
    }
}

package com.freeproject.mynews.mynewsreader.ui.splash;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.freeproject.mynews.mynewsreader.CoreApplication;

/**
 * Created by Lukas Dylan Adisurya on 12/27/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

public class SplashViewModelFactory implements ViewModelProvider.Factory {

    private final SplashUseCase splashUseCase;
    private final CoreApplication coreApplication;

    SplashViewModelFactory(CoreApplication coreApplication, SplashUseCase splashUseCase){
        this.splashUseCase = splashUseCase;
        this.coreApplication = coreApplication;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(coreApplication, splashUseCase);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

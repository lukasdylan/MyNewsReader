package com.freeproject.mynews.mynewsreader.ui.splash;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by Lukas Dylan Adisurya on 12/27/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

public class SplashUseCase {
    private final SplashRepository splashRepository;

    @Inject
    SplashUseCase(SplashRepository splashRepository){
        this.splashRepository = splashRepository;
    }

    Single<Intent> toMainActivity(Context context){
        return splashRepository.toMainActivity(context);
    }

    Single<Intent> toIntroActivity(Context context){
        return splashRepository.toIntroActivity(context);
    }
}

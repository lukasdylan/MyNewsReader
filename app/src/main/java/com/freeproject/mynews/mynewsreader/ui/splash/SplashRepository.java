package com.freeproject.mynews.mynewsreader.ui.splash;

import android.content.Context;
import android.content.Intent;

import com.freeproject.mynews.mynewsreader.ui.intro.IntroductionActivity;
import com.freeproject.mynews.mynewsreader.ui.main.MainActivity;

import io.reactivex.Single;

/**
 * Created by Lukas Dylan Adisurya on 12/27/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

class SplashRepository {
    Single<Intent> toMainActivity(Context context){
        return Single.just(new Intent(context, MainActivity.class));
    }

    Single<Intent> toIntroActivity(Context context){
        return Single.just(new Intent(context, IntroductionActivity.class));
    }
}

package com.freeproject.mynews.mynewsreader.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.freeproject.mynews.mynewsreader.CoreApplication;
import com.freeproject.mynews.mynewsreader.data.constant.GlobalConstant;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lukas Dylan Adisurya on 12/25/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

@Module
public class AppContextModule {

    @Provides
    Context provideContext(CoreApplication coreApplication){
        return coreApplication.getApplicationContext();
    }

    @Provides
    SharedPreferences provideSharedPreferences(Context context){
        return context.getSharedPreferences(GlobalConstant.PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}

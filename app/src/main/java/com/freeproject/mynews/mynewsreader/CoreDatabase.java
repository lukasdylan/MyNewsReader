package com.freeproject.mynews.mynewsreader;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.freeproject.mynews.mynewsreader.data.constant.GlobalConstant;
import com.freeproject.mynews.mynewsreader.data.network.Article;

/**
 * Created by Lukas Dylan Adisurya on 12/26/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

//@Database(entities = {Article.class}, version = 1)
public abstract class CoreDatabase extends RoomDatabase {

    private static CoreDatabase instance;

    public static CoreDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CoreDatabase.class, GlobalConstant.DATABASE_NAME)
                    .build();
        }
        return instance;
    }

    public static void destroyInstance(){
        instance = null;
    }
}

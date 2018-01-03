package com.freeproject.mynews.mynewsreader.data.network;

import com.freeproject.mynews.mynewsreader.data.constant.GlobalConstant;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Lukas Dylan Adisurya on 12/25/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

public interface APIService {
    @GET(GlobalConstant.API_NEWS_TOPHEADLINE_ENDPOINT)
    Observable<NewsResponse> topHeadlineFromSources(@Query("sources") String sourceName, @Query("apiKey") String apiKey);

    @GET(GlobalConstant.API_NEWS_TOPHEADLINE_ENDPOINT)
    Observable<NewsResponse> topHeadlineByCategory(@QueryMap Map<String, String> params);

}

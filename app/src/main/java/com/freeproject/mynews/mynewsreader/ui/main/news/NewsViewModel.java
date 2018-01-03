package com.freeproject.mynews.mynewsreader.ui.main.news;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;

import com.freeproject.mynews.mynewsreader.data.network.Article;
import com.freeproject.mynews.mynewsreader.data.network.NewsResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lukas Dylan Adisurya on 12/29/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

public class NewsViewModel extends AndroidViewModel {

    private final NewsUseCase newsUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<List<Article>> articleList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingStatus = new MutableLiveData<>();
    private final MutableLiveData<Intent> intentResult = new MutableLiveData<>();
    private final PublishProcessor<Integer> paginator = PublishProcessor.create();
    private final MutableLiveData<Integer> currentPage = new MutableLiveData<>();

    NewsViewModel(Application application, NewsUseCase newsUseCase) {
        super(application);
        this.newsUseCase = newsUseCase;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }

    void loadNewsArticleByCategory(String category) {
        compositeDisposable.add(newsUseCase.loadNewsByCategory(category)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> loadingStatus.setValue(true))
                .doOnComplete(() -> loadingStatus.setValue(false))
                .subscribeWith(new DisposableObserver<NewsResponse>() {
                    @Override
                    public void onNext(NewsResponse newsResponse) {
                        articleList.setValue(newsResponse.getArticles());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    void loadNextPage(){
        compositeDisposable.add(paginator.onBackpressureDrop()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> currentPage.setValue(integer + 1)));
    }

    void openArticleActivity(Article article){
        compositeDisposable.add(newsUseCase.toArticleActibity(getApplication())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> intentResult.setValue(null))
                .subscribe(intent -> {
                    intent.putExtra("Article", article);
                    intentResult.setValue(intent);
                }));
    }

    MutableLiveData<List<Article>> getArticleList() {
        return articleList;
    }

    MutableLiveData<Boolean> getLoadingStatus() {
        return loadingStatus;
    }

    MutableLiveData<Intent> getIntentResult() {
        return intentResult;
    }

    MutableLiveData<Integer> getCurrentPage() {
        return currentPage;
    }
}

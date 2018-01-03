package com.freeproject.mynews.mynewsreader.ui.splash;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Lukas Dylan Adisurya on 12/26/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

public class SplashViewModel extends AndroidViewModel {

    private final SplashUseCase splashUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Boolean> loadingStatus = new MutableLiveData<>();
    private final MutableLiveData<Intent> intentResult = new MutableLiveData<>();

    SplashViewModel(@NonNull Application application, SplashUseCase splashUseCase) {
        super(application);
        this.splashUseCase = splashUseCase;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }

    void openMainActivity() {
        compositeDisposable.add(
                splashUseCase
                        .toMainActivity(getApplication())
                        .delaySubscription(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> loadingStatus.setValue(true))
                        .doAfterTerminate(() -> {
                            loadingStatus.setValue(false);
                            intentResult.setValue(null);
                        })
                        .subscribe(intentResult::setValue));
    }

    void openIntroActivity() {
        compositeDisposable.add(
                splashUseCase
                        .toIntroActivity(getApplication())
                        .delaySubscription(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(disposable -> loadingStatus.setValue(true))
                        .doAfterTerminate(() -> {
                            loadingStatus.setValue(false);
                            intentResult.setValue(null);
                        })
                        .subscribe(intentResult::setValue));
    }

    MutableLiveData<Intent> getIntentResult() {
        return intentResult;
    }
}

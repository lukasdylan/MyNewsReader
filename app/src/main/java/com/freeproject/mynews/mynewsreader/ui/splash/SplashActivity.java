package com.freeproject.mynews.mynewsreader.ui.splash;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.freeproject.mynews.mynewsreader.R;
import com.freeproject.mynews.mynewsreader.data.constant.GlobalConstant;
import com.freeproject.mynews.mynewsreader.data.preferences.AppPreferences;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashActivity extends AppCompatActivity {

    @Inject
    AppPreferences appPreferences;
    @Inject
    SplashViewModelFactory viewModelFactory;

    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashViewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel.class);
        splashViewModel.getIntentResult().observe(this, this::processIntentResult);
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean firstInstall = appPreferences.getBooleanValue(GlobalConstant.PREFERENCES_FIRST_INSTALL_KEY, true);
        if (firstInstall) {
            appPreferences.setValue(GlobalConstant.PREFERENCES_FIRST_INSTALL_KEY, false);
            splashViewModel.openIntroActivity();
        } else {
            splashViewModel.openMainActivity();
        }
    }

    private void processIntentResult(Intent intent) {
        if (intent != null) {
            finish();
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}

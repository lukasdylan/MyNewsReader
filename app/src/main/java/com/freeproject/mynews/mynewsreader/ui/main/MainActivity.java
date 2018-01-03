package com.freeproject.mynews.mynewsreader.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.freeproject.mynews.mynewsreader.R;
import com.freeproject.mynews.mynewsreader.data.constant.GlobalConstant;
import com.freeproject.mynews.mynewsreader.ui.main.news.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tabLayout.setupWithViewPager(viewPager, true);

        setupToolbar();

        setupMenu();
    }

    private void setupToolbar(){
        toolbar.setTitle("My News Reader");
    }

    private void setupMenu(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(NewsFragment.newInstance(GlobalConstant.API_GENERAL_CATEGORY));
        viewPagerAdapter.addFragment(NewsFragment.newInstance(GlobalConstant.API_SPORT_CATEGORY));
        viewPagerAdapter.addFragment(NewsFragment.newInstance(GlobalConstant.API_HEALTH_CATEGORY));
        viewPagerAdapter.addFragment(NewsFragment.newInstance(GlobalConstant.API_BUSINESS_CATEGORY));
        viewPagerAdapter.addFragment(NewsFragment.newInstance(GlobalConstant.API_MUSIC_CATEGORY));
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.notifyDataSetChanged();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
            if(!mFragmentList.isEmpty()){
                mFragmentList.clear();
            }
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "General";
                case 1:
                    return "Sport";
                case 2:
                    return "Health";
                case 3:
                    return "Business";
                case 4:
                    return "Music";
            }
            return null;
        }
    }
}

package com.freeproject.mynews.mynewsreader.ui.main.news;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.freeproject.mynews.mynewsreader.GlideApp;
import com.freeproject.mynews.mynewsreader.R;
import com.freeproject.mynews.mynewsreader.data.network.Article;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, NewsAdapter.NewsCallback {

    private static final String CATEGORY_PARAM = "param1";
    private String categoryType;
    private Unbinder unbinder;
    private NewsAdapter newsAdapter;
    private LinearLayoutManager linearLayoutManager;
    private NewsViewModel newsViewModel;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.newsRV)
    RecyclerView newsRV;
    @BindView(R.id.loadingLayout)
    LinearLayout loadingLayout;

    @Inject
    NewsViewModelFactory newsViewModelFactory;

    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String categoryType) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY_PARAM, categoryType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryType = getArguments().getString(CATEGORY_PARAM);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        newsAdapter = new NewsAdapter(new ArrayList<>(), GlideApp.with(this), this);
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        newsRV.setLayoutManager(linearLayoutManager);
        newsRV.setHasFixedSize(true);
        newsRV.setAdapter(newsAdapter);

        newsViewModel = ViewModelProviders.of(this, newsViewModelFactory).get(NewsViewModel.class);
        newsViewModel.getLoadingStatus().observe(this, this::processLoadingStatus);
        newsViewModel.getArticleList().observe(this, articleList -> newsAdapter.addArticle(articleList));
        newsViewModel.getIntentResult().observe(this, intent -> {
            if(intent!=null){
                NewsFragment.this.startActivity(intent);
            }
        });
        newsViewModel.getCurrentPage().observe(this, integer -> newsAdapter.loadMoreArticle());

        newsViewModel.loadNewsArticleByCategory(categoryType);

        newsRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = linearLayoutManager.getItemCount();
                int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (totalItemCount <= (lastVisibleItem + 1)) {
                    newsViewModel.loadNextPage();
                }
            }
        });
    }

    private void processLoadingStatus(boolean isLoading) {
        loadingLayout.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        newsRV.setVisibility(isLoading ? View.GONE : View.VISIBLE);
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
        newsAdapter.removeAllArticle();
        newsViewModel.loadNewsArticleByCategory(categoryType);
    }

    @Override
    public void onSelectedArticle(Article article) {
        newsViewModel.openArticleActivity(article);
    }
}

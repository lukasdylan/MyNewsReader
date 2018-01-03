package com.freeproject.mynews.mynewsreader.ui.main.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.freeproject.mynews.mynewsreader.R;
import com.freeproject.mynews.mynewsreader.data.network.Article;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by Lukas Dylan Adisurya on 12/28/2017.
 * If you had any question about this project, you can contact me via E-mail lukas.dylan.adisurya@gmail.com
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<Article> articleList = new ArrayList<>();
    private final RequestManager requestManager;
    private final RequestOptions requestOptions;
    private final NewsCallback newsCallback;
    private final int maxLoad = 20;
    private int currentLoad = 0;

    NewsAdapter(List<Article> articleList, RequestManager requestManager, NewsCallback newsCallback) {
        this.articleList = articleList;
        this.requestManager = requestManager;
        this.requestOptions = new RequestOptions()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .format(DecodeFormat.PREFER_RGB_565)
                .dontAnimate()
                .centerCrop()
                .placeholder(android.R.color.darker_gray);
        this.newsCallback = newsCallback;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.bind(articleList.get(position));
    }

    void addArticle(List<Article> articleList) {
        int startPosition = this.articleList.size();
        this.articleList.addAll(articleList);
        currentLoad++;
        notifyItemRangeInserted(startPosition, this.articleList.size());
    }

    void loadMoreArticle(){
        currentLoad++;
        notifyDataSetChanged();
    }

    void removeAllArticle() {
        notifyItemRangeRemoved(0, articleList.size());
        articleList.clear();
    }

    @Override
    public int getItemCount() {
        if (currentLoad * maxLoad > articleList.size()) {
            return articleList.size();
        } else {
            return currentLoad * maxLoad;
        }
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.newsImageIV)
        ImageView newsImageIV;
        @BindView(R.id.newsTitleTV)
        TextView newsTitleTV;
        @BindView(R.id.newsDescTV)
        TextView newsDescTV;
        @BindView(R.id.newsDateTV)
        TextView newsDateTV;

        NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Article article) {
            requestManager.load(article.getUrlToImage())
                    .apply(requestOptions)
                    .transition(withCrossFade())
                    .into(newsImageIV);

            newsTitleTV.setText(article.getTitle());
            newsDescTV.setText(article.getDescription());
            newsDateTV.setText(article.getPublishedAt());
        }

        @OnClick(R.id.root)
        void click() {
            newsCallback.onSelectedArticle(articleList.get(getAdapterPosition()));
        }
    }

    public interface NewsCallback {
        void onSelectedArticle(Article article);
    }
}

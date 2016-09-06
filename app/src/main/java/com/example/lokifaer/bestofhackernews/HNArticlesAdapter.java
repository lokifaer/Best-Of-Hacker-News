package com.example.lokifaer.bestofhackernews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.oc.hnapp.android.HNArticle;
import com.oc.hnapp.android.HNQueryCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lokifaer on 05/09/16.
 */
public class HNArticlesAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements HNQueryCallback
{
    private static final int VIEW_TYPE_ARTICLE = 0;
    private static final int VIEW_TYPE_PROGRESS = 1;

    private final List<HNArticle> articleList = new ArrayList<HNArticle>();
    private boolean _hasMore = false;

    private final MainActivity mainActivity;

    public HNArticlesAdapter(MainActivity mActivity)
    {
        mainActivity = mActivity;
    }

    @Override
    public int getItemCount()
    {
        return articleList.size() + (_hasMore ? 1 : 0);
    }

    @Override
    public void onArticlesReceived(List<HNArticle> articles, boolean hasMore)
    {
        articleList.addAll(articles);
        _hasMore = hasMore;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position)
    {
        if (position < articleList.size())
            return VIEW_TYPE_ARTICLE;
        else
            return VIEW_TYPE_PROGRESS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        switch(viewType)
        {
            case VIEW_TYPE_ARTICLE:
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
                return new HNArticleViewHolder(view);
            }
            case VIEW_TYPE_PROGRESS:
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_progress, parent, false);
                return new ProgressViewHolder(view);
            }
            default:
                throw new IllegalStateException("Unknown type " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder instanceof HNArticleViewHolder)
            ((HNArticleViewHolder) holder).bind(articleList.get(position));
        else if (holder instanceof ProgressViewHolder)
            mainActivity.loadNext();
    }

    public static class HNArticleViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView title;

        public HNArticleViewHolder(View v)
        {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
        }

        public void bind(HNArticle a)
        {
            title.setText(a.title);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder
    {
        public ProgressViewHolder(View itemView) { super(itemView); }
    }
}

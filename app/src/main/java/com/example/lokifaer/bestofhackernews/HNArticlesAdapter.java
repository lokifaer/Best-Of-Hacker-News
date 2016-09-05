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
        extends RecyclerView.Adapter<HNArticlesAdapter.HNArticleViewHolder>
        implements HNQueryCallback
{
    private final List<HNArticle> articleList = new ArrayList<HNArticle>();

    @Override
    public int getItemCount()
    {
        return articleList.size();
    }

    @Override
    public void onArticlesReceived(List<HNArticle> articles, boolean hasMore)
    {
        articleList.addAll(articles);
        notifyDataSetChanged();
    }

    @Override
    public HNArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new HNArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HNArticleViewHolder holder, int position)
    {
        holder.bind(articleList.get(position));
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
}

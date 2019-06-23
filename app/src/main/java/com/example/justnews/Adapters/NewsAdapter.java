package com.example.justnews.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.justnews.APIS.Model.NewsModel.ArticlesItem;
import com.example.justnews.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewNewsHolder> {
    List<ArticlesItem> articlesItems ;
    onItemClickListener itemClickListener;

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public NewsAdapter(List<ArticlesItem> articlesItems) {
        this.articlesItems = articlesItems;
    }

    @NonNull
    @Override
    public ViewNewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_news,viewGroup,false);
        return new ViewNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewNewsHolder viewNewsHolder, int position) {
        final ArticlesItem articlesItem = articlesItems.get(position);
        viewNewsHolder.title.setText(articlesItem.getTitle());
        viewNewsHolder.time.setText(articlesItem.getPublishedAt());
        Glide.with(viewNewsHolder.itemView)
           .load(articlesItem.getUrlToImage())
           .into(viewNewsHolder.image);
        viewNewsHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(articlesItem);
                }
            }
        });
    }

    public void changeData(List<ArticlesItem> articlesItems){
        this.articlesItems = articlesItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (articlesItems==null) return 0;
        return articlesItems.size();
    }

    public class ViewNewsHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, time;
        public ViewNewsHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.news_image);
            title = itemView.findViewById(R.id.news_title);
            time = itemView.findViewById(R.id.news_time);
        }
    }

    public interface onItemClickListener{
        public void onItemClick(ArticlesItem articlesItem);
    }
}

package com.example.bookhut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingViewHolder>{
    Context _context;

    public TrendingAdapter(Context context) {
        _context = context;
    }

    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(_context).inflate(R.layout.layout_trending_item, parent, false);

        TrendingViewHolder viewHolder = new TrendingViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

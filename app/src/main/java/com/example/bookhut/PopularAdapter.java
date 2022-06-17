package com.example.bookhut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PopularAdapter extends RecyclerView.Adapter<PopularViewHolder> {
    Context _context;

    public PopularAdapter(Context context) {
        _context = context;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(_context).inflate(R.layout.layout_popular_item, parent, false);

        PopularViewHolder viewHolder = new PopularViewHolder(itemView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

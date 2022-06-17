package com.example.bookhut;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrendingViewHolder extends RecyclerView.ViewHolder{

    public TextView textViewTrendingBookTitle, textViewTrendingBookAuthor, textViewTrendingBookRating;
    public ImageView imageViewBookCover;

    public TrendingViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewBookCover = itemView.findViewById(R.id.bookCover);
        textViewTrendingBookTitle = itemView.findViewById(R.id.txtBookTitle);
        textViewTrendingBookAuthor = itemView.findViewById(R.id.txtBookAuthor);
        textViewTrendingBookRating = itemView.findViewById(R.id.txtBookRating);
    }
}

package com.example.bookhut.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookhut.R;

public class PopularViewHolder extends RecyclerView.ViewHolder{

    public TextView textViewPopularBookTitle, textViewPopularBookAuthor;
    public ImageView imageViewBookCover;

    public PopularViewHolder(@NonNull View itemView) {
        super(itemView);

        imageViewBookCover = itemView.findViewById(R.id.bookCover);
        textViewPopularBookTitle = itemView.findViewById(R.id.txtBookTitle);
        textViewPopularBookAuthor = itemView.findViewById(R.id.txtBookAuthor);
    }
}

package com.example.bookhut.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookhut.ExplorePage;
import com.example.bookhut.R;
import com.example.bookhut.data.BookData;

import java.util.Vector;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder> {
    private Context context;
    private Vector<BookData> bookLists;
    private OnItemClickListener itemClickListener;

    public ExploreAdapter(Context context, Vector<BookData> bookDataVector, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.bookLists = bookDataVector;
        this.itemClickListener = onItemClickListener;

        if (bookLists.isEmpty()){
            ExplorePage.noDataShow();
        }
        else {
            ExplorePage.noDataHide();
        }

    }

    @NonNull
    @Override
    public ExploreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.explore_item, parent, false);
        return new ExploreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreAdapter.ExploreViewHolder holder, int position) {
        holder.bName.setText(bookLists.get(position).getBookName());
        holder.bAuthor.setText(bookLists.get(position).getBookAuthor());
        holder.bRating.setText(bookLists.get(position).getBookRating().toString());
        Glide.with(context)
                .load(bookLists.get(position).getBookImage())
                .into(holder.bImage);
    }

    @Override
    public int getItemCount() {
        return bookLists.size();
    }

    public interface OnItemClickListener {
        void onItemClick(BookData books, int position);
    }

    protected class ExploreViewHolder extends RecyclerView.ViewHolder {
        TextView bName, bAuthor, bRating;
        ImageView bImage;

        public ExploreViewHolder(View view) {
            super(view);

            bImage = view.findViewById(R.id.ivBook);
            bName = view.findViewById(R.id.bookTitle);
            bAuthor = view.findViewById(R.id.bookAuthor);
            bRating = view.findViewById(R.id.bookRating);
        }
    }
}

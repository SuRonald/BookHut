package com.example.bookhut.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookhut.DetailPage;
import com.example.bookhut.R;
import com.example.bookhut.data.BookData;
import com.example.bookhut.data.UserData;

import java.util.Vector;

import static com.example.bookhut.DataVault.bookData;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder> {
    Intent movePage;
    Context ctx;
    Vector<BookData> books;
    BookData temp;

    public TrendingAdapter(Context ctx){
        this.ctx = ctx;
    }

    public void setBooks(Vector<BookData> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public TrendingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.layout_trending_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingAdapter.ViewHolder holder, int position) {
        holder.bookId = books.get(position).getBookID();
        holder.bookPic = books.get(position).getBookImage();
        Glide.with(ctx).load(holder.bookPic).into(holder.bookImage);
        holder.bookTitle.setText(books.get(position).getBookName());
        holder.bookAuthor.setText(books.get(position).getBookAuthor());
        holder.bookRating.setText("" + books.get(position).getBookRating());
        holder.bookStar = books.get(position).getBookRating();
        holder.bookPrice = books.get(position).getBookPrice();
        holder.bookDesc = books.get(position).getBookDesc();
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView bookCard;
        ImageView bookImage;
        TextView bookTitle, bookAuthor, bookRating;
        int bookId, bookPrice;
        String bookPic, bookDesc;
        Double bookStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookCard = itemView.findViewById(R.id.bookCard);
            bookCard.setOnClickListener(this);
            bookImage = itemView.findViewById(R.id.bookImage);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookRating = itemView.findViewById(R.id.bookRating);
        }

        @Override
        public void onClick(View view) {
            movePage = new Intent(view.getContext(), DetailPage.class);
            temp = new BookData(bookId, bookTitle.getText().toString(), bookAuthor.getText().toString(), bookStar, bookPrice, bookPic, bookDesc);
            bookData = temp;
            view.getContext().startActivity(movePage);
        }
    }
}
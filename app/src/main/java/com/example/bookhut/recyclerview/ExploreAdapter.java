package com.example.bookhut.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookhut.DetailPage;
import com.example.bookhut.ExplorePage;
import com.example.bookhut.R;
import com.example.bookhut.data.BookData;

import java.util.Vector;

import static com.example.bookhut.DataVault.bookData;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.ExploreViewHolder> {
    Intent movePage;
    private Context context;
    private Vector<BookData> bookLists;
    private BookData temp;

    public ExploreAdapter(Context context, Vector<BookData> bookDataVector) {
        this.context = context;
        this.bookLists = bookDataVector;

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
        holder.bId = bookLists.get(position).getBookID();
        holder.bPic = bookLists.get(position).getBookImage();
        holder.bStar = bookLists.get(position).getBookRating();
        holder.bPrice = bookLists.get(position).getBookPrice();
        holder.bDesc = bookLists.get(position).getBookDesc();
        holder.bName.setText(bookLists.get(position).getBookName());
        holder.bAuthor.setText("By " + bookLists.get(position).getBookAuthor());
        holder.bRating.setText(bookLists.get(position).getBookRating().toString());
        Glide.with(context)
                .load(bookLists.get(position).getBookImage())
                .into(holder.bImage);
    }

    @Override
    public int getItemCount() {
        return bookLists.size();
    }

    class ExploreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView bName, bAuthor, bRating;
        ImageView bImage;
        Integer bId, bPrice;
        String bPic, bDesc;
        Double bStar;

        public ExploreViewHolder(View view) {
            super(view);
            bImage = view.findViewById(R.id.ivBook);
            bName = view.findViewById(R.id.bookTitle);
            bAuthor = view.findViewById(R.id.bookAuthor);
            bRating = view.findViewById(R.id.bookRating);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            movePage = new Intent(view.getContext(), DetailPage.class);
            temp = new BookData(bId, bName.getText().toString(), bAuthor.getText().toString(), bStar, bPrice, bPic, bDesc);
            bookData = temp;
            view.getContext().startActivity(movePage);
        }
    }
}

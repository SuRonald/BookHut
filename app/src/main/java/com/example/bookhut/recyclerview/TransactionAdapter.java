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
import com.example.bookhut.R;
import com.example.bookhut.data.BookData;
import com.example.bookhut.data.TransactionData;
import com.example.bookhut.helper.BookDataHelper;

import java.util.Vector;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    Context ctx;
    Vector<TransactionData> transactions;
    BookData tempBook;

    public TransactionAdapter(Context ctx){
        this.ctx = ctx;
    }

    public void setTransactions(Vector<TransactionData> transactions) {
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public TransactionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.transaction_item, parent, false);
        return new TransactionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.ViewHolder holder, int position) {
        BookDataHelper BHelper = new BookDataHelper(ctx);
        BHelper.open();
        tempBook = BHelper.takeBook(transactions.get(position).getBookID());
        BHelper.close();
        Glide.with(ctx).load(tempBook.getBookImage()).into(holder.bookImage);
        holder.bookTitle.setText(tempBook.getBookName());
        holder.bookAuthor.setText(tempBook.getBookAuthor());
        holder.transDate.setText(transactions.get(position).getTransDate());
        holder.transQnty.setText("Quantity: " + transactions.get(position).getTransQnty());
        holder.totalPrice.setText("Rp. " + tempBook.getBookPrice()*transactions.get(position).getTransQnty());
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView bookImage;
        TextView bookTitle, bookAuthor, transDate, transQnty, totalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookImage = itemView.findViewById(R.id.bookImage);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            transDate = itemView.findViewById(R.id.transDate);
            transQnty = itemView.findViewById(R.id.transQnty);
            totalPrice = itemView.findViewById(R.id.totalPrice);
        }
    }
}

package com.example.challange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<GmailItem> items;
    Context context;

    public MainAdapter(Context context, ArrayList<GmailItem> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        GmailItem item = items.get(position);
        holder.email.setText(item.getTitle());
        holder.emailAddress.setText(item.getEmailAddress());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView email;
        TextView emailAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            email = itemView.findViewById(R.id.email);
            emailAddress = itemView.findViewById(R.id.email_address);
        }
    }
}

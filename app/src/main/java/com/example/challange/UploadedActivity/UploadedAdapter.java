package com.example.challange.UploadedActivity;

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
import com.example.challange.R;

import java.util.ArrayList;

public class UploadedAdapter extends RecyclerView.Adapter<UploadedAdapter.ViewHolder> {
    ArrayList<ImageItem> items;
    Context context;

    public UploadedAdapter(Context context, ArrayList<ImageItem> items){
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_card, parent, false);
        return new UploadedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageItem item = items.get(position);

        Glide.with(context).load(item.getImageUrl()).override(50, 50).into(holder.imageView);
        holder.documentName.setText(item.getDocumentName());

        holder.card.setOnClickListener(document -> {
            Intent intent = new Intent(context, Image.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("image", item.getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView imageView;
        TextView documentName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            imageView = itemView.findViewById(R.id.image_icon);
            documentName = itemView.findViewById(R.id.image_name);
        }
    }
}

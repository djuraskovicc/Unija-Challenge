package com.example.challange;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.ViewHolder> {
    ArrayList<DocumentItem> documents;
    Context context;

    public DocumentsAdapter(Context context, ArrayList<DocumentItem> documents){
        this.context = context;
        this.documents = documents;
    }

    @NonNull
    @Override
    public DocumentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.document_card, parent, false);
        return new DocumentsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DocumentItem item = documents.get(position);
        holder.documentName.setText(item.getDocumentName());
        holder.date.setText(item.getDocumentDate());

        holder.card.setOnClickListener(click -> {
            Intent intent = new Intent(context, FileViewer.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView card;
        TextView documentName, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.card);
            documentName = itemView.findViewById(R.id.document_name);
            date = itemView.findViewById(R.id.upload_date);
        }
    }
}

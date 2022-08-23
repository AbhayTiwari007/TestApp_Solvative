package com.example.testapp.ModelsandAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testapp.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    ArrayList<Datum> arrayList;
    Context context;

    public Adapter(ArrayList<Datum> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cards_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        holder.nametv.setText(arrayList.get(position).getFirstName() + " " + arrayList.get(position).getLastName());
        holder.emailtv.setText(arrayList.get(position).getEmail());
        Glide.with(context).load(arrayList.get(position).getAvatar()).into(holder.avtarImg);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avtarImg;
        TextView nametv, emailtv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.avtarImg = itemView.findViewById(R.id.card_img_id);
            this.emailtv = itemView.findViewById(R.id.email_id);
            this.nametv = itemView.findViewById(R.id.full_name_id);
        }
    }
}

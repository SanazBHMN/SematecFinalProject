package com.sanaz.iaust.hotel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
;

import com.google.gson.Gson;
import com.sanaz.iaust.hotel.pojo.Search;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    //constructor to pass a list to this class
    List<Search> search;
    Context context;
    public ItemAdapter(List<Search> search, Context context) {
        this.search = search;
        this.context=context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.txtMovie.setText(search.get(position).getTitle());
        Search item=search.get(position);
        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String JsonObject= new Gson().toJson(item);

                Intent intent = new Intent(context,MoreInfoActivity.class);
                intent.putExtra("movie_title", JsonObject);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return search.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView txtMovie;
        Button btnMore;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMovie = itemView.findViewById(R.id.txtMovie);
            btnMore = itemView.findViewById(R.id.btnMore);
        }
    }
}



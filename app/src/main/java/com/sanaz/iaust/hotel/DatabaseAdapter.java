package com.sanaz.iaust.hotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder> {

    List<DataList> datalist;

    //constructor
    public DatabaseAdapter(List<DataList> datalist) {

        this.datalist = datalist;
    }

    @NonNull
    @Override
    public DatabaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.database_item, parent, false);
        return new DatabaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatabaseViewHolder holder, int position) {
        DataList dataList = datalist.get(position);
        holder.txtTitleDB.setText(dataList.getTitle());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class DatabaseViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitleDB;

        public DatabaseViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitleDB = itemView.findViewById(R.id.txtTitleDB);
        }
    }
}

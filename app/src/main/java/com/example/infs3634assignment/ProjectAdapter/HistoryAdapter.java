package com.example.infs3634assignment.ProjectAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634assignment.R;
import com.example.infs3634assignment.model.History;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Holder>{
    private List<History> data;

    public void setData(List<History> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.his_title.setText(data.get(position).getRecipeTitle());
        holder.his_du.setText("Time Studying Recipe: "+data.get(position).getDuriation());
        holder.his_comment.setText("Comments: " + data.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView his_title;
        TextView his_du;
        TextView his_comment;

        public Holder(@NonNull View itemView) {
            super(itemView);
            his_title = itemView.findViewById(R.id.his_title);
            his_du = itemView.findViewById(R.id.his_du);
            his_comment = itemView.findViewById(R.id.his_comment);
        }
    }
}

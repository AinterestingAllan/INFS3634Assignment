package com.example.infs3634assignment.ProjectAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3634assignment.R;
import com.example.infs3634assignment.model.Steps;

import java.util.List;

//ADAPTER FOR STEP CLASS

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.Holder> {
    private List<Steps> data;

    public void setData(List<Steps> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.step_item, parent, false);
        StepAdapter.Holder holder = new StepAdapter.Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.step.setText("Step: " + data.get(position).getNumber());
        holder.instruction.setText(data.get(position).getStep());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView step;
        TextView instruction;

        public Holder(@NonNull View itemView) {
            super(itemView);
            step = itemView.findViewById(R.id.step);
            instruction = itemView.findViewById(R.id.instruction);
        }
    }
}

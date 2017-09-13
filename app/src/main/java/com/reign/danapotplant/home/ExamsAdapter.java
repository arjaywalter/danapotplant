package com.reign.danapotplant.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.reign.danapotplant.R;
import com.reign.danapotplant.models.Exam;

import java.util.ArrayList;
import java.util.List;

public class ExamsAdapter extends RecyclerView.Adapter<ExamsAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<Exam> data;
    private Context context;

    public ExamsAdapter(Context context, List<Exam> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }

    public void setData(ArrayList<Exam> skills) {
        data = skills;
        notifyDataSetChanged();
    }

    @Override
    public ExamsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_exams_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExamsAdapter.ViewHolder holder, int position) {
        holder.click(data.get(position), listener);
        holder.tvName.setText(data.get(position).getName());
        holder.determinateBar.setProgress(data.get(position).getProgress());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {
        void onClick(Exam Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ProgressBar determinateBar;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            determinateBar = (ProgressBar) itemView.findViewById(R.id.determinateBar);

        }

        public void click(final Exam cityListData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(cityListData);
                }
            });
        }
    }

}

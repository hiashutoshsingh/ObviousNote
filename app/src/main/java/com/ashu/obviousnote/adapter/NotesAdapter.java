package com.ashu.obviousnote.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ashu.obviousnote.DB.Note;
import com.ashu.obviousnote.R;
import com.ashu.obviousnote.Utils.CommonMethods;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private final OnItemClickListener listener;
    private List<Note> noteList;

    public NotesAdapter(List<Note> notes, OnItemClickListener listener) {
        this.listener = listener;
        noteList = notes;
    }

    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NotesAdapter.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @Override
    public void onBindViewHolder(NotesAdapter.ViewHolder holder, int position) {
        Note ratingModel = noteList.get(position);
        holder.bind(ratingModel, listener);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView task_time, task_name;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.abc_single_task, parent, false));
            task_name = itemView.findViewById(R.id.task_name);
            task_time = itemView.findViewById(R.id.task_time);
        }

        public void bind(final Note ratingModel, final OnItemClickListener listener) {
            task_name.setText(ratingModel.getNote());
            if (ratingModel.getTime() != null) {
                task_time.setVisibility(View.VISIBLE);
                task_time.setText(CommonMethods.getFormattedDateString(ratingModel.getTime()));
            }
            itemView.setOnClickListener(v -> listener.onItemClick(ratingModel));
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Note ratingModel);
    }

}
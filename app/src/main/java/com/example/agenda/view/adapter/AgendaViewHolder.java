package com.example.agenda.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda.R;
import com.google.android.material.textfield.TextInputEditText;

public class AgendaViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvItem;

    public AgendaViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvItem = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        tvItem.setText(text);
    }

    static AgendaViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false);
        return new AgendaViewHolder(view);
    }

}

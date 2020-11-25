package com.example.agenda.view.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.agenda.model.entity.Agenda;

public class AgendaAdapter extends ListAdapter<Agenda, AgendaViewHolder> {

    public AgendaAdapter(@NonNull DiffUtil.ItemCallback<Agenda> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public AgendaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return AgendaViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaViewHolder holder, int position) {
        Agenda current = getItem(position);

        holder.bind("Nombre:" + "\t\t\t\t\t\t" + current.getNombre() + "\n" + "Apellidos:" + "\t\t\t\t" + current.getApellidos()
                + "\n" + "Teléfono:" + "\t\t\t\t " + current.getTelefono() + "\n" + "Fecha nac:" + "\t\t\t" + current.getFechaNac() + "\n" + "Localidad:" + "\t\t\t" +
                current.getLocalidad() + "\n" + "Calle:" + "\t\t\t\t\t\t\t\t\t" + current.getCalle() + "\n" + "Número:" + "\t\t\t\t\t\t\t" + current.getNumero());
    }

    public static class AgendaDiff extends DiffUtil.ItemCallback<Agenda> {

        @Override
        public boolean areItemsTheSame(@NonNull Agenda oldItem, @NonNull Agenda newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Agenda oldItem, @NonNull Agenda newItem) {
            return oldItem.getNombre().equals(newItem.getNombre());
        }
    }

}

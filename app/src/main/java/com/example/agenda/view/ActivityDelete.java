package com.example.agenda.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.agenda.R;
import com.example.agenda.model.entity.Agenda;
import com.example.agenda.view.adapter.AgendaAdapter;
import com.example.agenda.viewmodel.AgendaViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ActivityDelete extends AppCompatActivity {

    private AgendaViewModel viewModel;

    Button btBuscar, btBorrar;
    String result;

    Dialog dialog;
    TextInputEditText etDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        viewModel = new ViewModelProvider(this).get(AgendaViewModel.class);
        viewModel.setContext(getApplication());

        init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateWindmill(ActivityDelete.this);
    }

    private void init() {

        etDelete = findViewById(R.id.etDelete);
        btBuscar = findViewById(R.id.btBuscarDelete);
        btBorrar = findViewById(R.id.btBorrarDelete);
        dialog = new Dialog(ActivityDelete.this);

        ImageButton btAtras = findViewById(R.id.btAtrasDelete);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDelete.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateWindmill(ActivityDelete.this);
            }
        });

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btBorrar.setEnabled(false);

                result = etDelete.getText().toString();

                if(result.equals("")) {
                    Snackbar.make(btBuscar, "No hay nada que buscar...", Snackbar.LENGTH_SHORT).show();
                } else {

                    RecyclerView recyclerView = findViewById(R.id.recyclerviewDelete);
                    final AgendaAdapter adapter = new AgendaAdapter(new AgendaAdapter.AgendaDiff());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ActivityDelete.this));
                    viewModel.getAllAgendaT(result).observe(ActivityDelete.this, new Observer<List<Agenda>>() {
                        @Override
                        public void onChanged(List<Agenda> agenda) {
                            adapter.submitList(agenda);
                            if(!(adapter.getItemCount() == 0)) {
                                btBorrar.setEnabled(true);
                            }
                        }
                    });
                }
            }

        });

        btBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBorrar();
            }
        });
    }

    private void alertDialogBorrar() {
        final MediaPlayer mediaPlayer = MediaPlayer.create(ActivityDelete.this, R.raw.sonidoborrar);

        dialog.setContentView(R.layout.borrar_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageButton btCerrarDialog = dialog.findViewById(R.id.ivCerrarBorrar);
        Button btRespuestaBorrar = dialog.findViewById(R.id.btRespustaBorrar);
        dialog.show();

        btCerrarDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btRespuestaBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.delete(result);
                dialog.dismiss();
                Intent intent = new Intent(ActivityDelete.this, MainActivity.class);
                startActivity(intent);
                mediaPlayer.start();
                Animatoo.animateCard(ActivityDelete.this);
            }
        });
    }
}
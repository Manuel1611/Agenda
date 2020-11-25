package com.example.agenda.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

public class ActivityUpdate extends AppCompatActivity {

    public static final String EXTRA_TELUPDATE = "com.example.agenda.TELUPDATE";

    private AgendaViewModel viewModel;

    Button btBuscar, btEditar;
    String result;

    TextInputEditText etUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        viewModel = new ViewModelProvider(this).get(AgendaViewModel.class);
        viewModel.setContext(getApplication());

        init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateWindmill(ActivityUpdate.this);
    }

    private void init() {

        etUpdate = findViewById(R.id.etUpdate);
        btBuscar = findViewById(R.id.btBuscarUpdate);
        btEditar = findViewById(R.id.btEditarUpdate);

        ImageButton btAtras = findViewById(R.id.btAtrasUpdate);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityUpdate.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateWindmill(ActivityUpdate.this);
            }
        });

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btEditar.setEnabled(false);

                result = etUpdate.getText().toString();

                if(result.equals("")) {
                    Snackbar.make(btBuscar, "No hay nada que buscar...", Snackbar.LENGTH_SHORT).show();
                } else {

                    RecyclerView recyclerView = findViewById(R.id.recyclerviewUpdate);
                    final AgendaAdapter adapter = new AgendaAdapter(new AgendaAdapter.AgendaDiff());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ActivityUpdate.this));
                    viewModel.getAllAgendaT(result).observe(ActivityUpdate.this, new Observer<List<Agenda>>() {
                        @Override
                        public void onChanged(List<Agenda> agenda) {
                            adapter.submitList(agenda);
                            if(!(adapter.getItemCount() == 0)) {
                                btEditar.setEnabled(true);
                            }
                        }
                    });
                }
            }

        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityUpdate.this, ActivityDatosUpdate.class);
                intent.putExtra(EXTRA_TELUPDATE, result);
                startActivity(intent);
                Animatoo.animateCard(ActivityUpdate.this);
            }
        });
    }
}
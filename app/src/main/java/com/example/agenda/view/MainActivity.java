package com.example.agenda.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.agenda.R;
import com.example.agenda.model.entity.Agenda;
import com.example.agenda.view.adapter.AgendaAdapter;
import com.example.agenda.viewmodel.AgendaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;

    private AgendaViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(AgendaViewModel.class);
        viewModel.setContext(getApplication());

        Intent i = getIntent();
        String nom = i.getStringExtra(ActivityInsert.EXTRA_NOMBRE);
        String apell = i.getStringExtra(ActivityInsert.EXTRA_APELLIDOS);
        String tel = i.getStringExtra(ActivityInsert.EXTRA_TELEFONO);
        String fechan = i.getStringExtra(ActivityInsert.EXTRA_FECHANAC);
        String loc = i.getStringExtra(ActivityInsert.EXTRA_LOCALIDAD);
        String call = i.getStringExtra(ActivityInsert.EXTRA_CALLE);
        int num = i.getIntExtra(ActivityInsert.EXTRA_NUMERO, 0);
        viewModel.insert(new Agenda(nom, apell, tel, fechan, loc, call, num));

        init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateWindmill(MainActivity.this);
    }

    private void init() {

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final AgendaAdapter adapter = new AgendaAdapter(new AgendaAdapter.AgendaDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageButton btInsertar = findViewById(R.id.btInsertar);
        ImageButton btDelete = findViewById(R.id.btDelete);
        ImageButton btUpdate = findViewById(R.id.btUpdate);

        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityInsert.class);
                startActivityForResult(intent, REQUEST_CODE);
                Animatoo.animateCard(MainActivity.this);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityDelete.class);
                startActivityForResult(intent, REQUEST_CODE);
                Animatoo.animateCard(MainActivity.this);
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityUpdate.class);
                startActivityForResult(intent, REQUEST_CODE);
                Animatoo.animateCard(MainActivity.this);
            }
        });

        viewModel.getAllAgenda().observe(this, new Observer<List<Agenda>>() {
            @Override
            public void onChanged(List<Agenda> agenda) {
                Log.v("xyz", "onChanged: " + agenda.toString());
                adapter.submitList(agenda);
            }
        });

    }
}
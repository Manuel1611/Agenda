package com.example.agenda.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.agenda.R;
import com.example.agenda.model.entity.Agenda;
import com.example.agenda.model.entity.Validar;
import com.example.agenda.view.adapter.AgendaViewHolder;
import com.example.agenda.viewmodel.AgendaViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityDatosUpdate extends AppCompatActivity {

    private TextInputEditText etNombreU, etApellidosU, etTelefonoU, etFechaNacimientoU, etLocalidadU, etCalleU, etNumeroU;

    private AgendaViewModel viewModel;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_update);

        Intent intent = getIntent();
        result = intent.getStringExtra(ActivityUpdate.EXTRA_TELUPDATE);

        viewModel = new ViewModelProvider(this).get(AgendaViewModel.class);
        viewModel.setContext(getApplication());

        init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateWindmill(ActivityDatosUpdate.this);
    }

    private void init() {
        etNombreU = findViewById(R.id.etNombreU);
        etApellidosU = findViewById(R.id.etApellidosU);
        etTelefonoU = findViewById(R.id.etTelefonoU);
        etFechaNacimientoU = findViewById(R.id.etFechaNacimientoU);
        etLocalidadU = findViewById(R.id.etLocalidadU);
        etCalleU = findViewById(R.id.etCalleU);
        etNumeroU = findViewById(R.id.etNumeroU);

        ImageButton btAtras = findViewById(R.id.btAtrasDatosUpdate);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityDatosUpdate.this, ActivityUpdate.class);
                startActivity(intent);
                Animatoo.animateWindmill(ActivityDatosUpdate.this);
            }
        });

        final ImageButton btEditar = findViewById(R.id.btEditarDatosUpdate);

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etNombreU.getText())
                        ||
                        TextUtils.isEmpty(etApellidosU.getText())
                        ||
                        TextUtils.isEmpty(etTelefonoU.getText())
                        ||
                        TextUtils.isEmpty(etFechaNacimientoU.getText())
                        ||
                        TextUtils.isEmpty(etLocalidadU.getText())
                        ||
                        TextUtils.isEmpty(etCalleU.getText())
                        ||
                        TextUtils.isEmpty(etNumeroU.getText())) {

                    Snackbar.make(btEditar, "Por favor, rellena todos los campos", Snackbar.LENGTH_SHORT).show();

                } else if (Validar.validarString(etNombreU.getText().toString())){
                    Snackbar.make(btEditar, "El nombre introducido no es válido", Snackbar.LENGTH_SHORT).show();
                    etNombreU.setText("");

                } else if (Validar.validarStringAp(etApellidosU.getText().toString())) {
                    Snackbar.make(btEditar, "Los apellidos introducidos no son válidos", Snackbar.LENGTH_SHORT).show();
                    etApellidosU.setText("");

                } else if (!Validar.validarTelefono(etTelefonoU.getText().toString())) {
                    Snackbar.make(btEditar, "El teléfono introducido no es válido", Snackbar.LENGTH_SHORT).show();
                    etTelefonoU.setText("");

                } else if (!Validar.validarFecha(etFechaNacimientoU.getText().toString())) {
                    Snackbar.make(btEditar, "La fecha de nacimiento no es válida", Snackbar.LENGTH_SHORT).show();
                    etFechaNacimientoU.setText("");

                } else if (Validar.validarString(etLocalidadU.getText().toString())) {
                    Snackbar.make(btEditar, "La localidad introducida no es válida", Snackbar.LENGTH_SHORT).show();
                    etLocalidadU.setText("");

                } else if (!Validar.lenCalle(etCalleU.getText().toString())) {
                    Snackbar.make(btEditar, "La calle introducida no es válida", Snackbar.LENGTH_SHORT).show();
                    etCalleU.setText("");

                } else if(!Validar.lenNumero(etNumeroU.getText().toString())) {
                    Snackbar.make(btEditar, "El número introducido no es válido", Snackbar.LENGTH_SHORT).show();
                    etNumeroU.setText("");

                } else {

                    String nombre = etNombreU.getText().toString();
                    String apellidos = etApellidosU.getText().toString();
                    String telefono = etTelefonoU.getText().toString();
                    String fechaNac = etFechaNacimientoU.getText().toString();
                    String localidad = etLocalidadU.getText().toString();
                    String calle = etCalleU.getText().toString();
                    String num = etNumeroU.getText().toString();
                    int numero = Integer.parseInt(num);

                    viewModel.update(nombre, apellidos, telefono, fechaNac, localidad, calle, numero, result);
                    Intent intent = new Intent(ActivityDatosUpdate.this, MainActivity.class);
                    startActivity(intent);
                    Animatoo.animateCard(ActivityDatosUpdate.this);
                }
            }
        });
    }
}
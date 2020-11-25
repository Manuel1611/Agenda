package com.example.agenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.agenda.R;
import com.example.agenda.model.entity.Validar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class ActivityInsert extends AppCompatActivity {

    public static final String EXTRA_NOMBRE = "com.example.agenda.NOMBRE";
    public static final String EXTRA_APELLIDOS = "com.example.agenda.APELLIDOS";
    public static final String EXTRA_TELEFONO = "com.example.agenda.TELEFONO";
    public static final String EXTRA_FECHANAC = "com.example.agenda.FECHANAC";
    public static final String EXTRA_LOCALIDAD = "com.example.agenda.LOCALIDAD";
    public static final String EXTRA_CALLE = "com.example.agenda.CALLE";
    public static final String EXTRA_NUMERO = "com.example.agenda.NUMERO";

    private TextInputEditText etNombre, etApellidos, etTelefono, etFechaNacimiento, etLocalidad, etCalle, etNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateWindmill(ActivityInsert.this);
    }

    private void init() {
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellidos);
        etTelefono = findViewById(R.id.etTelefono);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etLocalidad = findViewById(R.id.etLocalidad);
        etCalle = findViewById(R.id.etCalle);
        etNumero = findViewById(R.id.etNumero);

        ImageButton btAtras = findViewById(R.id.btAtrasInsert);

        btAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityInsert.this, MainActivity.class);
                startActivity(intent);
                Animatoo.animateWindmill(ActivityInsert.this);
            }
        });

        final ImageButton btGuardar = findViewById(R.id.btGuardar);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etNombre.getText()) || TextUtils.isEmpty(etApellidos.getText()) || TextUtils.isEmpty(etTelefono.getText()) || TextUtils.isEmpty(etFechaNacimiento.getText())
                        || TextUtils.isEmpty(etLocalidad.getText()) || TextUtils.isEmpty(etCalle.getText()) || TextUtils.isEmpty(etNumero.getText())) {

                            Snackbar.make(btGuardar, "Por favor, rellena todos los campos", Snackbar.LENGTH_SHORT).show();

                } else if (Validar.validarString(etNombre.getText().toString())){
                    Snackbar.make(btGuardar, "El nombre introducido no es válido", Snackbar.LENGTH_SHORT).show();
                    etNombre.setText("");

                } else if (Validar.validarStringAp(etApellidos.getText().toString())) {
                    Snackbar.make(btGuardar, "Los apellidos introducidos no son válidos", Snackbar.LENGTH_SHORT).show();
                    etApellidos.setText("");

                } else if (!Validar.validarTelefono(etTelefono.getText().toString())) {
                    Snackbar.make(btGuardar, "El teléfono introducido no es válido", Snackbar.LENGTH_SHORT).show();
                    etTelefono.setText("");

                } else if (!Validar.validarFecha(etFechaNacimiento.getText().toString())) {
                    Snackbar.make(btGuardar, "La fecha de nacimiento no es válida", Snackbar.LENGTH_SHORT).show();
                    etFechaNacimiento.setText("");

                } else if (Validar.validarString(etLocalidad.getText().toString())) {
                    Snackbar.make(btGuardar, "La localidad introducida no es válida", Snackbar.LENGTH_SHORT).show();
                    etLocalidad.setText("");

                } else if (!Validar.lenCalle(etCalle.getText().toString())) {
                    Snackbar.make(btGuardar, "La calle introducida no es válida", Snackbar.LENGTH_SHORT).show();
                    etCalle.setText("");

                } else if(!Validar.lenNumero(etNumero.getText().toString())) {
                    Snackbar.make(btGuardar, "El número introducido no es válido", Snackbar.LENGTH_SHORT).show();
                    etNumero.setText("");

                } else {

                    String nom = etNombre.getText().toString();
                    String apell = etApellidos.getText().toString();
                    String tel = etTelefono.getText().toString();
                    String fechan = etFechaNacimiento.getText().toString();
                    String loc = etLocalidad.getText().toString();
                    String call = etCalle.getText().toString();
                    String numText = etNumero.getText().toString();
                    int num = Integer.parseInt(numText);

                    Intent i = new Intent(ActivityInsert.this, MainActivity.class);

                    i.putExtra(EXTRA_NOMBRE, nom);
                    i.putExtra(EXTRA_APELLIDOS, apell);
                    i.putExtra(EXTRA_TELEFONO, tel);
                    i.putExtra(EXTRA_FECHANAC, fechan);
                    i.putExtra(EXTRA_LOCALIDAD, loc);
                    i.putExtra(EXTRA_CALLE, call);
                    i.putExtra(EXTRA_NUMERO, num);
                    startActivity(i);
                    Animatoo.animateCard(ActivityInsert.this);
                }
            }
        });
    }
}
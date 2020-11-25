package com.example.agenda.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "agenda")
public class Agenda {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "nombre")
    private String nombre;

    @NonNull
    @ColumnInfo(name = "apellidos")
    private String apellidos;

    @NonNull
    @ColumnInfo(name = "telefono")
    private String telefono;

    @NonNull
    @ColumnInfo(name = "fechanac")
    private String fechaNac;

    @NonNull
    @ColumnInfo(name = "localidad")
    private String localidad;

    @NonNull
    @ColumnInfo(name = "calle")
    private String calle;

    @NonNull
    @ColumnInfo(name = "numero")
    private int numero;

    public Agenda(@NonNull String nombre, @NonNull String apellidos, @NonNull String telefono, @NonNull String fechaNac, @NonNull String localidad, @NonNull String calle, @NonNull int numero) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fechaNac = fechaNac;
        this.localidad = localidad;
        this.calle = calle;
        this.numero = numero;
    }

    public Agenda() {
        this("", "", "", "", "", "", 0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(@NonNull String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(@NonNull String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NonNull String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(@NonNull String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(@NonNull String localidad) {
        this.localidad = localidad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(@NonNull String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}

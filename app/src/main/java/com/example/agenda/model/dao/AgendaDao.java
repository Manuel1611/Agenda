package com.example.agenda.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.agenda.model.entity.Agenda;

import java.util.List;

@Dao
public interface AgendaDao {

    @Delete
    void delete(Agenda agenda);

    @Query("delete from agenda where telefono = :tel")
    void deleteTlf(String tel);

    @Query("update agenda set nombre = :nombre, apellidos = :apellidos, telefono = :telefono, fechanac = :fechanac, localidad = :localidad, calle = :calle, numero = :numero where telefono = :tel")
    void updateTlf(String nombre, String apellidos, String telefono, String fechanac, String localidad, String calle, int numero, String tel);

    @Query("delete from agenda")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Agenda agenda);

    @Update
    void update(Agenda agenda);

    @Query("select * from agenda order by nombre")
    LiveData<List<Agenda>> getAllLive();

    @Query("select * from agenda where telefono = :telefono")
    LiveData<List<Agenda>> selectTlf(String telefono);

}

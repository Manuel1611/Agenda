package com.example.agenda.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.agenda.AgendaApplication;
import com.example.agenda.model.dao.AgendaDao;
import com.example.agenda.model.entity.Agenda;
import com.example.agenda.model.room.AgendaDb;
import com.example.agenda.view.ActivityDelete;

import java.util.List;

public class AgendaRepository {

    private AgendaDb db;

    private AgendaDao agDao;
    private LiveData<List<Agenda>> liveListaAgenda;
    private LiveData<List<Agenda>> liveListaAgendaT;

    public AgendaRepository(Application c) {

        db = AgendaDb.getDatabase(c);
        agDao = db.agendaDao();

        liveListaAgenda = agDao.getAllLive();
    }

    public LiveData<List<Agenda>> getLiveListaAgenda() {
        return liveListaAgenda;
    }

    public LiveData<List<Agenda>> getLiveListaAgendaT(String s) {
        return liveListaAgendaT = agDao.selectTlf(s);
    }

    public void update(String nombre, String apellidos, String telefono, String fechanac, String localidad, String calle, int numero, String tel) {
        AgendaApplication.threadExecutor.execute(() -> {
            agDao.updateTlf(nombre, apellidos, telefono, fechanac, localidad, calle, numero, tel);
        });
    }

    public void insert(Agenda agenda) {

        AgendaApplication.threadExecutor.execute(() -> {
            agDao.insert(agenda);
        });

    }

    public void delete(String tel) {
        AgendaApplication.threadExecutor.execute(() -> {
            agDao.deleteTlf(tel);
        });
    }

}

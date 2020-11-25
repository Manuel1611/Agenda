package com.example.agenda.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.agenda.model.AgendaRepository;
import com.example.agenda.model.entity.Agenda;

import java.util.List;

public class AgendaViewModel extends ViewModel {

    private AgendaRepository repository;

    private LiveData<List<Agenda>> liveAgenda, liveAgendaT, liveAgendaU;

    public AgendaViewModel() {
        super();
    }

    public void setContext(Application application) {
        repository = new AgendaRepository(application);
        liveAgenda = repository.getLiveListaAgenda();
    }

    public LiveData<List<Agenda>> getAllAgenda() {
        return liveAgenda;
    }

    public LiveData<List<Agenda>> getAllAgendaT(String s) {
        return liveAgendaT = repository.getLiveListaAgendaT(s);
    }

    public void update(String nombre, String apellidos, String telefono, String fechanac, String localidad, String calle, int numero, String tel) {
        repository.update(nombre, apellidos, telefono, fechanac, localidad, calle, numero, tel);
    }

    public void insert(Agenda agenda) {
        repository.insert(agenda);
    }

    public void delete(String tel) {
        repository.delete(tel);
    }

}

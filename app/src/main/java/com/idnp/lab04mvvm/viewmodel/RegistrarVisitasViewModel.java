package com.idnp.lab04mvvm.viewmodel;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.idnp.lab04mvvm.ClienteRepository;
import com.idnp.lab04mvvm.Event;
import com.idnp.lab04mvvm.R;
import com.idnp.lab04mvvm.model.Cliente;
import com.idnp.lab04mvvm.model.Visita;

public class RegistrarVisitasViewModel extends ViewModel {

    private final MutableLiveData<String> peso = new MutableLiveData<>();
    private final MutableLiveData<String> temperatura = new MutableLiveData<>();
    private final MutableLiveData<String> presion = new MutableLiveData<>();
    private final MutableLiveData<String> saturacion = new MutableLiveData<>();

    private final MutableLiveData<Event<Integer>> mErrorText = new MutableLiveData<>();
    private final MutableLiveData<Event<Integer>> mVisitSavedEvent = new MutableLiveData<>();

    private final ClienteRepository mClientRepository;

    public RegistrarVisitasViewModel() {
        mClientRepository = ClienteRepository.getsInstance();
    }

    public void saveVisita() {
        if (peso.getValue() == null) {
            mErrorText.setValue(new Event<>(R.string.peso_invalido));
            return;
        }
        if (temperatura.getValue() == null){
            mErrorText.setValue(new Event<>(R.string.temperatura_invalida));
            return;
        }
        if (presion.getValue() == null ){
            mErrorText.setValue(new Event<>(R.string.presion_invalida));
            return;
        }
        if (saturacion.getValue() == null){
            mErrorText.setValue(new Event<>(R.string.saturacion_invalida));
            return;
        }


        Visita visit;
        visit = new Visita();
        visit.setPeso(peso.getValue());
        visit.setPresion(presion.getValue());
        visit.setSaturacion(saturacion.getValue());
        visit.setTemperatura(temperatura.getValue());

        // Guardar categoría
        mClientRepository.addVisit(visit);
        // Enviar evento de navegación
        mVisitSavedEvent.setValue(new Event<>(1));
    }

    public LiveData<Event<Integer>> getVisitSavedEvent() {
        return mVisitSavedEvent;
    }

    public MutableLiveData<String> getPeso() {
        return peso;
    }
    public MutableLiveData<String> getTemperatura() {
        return temperatura;
    }
    public MutableLiveData<String> getPresion() {
        return presion;
    }
    public MutableLiveData<String> getSaturacion() {
        return saturacion;
    }

    public LiveData<Event<Integer>> getError() {
        return mErrorText;
    }

}

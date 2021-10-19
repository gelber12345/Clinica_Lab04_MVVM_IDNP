package com.idnp.lab04mvvm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.idnp.lab04mvvm.model.Cliente;
import com.idnp.lab04mvvm.model.Visita;

import java.util.HashMap;

public class ClienteRepository {

    private static ClienteRepository sInstance;

    private final MutableLiveData<Cliente> observableCliente;
    private  Cliente dummyData;
    public boolean empty=true;

    private ClienteRepository(){
        dummyData = new Cliente();
        observableCliente = new MutableLiveData<>();
        observableCliente.setValue(dummyData);
    }
    public static ClienteRepository getsInstance() {
        if (sInstance == null) {
            sInstance = new ClienteRepository();
        }
        return sInstance;
    }
    public LiveData<Cliente> getCLiente() {
        return new MutableLiveData<>(observableCliente.getValue());
    }

    public void addCliente(Cliente cliente) {
        dummyData = cliente;
        observableCliente.setValue(dummyData);
        empty=false;
    }
    public void addVisit(Visita visita){
        dummyData.setVisitas(visita);
    }

    public void delete() {
        dummyData = null;
        observableCliente.setValue(dummyData);
    }

    public LiveData<Cliente> getCategories() {
        return observableCliente;
    }
}

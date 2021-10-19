package com.idnp.lab04mvvm.viewmodel;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.idnp.lab04mvvm.model.Cliente;
import com.idnp.lab04mvvm.ClienteRepository;
import com.idnp.lab04mvvm.Event;
import com.idnp.lab04mvvm.R;
public class RegistrarPacientesViewModel extends ViewModel {

    private final MutableLiveData<String> dni = new MutableLiveData<>();
    private final MutableLiveData<String> correo = new MutableLiveData<>();
    private final MutableLiveData<String> direccion = new MutableLiveData<>();
    private final MutableLiveData<String> nombre = new MutableLiveData<>();

    private final MutableLiveData<Event<Integer>> mErrorText = new MutableLiveData<>();

    private final MutableLiveData<Event<Integer>> mClientSavedEvent = new MutableLiveData<>();

    private final ClienteRepository mClientRepository;

    //METODO PARA
    public void saveCliente() {
        if (dni.getValue() == null || dni.getValue().length()!=8) {
            mErrorText.setValue(new Event<>(R.string.dni_invalido));
            return;
        }
        if (nombre.getValue() == null){
            mErrorText.setValue(new Event<>(R.string.nombre_invalido));
            return;
        }
        if (direccion.getValue() == null){
            mErrorText.setValue(new Event<>(R.string.direccion_invalida));
            return;
        }
        if (correo.getValue() == null || !Patterns.EMAIL_ADDRESS.matcher(correo.getValue()).matches()){
            mErrorText.setValue(new Event<>(R.string.correo_invalido));
            return;
        }


        Cliente client;
        client = new Cliente();
        client.setDni(dni.getValue());
        client.setCorreo(correo.getValue());
        client.setDireccion(direccion.getValue());
        client.setNombre(nombre.getValue());

        // Guardar categoría
        mClientRepository.addCliente(client);
        // Enviar evento de navegación
        mClientSavedEvent.setValue(new Event<>(1));
    }
    //METODO PARA OBTENER EL EVENTO
    public LiveData<Event<Integer>> getClientSavedEvent() {
        return mClientSavedEvent;
    }

    public MutableLiveData<String> getNombre() {
        return nombre;
    }
    public MutableLiveData<String> getDni() {
        return dni;
    }
    public MutableLiveData<String> getCorreo() {
        return correo;
    }
    public MutableLiveData<String> getDireccion() {
        return direccion;
    }

    public RegistrarPacientesViewModel() {
        mClientRepository = ClienteRepository.getsInstance();
    }
    public LiveData<Event<Integer>> getError() {
        return mErrorText;
    }
}

package com.idnp.lab04mvvm.viewmodel;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import com.idnp.lab04mvvm.Event;
import com.idnp.lab04mvvm.R;
import com.idnp.lab04mvvm.ClienteRepository;
import com.idnp.lab04mvvm.model.Cliente;
import com.idnp.lab04mvvm.ui.RegistrarPacientes;
import com.idnp.lab04mvvm.ui.RegistrarVisitas;

import java.util.HashMap;

public class MainViewModel extends ViewModel {

    private final ClienteRepository repository;
    private final MutableLiveData<String> txt = new MutableLiveData<>();
    private final MutableLiveData<String> filter = new MutableLiveData<>("*");

    private  LiveData<Cliente> cliente;

    private final MutableLiveData<Event<Integer>> mSnackbarText = new MutableLiveData<>();

    private final MutableLiveData<Event<Integer>> mOpenClientEvent = new MutableLiveData<>();

    public MainViewModel() {
        repository = ClienteRepository.getsInstance();
        cliente = Transformations.switchMap(filter, y -> repository.getCLiente());
    }
    //FUNCIONES DE LOS BOTONES DE LA VISTA
    public void addClient(int clientId) {
        mOpenClientEvent.setValue(new Event<>(clientId));
    }
    public void addVisit(int visitId) {
        mOpenClientEvent.setValue(new Event<>(visitId));
    }
    public void enviarCorreo(int correoID){ mOpenClientEvent.setValue(new Event<>(correoID) ); }

    //MENSAJE QUE SERA DEVUELTO DE LAS OTRAS ACTIVITYS
    public void handleActivityResult(int requestCode, int resultCode) {
        if (RegistrarPacientes.REQUEST_CODE == requestCode) {
            if (resultCode==RESULT_OK){
                mSnackbarText.setValue(new Event<>(R.string.cliente_creado));
            }else{
                mSnackbarText.setValue(new Event<>(R.string.cliente_no_creado));
            }
        }
        if (RegistrarVisitas.REQUEST_CODE2 == requestCode) {
            if (resultCode==RESULT_OK){
                mSnackbarText.setValue(new Event<>(R.string.visita_creada));
            }else{
                mSnackbarText.setValue(new Event<>(R.string.visita_no_creada));
            }
        }

    }
    //VARIABLES OBSERVADAS
    public MutableLiveData<Event<Integer>> getOpenClientEvent() {
        return mOpenClientEvent;
    }
    public MutableLiveData<Event<Integer>> getSnackbarText() {
        return mSnackbarText;
    }
    public LiveData<Cliente> getCliente() {
        return cliente;
    }

    //ACTALIZAMOS EL TXT DE LA VISTA
    public void actualizar(){
        cliente= ClienteRepository.getsInstance().getCLiente();
        txt.setValue(cliente.getValue().toString());
    }
    //OBTENERMOS EL TXT
    public MutableLiveData<String> getTxt() {
        return txt;
    }
}

package com.idnp.lab04mvvm.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.idnp.lab04mvvm.Event;
import com.idnp.lab04mvvm.R;
import com.idnp.lab04mvvm.databinding.ActivityMainBinding;
import com.idnp.lab04mvvm.model.Cliente;
import com.idnp.lab04mvvm.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        setupSnackbar();
        setupNavigation();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mViewModel.handleActivityResult(requestCode, resultCode);
        mViewModel.actualizar();
    }
    //METODO PARA GESTIONAR LOS EVENTOS QUE SE VAN DANDO
    private void setupNavigation() {
        // Abrir actividad para crear o editar categoría
        mViewModel.getOpenClientEvent().observe(this, integerEvent -> {
            Integer id = integerEvent.getContentIfNotHandled();
            LiveData<Cliente> cliente=  mViewModel.getCliente();
            if (id == 0) {
                addPaciente(id);
            }
            if (id == 1 && cliente.getValue() != null) {
                addVisit(id);
            }else if (id == 2 && cliente.getValue() != null) {
                enviarcorreo();
            }else{
                mViewModel.getSnackbarText().setValue(new Event<>(R.string.no_cliente));
            }
        });
    }
    //METODOS PARA INICIAR LAS OTRAS ACTIVITYS
    private void addPaciente(int categoryId) {
        Intent intent = new Intent(this, RegistrarPacientes.class);
        startActivityForResult(intent, RegistrarPacientes.REQUEST_CODE);
    }
    private void addVisit(int categoryId) {
        Intent intent = new Intent(this, RegistrarVisitas.class);
        startActivityForResult(intent, RegistrarVisitas.REQUEST_CODE2);
    }
    //LISTENER PARA EL MENSAJE DE INFORMACION
    private void setupSnackbar() {
        // Mostrar snackbar en resultados
        mViewModel.getSnackbarText().observe(this, integerEvent -> {
            Integer stringId = integerEvent.getContentIfNotHandled();
            if (stringId != null) {

                Snackbar.make(findViewById(R.id.vistaMain),
                        stringId, Snackbar.LENGTH_LONG).show();
            }
        });
    }
    //METODO PARA ENVIAR UN CORREO
    private  void enviarcorreo(){
        Intent correoIntent = new Intent(Intent.ACTION_SEND);
        correoIntent.setType("message/rfc22");
        String correo = mViewModel.getCliente().getValue().getCorreo();
        correoIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
        correoIntent.putExtra(Intent.EXTRA_SUBJECT, "Correo de prueba");
        correoIntent.putExtra(Intent.EXTRA_TEXT, "Aqui esta el mensaje");
        startActivity(Intent.createChooser(correoIntent, "Enviar Email"));
    }
}
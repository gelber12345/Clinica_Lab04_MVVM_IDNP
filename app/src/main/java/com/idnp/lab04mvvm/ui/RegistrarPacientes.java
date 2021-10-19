package com.idnp.lab04mvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.idnp.lab04mvvm.R;
import com.idnp.lab04mvvm.databinding.ActivityRegistrarPacientesBinding;
import com.idnp.lab04mvvm.viewmodel.RegistrarPacientesViewModel;

public class RegistrarPacientes extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    public static final String TAG = "boton";
    private ActivityRegistrarPacientesBinding mBinding;
    private RegistrarPacientesViewModel mViewModel;
    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_registrar_pacientes);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_registrar_pacientes);
        mViewModel = new ViewModelProvider(this).get(RegistrarPacientesViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        setupInputErrors();
        setupNavigation();
    }
    //METODO PARA INFORMAR SOBRE LOS ERRORES DE LOS INPUTS
    private void setupInputErrors() {
        // Limpiar código para poner lambdas
        mViewModel.getError().observe(this, event -> {
            // Mostrar error en el nombre de la categoría
            Integer msg = event.getContentIfNotHandled();
            if (msg != null) {
                switch (msg)
                {
                    case R.string.dni_invalido:  mBinding.etDni.setError(getText(msg));
                        break;
                    case R.string.nombre_invalido:  mBinding.etNombre.setError(getText(msg));
                        break;
                    case R.string.direccion_invalida:  mBinding.etDireccion.setError(getText(msg));
                        break;
                    case R.string.correo_invalido:  mBinding.etCorreo.setError(getText(msg));
                        break;
                }
            }
        });
    }
    //METODO PARA GESTIONAR LOS EVENTOS
    private void setupNavigation() {
        Log.d(TAG, "setupNavigation: ME DISTE CLICK");
        mViewModel.getClientSavedEvent().observe(this, categoryEvent -> {
            Integer categoryId = categoryEvent.getContentIfNotHandled();
            if (categoryId == 1) {
                // Finalizar actividad con resultado exitoso
                setResult(REQUEST_CODE);
                finish();
            }
        });
    }
}
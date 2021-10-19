package com.idnp.lab04mvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import com.idnp.lab04mvvm.R;
import com.idnp.lab04mvvm.databinding.ActivityRegistrarVisitasBinding;
import com.idnp.lab04mvvm.viewmodel.RegistrarPacientesViewModel;
import com.idnp.lab04mvvm.viewmodel.RegistrarVisitasViewModel;


public class RegistrarVisitas extends AppCompatActivity {
    public static final int REQUEST_CODE2 = 2;
    public static final String TAG = "boton";
    private ActivityRegistrarVisitasBinding mBinding;
    private RegistrarVisitasViewModel mViewModel;
    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_registrar_visitas);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_registrar_visitas);
        mViewModel = new ViewModelProvider(this).get(RegistrarVisitasViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        setupInputErrors();
        setupNavigation();
    }
    private void setupInputErrors() {
        // Limpiar código para poner lambdas
        mViewModel.getError().observe(this, event -> {
            // Mostrar error en el nombre de la categoría
            Integer msg = event.getContentIfNotHandled();
            if (msg != null) {
                switch (msg)
                {
                    case R.string.peso_invalido:  mBinding.etPeso.setError(getText(msg));
                        break;
                    case R.string.temperatura_invalida:  mBinding.etTemperatura.setError(getText(msg));
                        break;
                    case R.string.presion_invalida:  mBinding.etPresion.setError(getText(msg));
                        break;
                    case R.string.saturacion_invalida:  mBinding.etNivel.setError(getText(msg));
                        break;
                }
            }
        });
    }
    private void setupNavigation() {
        Log.d(TAG, "setupNavigation: ME DISTE CLICK AAAAAAAAAAAA");
        mViewModel.getVisitSavedEvent().observe(this, visitEvent -> {
            Integer categoryId = visitEvent.getContentIfNotHandled();
            if (categoryId == 1) {
                // Finalizar actividad con resultado exitoso
                setResult(REQUEST_CODE2);
                finish();
            }
        });
    }
}
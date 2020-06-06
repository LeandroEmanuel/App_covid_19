package com.example.app_covid_19;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class fragment_doencas_conhecidas extends Fragment {
    EditText editTextOutrasDoencas;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doencas_conhecidas, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextOutrasDoencas = view.findViewById(R.id.editTextNome);
        view.findViewById(R.id.buttonCancelarDoencasConhecidas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelarDoencas();
            }
        });

    }

    private void cancelarDoencas() {
        NavController navController = new NavHostFragment().findNavController(fragment_doencas_conhecidas.this);
        navController.navigate(R.id.to_menu_principal);
        editTextOutrasDoencas.setText("");
    }
}

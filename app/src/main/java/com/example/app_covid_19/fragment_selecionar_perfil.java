package com.example.app_covid_19;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class fragment_selecionar_perfil extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selecionar_perfil, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.buttonGerirPerfis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerirPerfil();
            }
        });
    }

    private void gerirPerfil() {
        NavController navController = NavHostFragment.findNavController(fragment_selecionar_perfil.this);
        navController.navigate(R.id.to_editar_perfil);
    }
}

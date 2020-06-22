package com.example.app_covid_19;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class fragment_editar_perfis extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_perfis, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        view.findViewById(R.id.buttonRegistoDiario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registoDiario();
            }
        });
        view.findViewById(R.id.buttonTestes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testes();
            }
        });
        view.findViewById(R.id.buttonHistorico).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historico();
            }
        });

    }

    private void historico() {
        NavController navController = NavHostFragment.findNavController(fragment_editar_perfis.this);
        navController.navigate(R.id.to_historico);
    }

    private void testes() {
        NavController navController = NavHostFragment.findNavController(fragment_editar_perfis.this);
        navController.navigate(R.id.to_testes);
    }

    private void registoDiario() {
        NavController navController = NavHostFragment.findNavController(fragment_editar_perfis.this);
        navController.navigate(R.id.to_registo_diario);
    }

    private void alterarPerfil() {
        NavController navController = NavHostFragment.findNavController(fragment_editar_perfis.this);
        navController.navigate(R.id.to_gestao_dados);
    }
}

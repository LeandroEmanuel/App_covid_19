package com.example.app_covid_19;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class fragment_gestao_dados extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gestao_dados, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.buttonDadosPessoais).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dadosPessoais();
            }
        });
        view.findViewById(R.id.buttonDoencas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doencasConghecidas();
            }
        });
    }

    private void doencasConghecidas() {
        NavController navController = NavHostFragment.findNavController(fragment_gestao_dados.this);
        navController.navigate(R.id.to_doencas_conhecidas);
    }

    private void dadosPessoais() {
        NavController navController = NavHostFragment.findNavController(fragment_gestao_dados.this);
        navController.navigate(R.id.to_dados_pessoais);
    }
}

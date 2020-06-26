package com.example.app_covid_19;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class fragment_testes extends Fragment{
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_testes, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_tabela_testes);

        view.findViewById(R.id.buttonNovoTeste).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                novoTeste();
            }
        });
        view.findViewById(R.id.buttonHistoricoTestes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabelaTeste();
            }
        });

    }

    private void novoTeste() {
        NavController navController = NavHostFragment.findNavController(fragment_testes.this);
        navController.navigate(R.id.action_fragment_testes_to_fragment_resultado_teste2);
    }
    private void tabelaTeste() {
        NavController navController = NavHostFragment.findNavController(fragment_testes.this);
        navController.navigate(R.id.to_tabela_resultado_testes);
    }
}

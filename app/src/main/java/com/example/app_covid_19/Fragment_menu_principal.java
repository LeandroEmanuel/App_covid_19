package com.example.app_covid_19;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public class fragment_menu_principal extends Fragment{

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_principal, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_principal);

        view.findViewById(R.id.buttonProcurarPerfil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarPerfil();
            }
        });
        view.findViewById(R.id.buttonSairApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sairApp();
            }
        });
    }

    private void sairApp() {
        getActivity().finish();
    }

    private void selecionarPerfil() {
        NavController navController = NavHostFragment.findNavController(fragment_menu_principal.this);
        navController.navigate(R.id.to_selecionar_perfil);
    }

    public void configuracoes() {
        NavController navController = NavHostFragment.findNavController(fragment_menu_principal.this);
        navController.navigate(R.id.action_fragment_menu_principal_to_fragment_configuracoes);
    }

    public void sobre() {
        NavController navController = NavHostFragment.findNavController(fragment_menu_principal.this);
        navController.navigate(R.id.action_fragment_menu_principal_to_fragment_sobre);
    }

}

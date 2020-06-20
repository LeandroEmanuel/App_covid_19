package com.example.app_covid_19;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class fragment_historico extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historico, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.buttonTabelaRegistos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabelaRegistos();
            }
        });

    }

    private void tabelaRegistos() {
        NavController navController = NavHostFragment.findNavController(fragment_historico.this);
        navController.navigate(R.id.to_tabela_registos_diarios);
    }

    //teste string to date


}
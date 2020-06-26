package com.example.app_covid_19;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_vazio);
        Context context=getContext();

        TextView textViewTotalPessoas = (TextView) view.findViewById(R.id.textViewTotalPessoas);
        TextView textViewTotalTestes = (TextView) view.findViewById(R.id.textViewTotalTestes);
        TextView textViewTotalTestesPositivos = (TextView) view.findViewById(R.id.textViewTotalTestesPositivos);
        TextView textViewTotalTestesNegativos = (TextView) view.findViewById(R.id.textViewTotalTestesNegativos);
        TextView textViewTotalTestesInconclusivos = (TextView) view.findViewById(R.id.textViewTotalTestesInconclusivos);
        TextView textViewMediaTemperaturas = (TextView) view.findViewById(R.id.textViewMediaTemperaturas);

        Estatisticas estatisticas = new Estatisticas(context);
        textViewMediaTemperaturas.setText(""+estatisticas.getMediaTemperatura());
        textViewTotalPessoas.setText(""+estatisticas.getTotalPessoas());
        textViewTotalTestes.setText(""+estatisticas.getTotalTestes());
        textViewTotalTestesPositivos.setText(""+estatisticas.getTotalTestesPositivos());
        textViewTotalTestesNegativos.setText(""+estatisticas.getTotalTestesNegativos());
        textViewTotalTestesInconclusivos.setText(""+estatisticas.getTotalTestesInconclusivos());

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
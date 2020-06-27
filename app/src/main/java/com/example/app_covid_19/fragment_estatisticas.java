package com.example.app_covid_19;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class fragment_estatisticas extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_estatisticas, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_vazio);
        Context context = getContext();

        TextView textViewTotalPessoas = (TextView) view.findViewById(R.id.textViewTotalPessoas);
        TextView textViewTotalTestes = (TextView) view.findViewById(R.id.textViewTotalTestes);
        TextView textViewTotalTestesPositivos = (TextView) view.findViewById(R.id.textViewTotalTestesPositivos);
        TextView textViewTotalTestesNegativos = (TextView) view.findViewById(R.id.textViewTotalTestesNegativos);
        TextView textViewTotalTestesInconclusivos = (TextView) view.findViewById(R.id.textViewTotalTestesInconclusivos);

        Estatisticas estatisticas = new Estatisticas(context);
        textViewTotalPessoas.setText(getString(R.string.pessoas_registadas)+estatisticas.getTotalPessoas());
        textViewTotalTestes.setText(getString(R.string.testes_feitos)+estatisticas.getTotalTestes());
        textViewTotalTestesPositivos.setText(getString(R.string.testes_positivos)+estatisticas.getTotalTestesPositivos());
        textViewTotalTestesNegativos.setText(getString(R.string.testes_negativos)+estatisticas.getTotalTestesNegativos());
        textViewTotalTestesInconclusivos.setText(getString(R.string.testes_inconclusivos)+estatisticas.getTotalTestesInconclusivos());

    }
}
package com.example.app_covid_19;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class fragment_editar_menu_perfis extends Fragment {


    private TextView textViewTituloEditarPerfil;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_menu_perfis, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textViewTituloEditarPerfil = (TextView) view.findViewById(R.id.textViewTituloEditarPerfil);//aqui

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
        NavController navController = NavHostFragment.findNavController(fragment_editar_menu_perfis.this);
        navController.navigate(R.id.to_historico);
    }

    private void testes() {
        NavController navController = NavHostFragment.findNavController(fragment_editar_menu_perfis.this);
        navController.navigate(R.id.to_testes);
    }

    private void registoDiario() {
        Context context = getContext();

        long idPerfilSelecionado = ((MainActivity) getActivity()).getPerfil().getId();
        BdCovidOpenHelper openHelper = new BdCovidOpenHelper(context);

        SQLiteDatabase dbCovid = openHelper.getReadableDatabase();
        BdTabelaRegistos bdTabelaRegistos = new BdTabelaRegistos(dbCovid);

        int existeRegisto = bdTabelaRegistos.contaRegistos(idPerfilSelecionado);

       if(existeRegisto == 0){
           NavController navController = NavHostFragment.findNavController(fragment_editar_menu_perfis.this);
           navController.navigate(R.id.action_fragment_editar_perfis_to_fragment_insere_registo_diario);
       }else {
           NavController navController = NavHostFragment.findNavController(fragment_editar_menu_perfis.this);
           navController.navigate(R.id.action_fragment_editar_perfis_to_fragment_altera_registo);
       }

    }

}

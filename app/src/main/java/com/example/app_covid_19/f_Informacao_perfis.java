package com.example.app_covid_19;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class f_Informacao_perfis extends Fragment {


    private TextView textViewTituloEditarPerfil;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informacao_perfis, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();

        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_voltar);
        textViewTituloEditarPerfil = (TextView) view.findViewById(R.id.textViewTituloEditarPerfil);

        view.findViewById(R.id.buttonRegistoDiario).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registoDiario();
            }
        });
        view.findViewById(R.id.buttonTabelaRegistos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabelaRegistos();
            }
        });
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

    private void registoDiario() {
           NavController navController = NavHostFragment.findNavController(f_Informacao_perfis.this);
           navController.navigate(R.id.action_fragment_editar_perfis_to_fragment_insere_registo_diario);
    }
    private void tabelaRegistos() {
        NavController navController = NavHostFragment.findNavController(f_Informacao_perfis.this);
        navController.navigate(R.id.to_tabela_registos_diarios);
    }
    public void selecionar_perfil() {
        NavController navController = NavHostFragment.findNavController(f_Informacao_perfis.this);
        navController.navigate(R.id.to_Selecionar_perfil);
    }
    private void novoTeste() {
        NavController navController = NavHostFragment.findNavController(f_Informacao_perfis.this);
        navController.navigate(R.id.action_fragment_editar_perfis_to_fragment_resultado_teste);
    }
    private void tabelaTeste() {
        NavController navController = NavHostFragment.findNavController(f_Informacao_perfis.this);
        navController.navigate(R.id.action_fragment_editar_perfis_to_fragment_tabela_resultado_testes);
    }

}

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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;


public class f_Inserir_teste extends Fragment {

    TextView textViewDataTesteResultado;
    private int mAno, mMes, mDia;
    private String sysDate;
    private Teste teste;
    private RadioButton radioButtoTestePositivo;
    private RadioButton radioButtonTesteNegativo;
    private RadioButton radioButtonTesteInconclusivo;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inserir_teste, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_inserir_teste);

        textViewDataTesteResultado = (TextView)view.findViewById(R.id.textViewDataTesteResultado);
        radioButtoTestePositivo = (RadioButton)view.findViewById(R.id.radioButtoTestePositivo);
        radioButtonTesteNegativo = (RadioButton)view.findViewById(R.id.radioButtonTesteNegativo);
        radioButtonTesteInconclusivo = (RadioButton)view.findViewById(R.id.radioButtonTesteInconclusivo);

        final Calendar calendario = Calendar.getInstance();
        mAno = calendario.get(Calendar.YEAR);
        mMes = calendario.get(Calendar.MONTH);
        mDia = calendario.get(Calendar.DAY_OF_MONTH);
        sysDate = mDia + "/" + (mMes +1) + "/" + mAno;

        textViewDataTesteResultado.setText(sysDate);


    }

    public void cancelarInserirTeste() {
        NavController navController = NavHostFragment.findNavController(f_Inserir_teste.this);
        navController.navigate(R.id.action_fragment_resultado_teste_to_fragment_editar_perfis);
    }

    public void guardarNovoTeste(){

        boolean auxPositivo = radioButtoTestePositivo.isChecked();
        boolean auxNegativo = radioButtonTesteNegativo.isChecked();
        boolean auxInconclusico = radioButtonTesteInconclusivo.isChecked();

        int resultado = 0;
        if(auxPositivo){
            resultado =1;
        }else if(auxNegativo){
            resultado = 2;
        }else if(auxInconclusico){
            resultado = 3;
        }
        if (resultado == 0){
            radioButtoTestePositivo.setError("");
            radioButtonTesteNegativo.setError("");
            radioButtonTesteInconclusivo.setError("");
            return;
        }


        long idPerfilSelecionado = ((MainActivity) getActivity()).getPerfil().getId();
        teste = new Teste();
        teste.setDataTeste(sysDate);
        teste.setResultadoTeste(resultado);
        teste.setIdPerfil(idPerfilSelecionado);

        try {
            getActivity().getContentResolver().insert(BdCovidContentProvider.ENDERECO_TESTES, Converte.testeParaContentValues(teste));
            Toast.makeText(getContext(),R.string.teste_guardado_com_sucesso, Toast.LENGTH_SHORT).show();
            NavController navController = NavHostFragment.findNavController(f_Inserir_teste.this);
            navController.navigate(R.id.action_fragment_resultado_teste_to_fragment_editar_perfis);

        } catch (Exception e) {
            Snackbar.make(textViewDataTesteResultado, R.string.erro_inserir_teste, Snackbar.LENGTH_INDEFINITE).show();
        }
    }
}

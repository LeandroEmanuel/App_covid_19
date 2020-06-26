package com.example.app_covid_19;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;


public class fragment_inserir_teste extends Fragment {

    TextView textViewDataTesteResultado;
    private int mAno, mMes, mDia;
    private String sysDate;
    private Teste teste;
    private RadioButton radioButtoTestePositivo;
    private RadioButton radioButtonTesteNegativo;
    private RadioButton radioButtonTesteInconclusivo;
    public static final int _CURSOR_LOADER_PERFIS = 0;



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

        textViewDataTesteResultado =(TextView)view.findViewById(R.id.textViewDataTesteResultado);
        //radioGroupTeste = (RadioGroup) view.findViewById(R.id.radioGroupTeste);

        final Calendar calendario = Calendar.getInstance();
        mAno = calendario.get(Calendar.YEAR);
        mMes = calendario.get(Calendar.MONTH);
        mDia = calendario.get(Calendar.DAY_OF_MONTH);
        sysDate = mDia + "/" + (mMes +1) + "/" + mAno;

        textViewDataTesteResultado.setText(sysDate);


    }

    public void cancelarInserirTeste() {
        NavController navController = NavHostFragment.findNavController(fragment_inserir_teste.this);
        navController.navigate(R.id.action_fragment_resultado_teste_to_fragment_testes);
    }

    public void guardarNovoTeste(){
        //todo: guardar o radioButton selecionado

        long idPerfilSelecionado = ((MainActivity) getActivity()).getPerfil().getId();
        teste = new Teste();
        teste.setDataTeste(sysDate);
        //teste.setResultadoTeste(radiobuton);
        teste.setIdPerfil(idPerfilSelecionado);

        try {
            getActivity().getContentResolver().insert(BdCovidContentProvider.ENDERECO_TESTES, Converte.testeParaContentValues(teste));
            Toast.makeText(getContext(),R.string.teste_guardado_com_sucesso, Toast.LENGTH_SHORT).show();
            NavController navController = NavHostFragment.findNavController(fragment_inserir_teste.this);
            navController.navigate(R.id.actionaltera_dados_pessoais_to__selecionar_perfil);

        } catch (Exception e) {
            Snackbar.make(textViewDataTesteResultado, R.string.erro_inserir_teste, Snackbar.LENGTH_INDEFINITE).show();
        }
    }
}
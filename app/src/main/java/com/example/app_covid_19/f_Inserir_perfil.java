package com.example.app_covid_19;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;


public class f_Inserir_perfil extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText editTextViewDataNascimento;
    Button buttonSelecionarDataNascimento;
    private int mAno, mMes, mDia;
    private EditText editTextNome;
    private CheckBox checkBoxCardiovascular;
    private CheckBox checkBoxDiabetes;
    private CheckBox checkBoxHipertensao;
    private CheckBox checkBoxDoencasOncologicas;
    private CheckBox checkBoxResp;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insere_perfil, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_inserir_perfil);


        editTextNome = view.findViewById(R.id.editTextNome);
        editTextViewDataNascimento = view.findViewById(R.id.textViewAlteraDataNascimento);

        buttonSelecionarDataNascimento = (Button)view.findViewById(R.id.buttonSelecionarDataNascimento);
        editTextViewDataNascimento =(EditText) view.findViewById(R.id.textViewAlteraDataNascimento);
        checkBoxCardiovascular = (CheckBox) view.findViewById(R.id.checkBoxCardiovascular);
        checkBoxDiabetes = (CheckBox) view.findViewById(R.id.checkBoxDiabetes);
        checkBoxHipertensao = (CheckBox) view.findViewById(R.id.checkBoxHipertensao);
        checkBoxDoencasOncologicas = (CheckBox) view.findViewById(R.id.checkBoxDoencasOncologicas);
        checkBoxResp = (CheckBox) view.findViewById(R.id.checkBoxResp);

        view.findViewById(R.id.buttonSelecionarDataNascimento).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( v == buttonSelecionarDataNascimento){
                    final Calendar calendario = Calendar.getInstance();
                    mAno = calendario.get(Calendar.YEAR);
                    mMes = calendario.get(Calendar.MONTH);
                    mDia = calendario.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            editTextViewDataNascimento.setText(dayOfMonth+ "/" +(month + 1) + "/" + year);
                            retirarFocus();
                        }
                    }, mAno, mMes, mDia);
                    datePickerDialog.show();
                }
            }
        });

    }


    public void guardaNovoPerfil() {// não insere dados pessois
        String nome = editTextNome.getText().toString();
        String dataNascimento = editTextViewDataNascimento.getText().toString();

        boolean auxCardio = checkBoxCardiovascular.isChecked();
        int cardio;
        if(auxCardio){
            cardio = 1;
        }
        else {
            cardio = 0;
        }

        boolean auxDiabetes = checkBoxDiabetes.isChecked();
        int diabetes;
        if(auxDiabetes){
            diabetes = 1;
        } else{
            diabetes = 0;
        }
        boolean auxHiper = checkBoxHipertensao.isChecked();
        int hiper;
        if(auxHiper){
            hiper = 1;
        } else{
            hiper = 0;
        }
        boolean auxOnco = checkBoxDoencasOncologicas.isChecked();
        int onco;
        if(auxOnco){
            onco = 1;
        } else{
            onco = 0;
        }
        boolean auxResp = checkBoxResp.isChecked();
        int resp;
        if(auxResp){
            resp = 1;
        } else{
            resp = 0;
        }
        // validacoes

        if ((nome.length() != 0) ){
            String aux = "";
            String aux2 = nome;
            int pos = 0;

            for(int i = 0; i < nome.length();i++) {// correr a string caracter a caracter

                if(pos == 0)//if de controlo para maiuscula
                {
                    aux = aux + aux2.substring(0, 1).toUpperCase();
                    aux2=aux2.substring(1);// remover do aux2 a letra introduzida em aux
                    pos++;
                }
                else
                {
                    if(i == nome.indexOf(" ",i))// descobrir o espaco a partir da posicao i
                    {
                        pos=0;
                    }
                    aux = aux + aux2.substring(0, 1);
                    aux2 = aux2.substring(1);
                }
            }
            nome = aux;
        }
        if(nome.length() == 0){
            editTextNome.setError(getString(R.string.preencher_nome));
            editTextNome.requestFocus();
            return;
        } else if (!nome.matches("^([A-z][a-z]*((\\s)))+[A-z][a-z]*$")){//https://stackoverflow.com/questions/7362567/java-regex-for-full-name
            editTextNome.setError(getString(R.string.nome_invalido));
            editTextNome.requestFocus();
            return;
        }
        if(dataNascimento.length() == 0){
            editTextViewDataNascimento.setError(getString(R.string.selecionar_data));
            editTextViewDataNascimento.requestFocus();
            return;
        }

        Perfil perfil = new Perfil();
        perfil.setNome(nome);
        perfil.setDataNascimento(dataNascimento);
        perfil.setCardio(cardio);
        perfil.setDiabetes(diabetes);
        perfil.setHiper(hiper);
        perfil.setOnco(onco);
        perfil.setResp(resp);

        try {
            getActivity().getContentResolver().insert(BdCovidContentProvider.ENDERECO_PERFIS, Converte.perfilParaContentValues(perfil));
            Toast.makeText(getContext(), R.string.perfil_inserido_sucesso, Toast.LENGTH_SHORT).show();
            NavController navController = NavHostFragment.findNavController(f_Inserir_perfil.this);
            navController.navigate(R.id.fragment_selecionar_perfil2);
        } catch (Exception e){
            Snackbar.make(editTextNome, R.string.erro_inserir_perfil, Snackbar.LENGTH_INDEFINITE).show();
        }
    }
    public void retirarFocus(){
        editTextViewDataNascimento.clearFocus();
        editTextViewDataNascimento.setError(null);
    }

    public void cancelarInserirPerfil() {
        NavController navController = NavHostFragment.findNavController(f_Inserir_perfil.this);
        navController.navigate(R.id.fragment_selecionar_perfil2);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new androidx.loader.content.CursorLoader(getContext(), BdCovidContentProvider.ENDERECO_PERFIS, BdTabelaPerfis.TODOS_OS_CAMPOS, null, null, BdTabelaPerfis.NOME);
    }


    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }


    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
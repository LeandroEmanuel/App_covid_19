package com.example.app_covid_19;

import android.app.DatePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;


public class fragment_alterar_perfil extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private TextView textViewAlteraDataNascimento;
    private EditText editTextAlteraNome;
    Button buttonSelecionarDataNascimento;
    private int mAno, mMes, mDia;
    private Perfil perfil;
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
        return inflater.inflate(R.layout.fragment_altera_perfil, container, false);
    }


    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_alterar_perfil);


        editTextAlteraNome = (EditText) view.findViewById(R.id.editTextNome);
        textViewAlteraDataNascimento =(TextView) view.findViewById(R.id.textViewAlteraDataNascimento);

        perfil = activity.getPerfil();
        editTextAlteraNome.setText(perfil.getNome());
        textViewAlteraDataNascimento.setText(perfil.getDataNascimento());

        buttonSelecionarDataNascimento = (Button)view.findViewById(R.id.buttonSelecionarDataNascimento);
        textViewAlteraDataNascimento = (TextView)view.findViewById(R.id.textViewAlteraDataNascimento);
        checkBoxCardiovascular = (CheckBox) view.findViewById(R.id.checkBoxCardiovascular);
        checkBoxDiabetes = (CheckBox) view.findViewById(R.id.checkBoxDiabetes);
        checkBoxHipertensao = (CheckBox) view.findViewById(R.id.checkBoxHipertensao) ;
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
                            textViewAlteraDataNascimento.setText(dayOfMonth+ "/" +(month + 1) + "/" + year);
                        }
                    }, mAno, mMes, mDia);
                    datePickerDialog.show();
                }
            }
        });
        
    }

    public void cancelarAlterarDadosPessoais() {
        NavController navController = NavHostFragment.findNavController(fragment_alterar_perfil.this);
        navController.navigate(R.id.actionaltera_dados_pessoais_to__selecionar_perfil);
    }

    public void guardaAlteraPerfil() {
        String nome = editTextAlteraNome.getText().toString();
        String dataNascimento = textViewAlteraDataNascimento.getText().toString();

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

        //Perfil perfil = activity.getPerfil();
        perfil.setNome(nome);
        perfil.setDataNascimento(dataNascimento);
        perfil.setCardio(cardio);
        perfil.setDiabetes(diabetes);
        perfil.setHiper(hiper);
        perfil.setOnco(onco);
        perfil.setResp(resp);

        try {
            Uri enderecoPerfil = Uri.withAppendedPath(BdCovidContentProvider.ENDERECO_PERFIS, String.valueOf(perfil.getId()));

            int registos = getActivity().getContentResolver().update(enderecoPerfil, Converte.perfilParaContentValues(perfil), BdTabelaPerfis._ID+ "=?", new String[]{String.valueOf(perfil.getId())});
            if(registos == 1){
                Toast.makeText(getContext(),R.string.perfil_inserido_sucesso, Toast.LENGTH_SHORT).show();
                NavController navController = NavHostFragment.findNavController(fragment_alterar_perfil.this);
                navController.navigate(R.id.actionaltera_dados_pessoais_to__selecionar_perfil);
                return;
            }
        } catch (Exception e){
            Snackbar.make(editTextAlteraNome, R.string.erro_alterar_perfil, Snackbar.LENGTH_INDEFINITE).show();
        }
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
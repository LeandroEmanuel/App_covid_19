package com.example.app_covid_19;

import android.app.DatePickerDialog;
import android.content.ClipData;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;


public class fragment_insere_dados_pessoais extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    EditText nome;
    TextView textViewDataNascimento;
    Button buttonSelecionarDataNascimento;
    private int mAno, mMes, mDia;
    private EditText editTextNome;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insere_dados_pessoais, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_inserir_perfil);
        

        nome = view.findViewById(R.id.editTextAlteraNome);
        textViewDataNascimento = view.findViewById(R.id.textViewAlteraDataNascimento);
        view.findViewById(R.id.buttonCancelarDAdosPessoais).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               cancelarDadosPessoais();

           }
       });
        buttonSelecionarDataNascimento = (Button)view.findViewById(R.id.buttonSelecionarDataNascimento);
        textViewDataNascimento =(TextView)view.findViewById(R.id.textViewAlteraDataNascimento);
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
                            textViewDataNascimento.setText(dayOfMonth+ "/" +(month + 1) + "/" + year);
                        }
                    }, mAno, mMes, mDia);
                    datePickerDialog.show();
                }
            }
        });
        
    }
    private void cancelarDadosPessoais() {
        NavController navController = NavHostFragment.findNavController(fragment_insere_dados_pessoais.this);
        navController.navigate(R.id.to_menu_principal);
        nome.setText("");
        textViewDataNascimento.setText("");

    }

    public void guardaNovoPerfil() {
        String nome = editTextNome.getText().toString();
        String dataNascimento = textViewDataNascimento.getText().toString();

        if(nome.length() == 0){
            editTextNome.setError("Preencha o nome!");
            editTextNome.requestFocus();
            return;
        } else if(dataNascimento.length() == 0){
            textViewDataNascimento.setError("Selecione uma data!");
            textViewDataNascimento.requestFocus();
        }

        //todo: acrescentar mais validacoes

        Perfil perfil = new Perfil();
        perfil.setNome(nome);
        perfil.setDataNascimento(dataNascimento);

        try {
            getActivity().getContentResolver().insert(BdCovidContentProvider.ENDERECO_PERFIS, Converte.perfilParaContentValues(perfil));
            Toast.makeText(getContext(), "Perfil inserido com sucesso", Toast.LENGTH_SHORT).show();
            NavController navController = NavHostFragment.findNavController(fragment_insere_dados_pessoais.this);
            navController.navigate(R.id.fragment_selecionar_perfil2);
        } catch (Exception e){
            Snackbar.make(editTextNome,"Erro: Não foi possivel inserir o perfil! ", Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    public void cancelarInserirPerfil() {
        NavController navController = NavHostFragment.findNavController(fragment_insere_dados_pessoais.this);
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
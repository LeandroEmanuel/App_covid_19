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


public class fragment_altera_dados_pessoais extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {


    private TextView textViewAlteraDataNascimento;
    Button buttonSelecionarDataNascimento;
    private int mAno, mMes, mDia;
    private EditText editTextAlteraNome;
    private Perfil perfil;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_altera_dados_pessoais, container, false);
    }

    public void onViewCreated(@NonNull final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_alterar_perfil);


        editTextAlteraNome = (EditText) view.findViewById(R.id.editTextAlteraNome);
        textViewAlteraDataNascimento =(TextView) view.findViewById(R.id.textViewAlteraDataNascimento);

        perfil = activity.getPerfil();
        editTextAlteraNome.setText(perfil.getNome());
        textViewAlteraDataNascimento.setText(perfil.getDataNascimento());

        //todo: ver se o bbotao de get data funciona

        buttonSelecionarDataNascimento = (Button)view.findViewById(R.id.buttonSelecionarDataNascimento);
        textViewAlteraDataNascimento =(TextView)view.findViewById(R.id.textViewAlteraDataNascimento);
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
        NavController navController = NavHostFragment.findNavController(fragment_altera_dados_pessoais.this);
        navController.navigate(R.id.actionaltera_dados_pessoais_to__selecionar_perfil);
    }

    public void guardaAlteraPerfil() {
        String nome = editTextAlteraNome.getText().toString();
        String dataNascimento = textViewAlteraDataNascimento.getText().toString();

        MainActivity activity = (MainActivity) getActivity();

        Perfil perfil = activity.getPerfil();
        perfil.setNome(nome);
        perfil.setDataNascimento(dataNascimento);

        try {
            Uri enderecoPerfil = Uri.withAppendedPath(BdCovidContentProvider.ENDERECO_PERFIS, String.valueOf(perfil.getId()));

            int registos = getActivity().getContentResolver().update(enderecoPerfil, Converte.perfilParaContentValues(perfil), null, null);
            if(registos == 1){
                Toast.makeText(getContext(),"Perfil guardado com sucesso", Toast.LENGTH_SHORT).show();
                NavController navController = NavHostFragment.findNavController(fragment_altera_dados_pessoais.this);
                navController.navigate(R.id.actionaltera_dados_pessoais_to__selecionar_perfil);
                return;
            }
        } catch (Exception e){
            Snackbar.make(editTextAlteraNome,"Erro: Não foi possivel alterar o perfil! ", Snackbar.LENGTH_INDEFINITE).show();
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
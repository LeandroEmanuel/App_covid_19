package com.example.app_covid_19;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;


public class f_Inserir_registo extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    EditText editTextTemperatura;
    TextView textViewDataRegistoDiario;
    CheckBox checkBoxTosse;
    CheckBox checkBoxDifResp;
    private int mAno, mMes, mDia;
    private Registo registo;
    public static int ID_CURSOR_LOADER_PERFIS;
    private String sysDate;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inserir_registo, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context =getContext();

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_inserir_registo);

        editTextTemperatura = view.findViewById(R.id.editTextTemperatura);
        textViewDataRegistoDiario =(TextView)view.findViewById(R.id.textViewDataRegistoDiario);
        checkBoxTosse = (CheckBox) view.findViewById(R.id.checkBoxTosse);
        checkBoxDifResp = (CheckBox) view.findViewById(R.id.checkBoxDifResp);

        final Calendar calendario = Calendar.getInstance();
        mAno = calendario.get(Calendar.YEAR);
        mMes = calendario.get(Calendar.MONTH);
        mDia = calendario.get(Calendar.DAY_OF_MONTH);
        sysDate = mDia + "/" + (mMes +1) + "/" + mAno;
        textViewDataRegistoDiario.setText(sysDate);
        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_PERFIS, null, this);
    }

    public void cancelarRegistoDiario() {
        NavController navController = NavHostFragment.findNavController(f_Inserir_registo.this);
        navController.navigate(R.id.action_fragment_insere_registo_diario_to_fragment_editar_perfis);
    }
    public void guardar_novo_registo(){
        float temperatura;
        boolean auxtosse = checkBoxTosse.isChecked();
        int tosse;
        if(auxtosse){
            tosse = 1;
        }
        else {
            tosse = 0;
        }

        boolean auxeDifResp = checkBoxDifResp.isChecked();
        int difResp;
        if(auxeDifResp){
            difResp = 1;
        } else{
            difResp = 0;
        }
        try {
            temperatura = Float.parseFloat(editTextTemperatura.getText().toString());
            if (("" + temperatura).length() == 0) {
                editTextTemperatura.setError(getString(R.string.temperatura_invalida));
                editTextTemperatura.requestFocus();
                return;
            } else if (temperatura <= 34.0 || temperatura > 50.0) {
                editTextTemperatura.setError(getString(R.string.temperatura_invalida));
                editTextTemperatura.requestFocus();
                return;
            }
        }catch(Exception e){
            editTextTemperatura.setError(getString(R.string.temperatura_invalida));
            editTextTemperatura.requestFocus();
            return;
        }
        long idPerfilSelecionado = ((MainActivity) getActivity()).getPerfil().getId();// ir buscar o id de um perfil
        Registo registo = new Registo();
        registo.setDataRegisto(sysDate);
        registo.setTemperatura(temperatura);
        registo.setTosse(tosse);
        registo.setDifResp(difResp);
        registo.setIdPerfil(idPerfilSelecionado);
        try{
            getActivity().getContentResolver().insert(BdCovidContentProvider.ENDERECO_REGISTOS, Converte.registoParaContentValues(registo));
            Toast.makeText(getContext(), R.string.registo_inserido_sucesso, Toast.LENGTH_SHORT).show();
            NavController navController = NavHostFragment.findNavController(f_Inserir_registo.this);
            navController.navigate(R.id.action_fragment_insere_registo_diario_to_fragment_editar_perfis);
        }catch (Exception e){
            Snackbar.make(editTextTemperatura, R.string.erro_inserir_registo, Snackbar.LENGTH_INDEFINITE).show();
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new androidx.loader.content.CursorLoader(getContext(), BdCovidContentProvider.ENDERECO_REGISTOS, BdTabelaRegistos.TODOS_OS_CAMPOS, null, null, BdTabelaPerfis.NOME);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
    }


}

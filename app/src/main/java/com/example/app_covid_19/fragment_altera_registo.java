package com.example.app_covid_19;

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
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class fragment_altera_registo extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private TextView textViewDataRegistoDiario;
    private EditText editTextTemperatura;
    CheckBox checkBoxTosse;
    CheckBox checkBoxDifResp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_altera_registo, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanState){
        super.onViewCreated(view, savedInstanState);
        Context context = getContext();

        MainActivity activity =(MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_alterar_registo);
        textViewDataRegistoDiario = (TextView) view.findViewById(R.id.textViewDataRegistoDiario);
        editTextTemperatura = (EditText) view.findViewById(R.id.editTextTemperatura);

    }
    public void cancelarAlteraRegistoDiario() {
        NavController navController = NavHostFragment.findNavController(fragment_altera_registo.this);
        navController.navigate(R.id.action_fragment_altera_registo_to_fragment_selecionar_perfil2);
    }
    public void guardarRegistoAlterado(){
        float temperatura = Float.parseFloat(editTextTemperatura.getText().toString());
        String dataRegisto = textViewDataRegistoDiario.getText().toString();

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

        long idPerfilSelecionado = ((MainActivity) getActivity()).getPerfil().getId();// ir buscar o id de um perfil
        Registo registo = new Registo();
        registo.setDataRegisto(dataRegisto);
        registo.setTemperatura(temperatura);
        registo.setTosse(tosse);
        registo.setDifResp(difResp);
        registo.setIdPerfil(idPerfilSelecionado);

        try{
            getActivity().getContentResolver().insert(BdCovidContentProvider.ENDERECO_REGISTOS, Converte.registoParaContentValues(registo));
            Toast.makeText(getContext(),"Registo alterado com sucesso", Toast.LENGTH_SHORT).show();
            NavController navController = NavHostFragment.findNavController(fragment_altera_registo.this);
            navController.navigate(R.id.action_fragment_altera_registo_to_fragment_selecionar_perfil2);
        }catch (Exception e){
            Snackbar.make(editTextTemperatura,"Não foi possivel alterar o registo", Snackbar.LENGTH_INDEFINITE).show();
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
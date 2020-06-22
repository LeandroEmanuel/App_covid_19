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
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;


public class fragment_insere_registo_diario extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    EditText editTextTemperatura;
    TextView textViewDataRegistoDiario;
    Button buttonSelecionarDataRegisto;
    CheckBox checkBoxTosse;
    CheckBox checkBoxDifResp;
    private int mAno, mMes, mDia;
    private Registo registo;
    public static int ID_CURSOR_LOADER_PERFIS;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_insere_registo_diario, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context =getContext();

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_insere_registo_diario);

        editTextTemperatura = view.findViewById(R.id.editTextTemperatura);
        buttonSelecionarDataRegisto = (Button)view.findViewById(R.id.buttonSelecionarDataRegisto);
        textViewDataRegistoDiario =(TextView)view.findViewById(R.id.textViewDataRegistoDiario);
        view.findViewById(R.id.buttonSelecionarDataRegisto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( v == buttonSelecionarDataRegisto){
                    final Calendar calendario = Calendar.getInstance();
                    mAno = calendario.get(Calendar.YEAR);
                    mMes = calendario.get(Calendar.MONTH);
                    mDia = calendario.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            textViewDataRegistoDiario.setText(dayOfMonth+ "/" +(month + 1) + "/" + year);
                        }
                    }, mAno, mMes, mDia);
                    datePickerDialog.show();
                }
            }
        });
        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_PERFIS, null, this);
    }

    private void cancelarRegistoDiario() {
        NavController navController = NavHostFragment.findNavController(fragment_insere_registo_diario.this);
        navController.navigate(R.id.to_menu_principal);
    }
    public void guardar_novo_registo(){
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

        Registo registo = new Registo();
        registo.setDataRegisto(dataRegisto);
        registo.setTemperatura(temperatura);
        registo.setTosse(tosse);

        try{
            getActivity().getContentResolver().insert(BdCovidContentProvider.ENDERECO_REGISTOS, Converte.registoParaContentValues(registo));
            Toast.makeText(getContext(),"Registo inserido com sucesso", Toast.LENGTH_SHORT).show();
            NavController navController = NavHostFragment.findNavController(fragment_insere_registo_diario.this);
            navController.navigate(R.id.action_fragment_insere_registo_diario_to_fragment_selecionar_perfil2);
        }catch (Exception e){
            Snackbar.make(editTextTemperatura,"Não foi possivel inserir o registo", Snackbar.LENGTH_INDEFINITE).show();
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

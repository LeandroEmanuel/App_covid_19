package com.example.app_covid_19;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;


public class fragment_elterar_testes extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private int mAno, mMes, mDia;
    private String sysDate;
    public static final int _CURSOR_LOADER_PERFIS = 0;
    Teste teste;
    TextView textViewAlteraDataTesteResultado;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elterar_testes, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();

        MainActivity activity = (MainActivity) getActivity();
        activity.setFragmentActual(this);
        activity.setMenuActual(R.menu.menu_alterar_teste);
        teste = activity.getTest();

        textViewAlteraDataTesteResultado =(TextView)view.findViewById(R.id.textViewDataTesteResultado);

        final Calendar calendario = Calendar.getInstance();
        mAno = calendario.get(Calendar.YEAR);
        mMes = calendario.get(Calendar.MONTH);
        mDia = calendario.get(Calendar.DAY_OF_MONTH);
        sysDate = mDia + "/" + (mMes +1) + "/" + mAno;

        textViewAlteraDataTesteResultado.setText(sysDate);
    }
    public void cancelarAlterarTeste(){
        NavController navController = NavHostFragment.findNavController(fragment_elterar_testes.this);
        navController.navigate(R.id.actionaltera_dados_pessoais_to__selecionar_perfil);
    }

    public void guardarTesteAlterado(){

        long idPerfilSelecionado = ((MainActivity) getActivity()).getPerfil().getId();


        teste.setDataTeste(sysDate);
        //teste.setResultadoTeste(radiobuton);
        teste.setIdPerfil(idPerfilSelecionado);

        try {
            Uri enderecoPerfil = Uri.withAppendedPath(BdCovidContentProvider.ENDERECO_TESTES, String.valueOf(teste.getId()));

            int registos = getActivity().getContentResolver().update(enderecoPerfil, Converte.testeParaContentValues(teste), BdTabelaTestes._ID+ "=?", new String[]{String.valueOf(teste.getId())});
            if(registos == 1){
                Toast.makeText(getContext(),"Teste guardado com sucesso", Toast.LENGTH_SHORT).show();
                NavController navController = NavHostFragment.findNavController(fragment_elterar_testes.this);
                navController.navigate(R.id.actionaltera_dados_pessoais_to__selecionar_perfil);
                return;
            }
        } catch (Exception e) {
            Snackbar.make(textViewAlteraDataTesteResultado,"Erro: Não foi possivel alterar o teste! ", Snackbar.LENGTH_INDEFINITE).show();
        }

    }
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}